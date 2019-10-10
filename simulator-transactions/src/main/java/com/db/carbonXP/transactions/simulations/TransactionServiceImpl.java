package com.db.carbonXP.transactions.simulations;

import com.db.carbonXP.transactions.model.TransactionRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionServiceImpl {
    @Autowired
    TransactionRawRepo transactionRawRepo;

    public void insert(List<TransactionRaw> offers){
        transactionRawRepo.insert(offers);
    }
}
