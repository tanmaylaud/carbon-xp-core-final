package com.offer.mgmt.repository;

import com.offer.mgmt.model.Offer;


import com.offer.mgmt.model.WalletOfferStatus;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepository extends MongoRepository<Offer,String> {






}

