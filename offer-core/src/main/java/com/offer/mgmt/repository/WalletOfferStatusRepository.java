package com.offer.mgmt.repository;

import com.offer.mgmt.model.WalletOfferStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WalletOfferStatusRepository extends MongoRepository<WalletOfferStatus,String> {
    List<WalletOfferStatus> findByAccountNumber(String accountNumber);
}
