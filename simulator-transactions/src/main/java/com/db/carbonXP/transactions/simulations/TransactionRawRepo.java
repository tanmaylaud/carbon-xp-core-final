package com.db.carbonXP.transactions.simulations;

import com.db.carbonXP.transactions.model.TransactionRaw;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRawRepo extends MongoRepository<TransactionRaw,String> {
}

