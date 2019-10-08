package com.db.Transaction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    @Query("{ 'accountNumber' : ?0 }")
    List<Transaction> findByAccountNumber(String accountNumber);

}
