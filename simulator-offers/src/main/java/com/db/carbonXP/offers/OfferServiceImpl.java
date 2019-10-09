package com.db.carbonXP.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferServiceImpl  {

    @Autowired
    OfferRepository offerRepository;

    public void insert(List<Offer> offers){
        offerRepository.insert(offers);
    }
}
