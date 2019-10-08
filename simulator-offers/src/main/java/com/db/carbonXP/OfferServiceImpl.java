package com.db.carbonXP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
