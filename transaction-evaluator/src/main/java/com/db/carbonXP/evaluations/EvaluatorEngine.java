package com.db.carbonXP.evaluations;

import com.google.gson.Gson;
import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Component
public class EvaluatorEngine {

    @Autowired
    Environment environment;

    @Autowired
    TransactionServiceImpl transactionService;

    QueueClient receiveClient;
    Map<Long,Long> skuMap ;
    public void evaluate() throws Exception {
       try {
           loadSkuMap();
           receiveClient = new QueueClient(new ConnectionStringBuilder(environment.getProperty("queueConnection"), environment.getProperty("queueName")), ReceiveMode.PEEKLOCK);
           System.out.println("Reached evaluator");
           this.registerReceiver(receiveClient);
           System.out.println("Reached end");
           // shut down receiver to close the receive loop
           while (true) {
           }
       } catch (Exception e){
           System.out.println(e.getMessage());
           if(receiveClient!=null)
               receiveClient.close();
       }
    }

    private void loadSkuMap() throws FileNotFoundException {
        System.out.println("Reached sku loader");
        BufferedReader reader;
        File file = ResourceUtils.getFile("classpath:skudata.csv");
        skuMap = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            line = reader.readLine();
            Random random = new Random();
            System.out.println("Reading sku data");
            while (line != null) {
                System.out.println(line);
                System.out.println("Reading line");
                String[] data = line.split(",");
                skuMap.put(Long.parseLong(data[0]),Long.parseLong(data[1]));
                line = reader.readLine();
            }
            reader.close();
            System.out.println("Loaded sku map");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void registerReceiver(QueueClient queueClient) throws Exception {
        // register the RegisterMessageHandler callback
        Gson gson = new Gson();
        System.out.println("Trying to read messages");
        queueClient.registerMessageHandler(
                new IMessageHandler() {
                    // callback invoked when the message handler loop has obtained a message
                    public CompletableFuture<Void> onMessageAsync(IMessage message) {
                        // receives message is passed to callback
                       System.out.println ("Trying...");
                        if (message.getLabel() != null &&
                                message.getContentType() != null &&
                                message.getLabel().contentEquals("TransactionRAW") &&
                                message.getContentType().contentEquals("application/json")) {

                            byte[] body = message.getBody();
                            TransactionRaw transactionRaw = gson.fromJson(new String(body, StandardCharsets.UTF_8), TransactionRaw.class);

                            System.out.println("Got message"+transactionRaw.getName());
                            evaluateAndDump(transactionRaw);
                        }

                        return CompletableFuture.completedFuture(null);
                    }

                    // callback invoked when the message handler has an exception to report
                    public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
                        System.out.printf(exceptionPhase + "-" + throwable.getMessage());
                    }
                },
                // 1 concurrent call, messages are auto-completed, auto-renew duration
                new MessageHandlerOptions(1, true, Duration.ofMinutes(1)));
    }

    private void evaluateAndDump(TransactionRaw transactionRaw) {
       // System.out.println(getLatestTransaction(transactionRaw.getAccountNumber()));
         Optional<Transaction> lastTransaction= transactionService.findByAccountNumber(transactionRaw.getAccountNumber()).stream().
                max(Comparator.comparing(Transaction::getTransactionId));

         double credits = getCarbonEquivalentValue(transactionRaw.getSkuID(),transactionRaw.getUnits(),transactionRaw.getCategory(),transactionRaw.getDescription());
         if(lastTransaction.isPresent()){
             Transaction transaction = new Transaction();
             transaction.setAccountNumber(transactionRaw.getAccountNumber());
             transaction.setCategory(transactionRaw.getName());
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
             transaction.setDate(simpleDateFormat.format(new Date()));
             transaction.setTransactionType("Credit");
             transaction.setDescription(transactionRaw.getDescription());
             transaction.setTransactionId(new Date().getTime());
             transaction.setCreditDebitCarbonAmount(credits);
             transaction.setCarbonBalance(lastTransaction.get().getCarbonBalance()+credits);
             transactionService.create(transaction);
         }

         else{
             Transaction transaction = new Transaction();
             transaction.setAccountNumber(transactionRaw.getAccountNumber());
             transaction.setCategory(transactionRaw.getName());
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
             transaction.setDate(simpleDateFormat.format(new Date()));
             transaction.setTransactionType("Credit");
             transaction.setDescription(transactionRaw.getDescription());
             transaction.setTransactionId(new DateTime().getMillis());
             transaction.setCreditDebitCarbonAmount(credits);
             transaction.setCarbonBalance(credits);
             transactionService.create(transaction);
         }


    }

    private double getCarbonEquivalentValue(String skuID, int units, int category,String description) {
        double amount=0;
        if(category==4){
            long eThreshold=Long.parseLong(environment.getProperty("eThreshold"));
            long gThreshold=Long.parseLong(environment.getProperty("gThreshold"));
            if (description.contains("Electric")){
                if(eThreshold-units>0){
                 amount = (eThreshold-units)*skuMap.get(Long.parseLong(skuID));
                }
            }
            else if(gThreshold-units>0){
                amount = (gThreshold-units)*skuMap.get(Long.parseLong(skuID));
            }
        }
       else amount = skuMap.get(Long.parseLong(skuID))*units;

       return amount;

    }


}
