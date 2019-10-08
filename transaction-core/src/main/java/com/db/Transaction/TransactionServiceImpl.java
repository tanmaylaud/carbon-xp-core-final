package com.db.Transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public void create(Transaction transaction) {

        Transaction transaction1=transactionRepository.insert(transaction);
        System.out.println("transaction created---"+transaction1);

    }

    @Override
    public void update(Transaction transaction) {
        Transaction transaction1 = transactionRepository.save(transaction);
        System.out.println("Updated:- " + transaction1);

    }

    @Override
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
        System.out.println("Deleted:- " + transaction.getAccountNumber());

    }

    @Override
    public void deleteAll() {
        transactionRepository.deleteAll();

    }

    @Override
    public Transaction find(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> findByAccountNumber(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
