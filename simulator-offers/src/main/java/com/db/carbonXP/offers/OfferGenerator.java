package com.db.carbonXP.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class OfferGenerator {


    @Autowired
    OfferServiceImpl offerService;
    @Autowired
    Environment env;


    public  void runMe () throws InterruptedException, IOException {


        Random random = new Random();
        while(true){
            run(random.nextInt(10));
            Thread.sleep(1000*40);
        }
    }

    public  void run(int count){
        dumpOffersIntoDB(generateOffers(count));
    }

    public  void dumpOffersIntoDB(List<Offer> offers){
     offerService.insert(offers);
        System.out.println("Dumped offers into DB");
    }
    public  List<Offer> generateOffers(int count){
        Random random = new Random();
        int categoryIdx = random.nextInt(4)+1;
        List<String> categories= Arrays.asList(env.getProperty("category"+String.valueOf(categoryIdx)).split(","));
        List<String> companies= Arrays.asList(env.getProperty("companies"+String.valueOf(categoryIdx)).split(","));
        List<Offer> offers = new ArrayList<>();

        while(count>0){
            int companiesPtr = random.nextInt(companies.size());
            int catPtr = random.nextInt(categories.size());
            Offer offer = new Offer();
            offer.setOfferId(Math.floor(Math.random()*10000.0));
            offer.setOfferName(companies.get(companiesPtr));
            offer.setOfferDescription(categories.get(catPtr));
            offer.setOfferValue((categories.size()-catPtr)*50);
            offer.setCreditRating((categories.size()-catPtr)*100);
            offer.setValidity(new Date(2019,11,random.nextInt(28)+1));
            offers.add(offer);
            count--;
        }
        System.out.println("Generated offers");
        return offers;
    }

}
