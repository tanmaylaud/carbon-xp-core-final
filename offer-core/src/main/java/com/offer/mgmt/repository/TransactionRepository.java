package com.offer.mgmt.repository;

import com.offer.mgmt.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {
    List<Transaction> findByAccountNumber(String accountNumber);

}
