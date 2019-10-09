package com.db.Transaction;

import java.util.List;

public interface TransactionService {

    public void create(Transaction transaction);

    public void update(Transaction transaction);

    public void delete(Transaction transaction);

    public void deleteAll();

    public Transaction find(Transaction transaction);

    public List<Transaction> findByAccountNumber(String accountNumber);


    public List <Transaction> findAll();
}
