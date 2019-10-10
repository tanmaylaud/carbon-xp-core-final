package com.db.carbonXP.transactions.simulations;

import com.db.carbonXP.transactions.model.TransactionRaw;
import com.google.gson.Gson;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@EnableScheduling
@Component
public class Simulator {

    QueueClient sendClient;

    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    Environment environment;

    @Autowired
    UserServiceImpl userService;

    int counter=0;

    @Scheduled(fixedRate = 40000)
    public void generateData() throws FileNotFoundException, InterruptedException, ServiceBusException {

        sendClient = new QueueClient(new ConnectionStringBuilder(environment.getProperty("queueConnection"), environment.getProperty("queueName")), ReceiveMode.PEEKLOCK);
        try{

                List finalTransList= generateAndDumpData();
                publishtoQueue(finalTransList);
               // sendClient.close();
          }
        catch (Exception e){
            System.out.println(e.getMessage());
          sendClient.close();
        }


    }

    private void publishtoQueue(List finalTransList) throws ServiceBusException, InterruptedException {
        sendMessagesAsync(sendClient,finalTransList).thenRunAsync(() -> sendClient.closeAsync());
        System.out.println("Sent messages");
        System.out.println("Waiting for next event...");
    }

    private CompletableFuture<Void> sendMessagesAsync(QueueClient sendClient, List data) {
         Gson gson = new Gson();
        List<CompletableFuture> tasks = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            final String messageId = String.valueOf(counter);
            counter++;
            Message message = new Message(gson.toJson(data.get(i),TransactionRaw.class).getBytes(StandardCharsets.UTF_8));
            message.setContentType("application/json");
            message.setLabel("TransactionRAW");
            message.setMessageId(messageId);
            System.out.printf("\nMessage sending: Id = %s", message.getMessageId());
            tasks.add(
                    sendClient.sendAsync(message).thenRunAsync(() -> {
                        System.out.printf("\n\tMessage acknowledged: Id = %s", message.getMessageId());
                    }));
        }
        return CompletableFuture.allOf(tasks.toArray(new CompletableFuture<?>[tasks.size()]));
    }

    private List<TransactionRaw> generateAndDumpData() throws IOException {
        Random  random = new Random();
        List<TransactionRaw> transactionRawList = getAllTransactions();
       // List<String> accounts = Arrays.asList(environment.getProperty("accounts").split(","));
         List<String> accounts =new ArrayList<>();
         userService.findAll().forEach(user-> accounts.add(user.getAccountNumber()));

        List<TransactionRaw> finalTransactionList = new ArrayList<>();
        for(int j =0; j<accounts.size();j++) {
            int count = random.nextInt(2)+1;
            for (int i = 0; i < count; i++) {
                TransactionRaw transactionRaw = transactionRawList.get(random.nextInt(transactionRawList.size()));
                if(transactionRaw.getCategory()==4){
                    transactionRaw.setUnits(transactionRaw.getUnits()+random.nextInt(1000)+1);
                }
                if(transactionRaw.getCategory()==3){
                    transactionRaw.setUnits(1);
                }
                transactionRaw.setAccountNumber(accounts.get(j));
                finalTransactionList.add(transactionRaw);
            }
        }
        transactionService.insert(finalTransactionList);
        return finalTransactionList;
    }

    private List<TransactionRaw> getAllTransactions() throws IOException {

        BufferedReader reader;
        //File file = ResourceUtils.getFile("classpath:transactions.csv");
        ClassPathResource classPathResource = new ClassPathResource("transactions.csv");


        InputStream inputStream =classPathResource.getInputStream();

        List<TransactionRaw> transactionRawList = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String line = reader.readLine();
            line = reader.readLine();
            Random random = new Random();

            while (line != null) {
                System.out.println(line);
                TransactionRaw transactionRaw = new TransactionRaw();
                String[] data = line.split(",");
                transactionRaw.setCategory(Integer.parseInt(data[0]));
                transactionRaw.setSkuID(data[1]);
                transactionRaw.setName(data[2]);
                transactionRaw.setDescription(data[3]);
                transactionRaw.setUnits(random.nextInt(5) + 1);

                transactionRaw.setDate(new Date());
                transactionRawList.add(transactionRaw);
                line = reader.readLine();
            }
            reader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
       return  transactionRawList;
    }

}
