package com.db.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/mongo/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @RequestMapping(value = "/createTransaction",method = RequestMethod.POST)
    public String create(@RequestBody Transaction currentTransaction) {
        Optional<Transaction> previousTransaction=findLatestTransaction(currentTransaction.getAccountNumber());

        if(previousTransaction.isPresent()){
            updateTransaction(currentTransaction,previousTransaction.get());
        }
        currentTransaction.setTransactionId(new Date().getTime());
        transactionService.create(currentTransaction);

        return "Transaction is created.";
    }



    @GetMapping(value= "/getallTransactions")
    public Collection<Transaction> getAll() {
        return transactionService.findAll();
    }


    @GetMapping(value= "/getTransactionByAccountNumber/{account-number}")
    public Optional<Transaction> getByAccountNumber(@PathVariable(value= "account-number") String accountNumber) {
        return transactionService.findByAccountNumber(accountNumber).stream().findFirst();
    }

    @GetMapping(value= "/getLatestTransaction/{account-number}")
    public Optional<Transaction> getLatestTransactionByAccNum(@PathVariable(value= "account-number") String accountNumber) {
        return findLatestTransaction(accountNumber);
    }

    public Optional<Transaction> findLatestTransaction(String accountNumber){
        return transactionService.findByAccountNumber(accountNumber).stream().
                max(Comparator.comparing(Transaction::getTransactionId));
    }


    private void updateTransaction(Transaction currentTransaction,Transaction previousTransaction){
        System.out.println("inside update Transaction");
        currentTransaction.setCarbonBalance(currentTransaction.getCreditDebitCarbonAmount()+previousTransaction.getCarbonBalance());


    }

}
