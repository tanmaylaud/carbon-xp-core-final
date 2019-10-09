package com.db.carbonXP.offers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends MongoRepository<Offer,String> {
}
