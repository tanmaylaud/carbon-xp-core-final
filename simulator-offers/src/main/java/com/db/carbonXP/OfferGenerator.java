package com.db.carbonXP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class OfferGenerator {


    @Autowired
    OfferServiceImpl offerService;
    @Autowired
    Environment env;
    @Value("${category1}")
    String mansi;

    public  void runMe () throws InterruptedException, IOException {
        System.out.println(mansi);

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
        System.out.println(env);
        List<String> categories= Arrays.asList(env.getProperty("category"+String.valueOf(categoryIdx)).split(","));
        List<String> companies= Arrays.asList(env.getProperty("companies"+String.valueOf(categoryIdx)).split(","));
        List<Offer> offers = new ArrayList<>();

        while(count>0){
            int companiesPtr = random.nextInt(companies.size());
            int catPtr = random.nextInt(categories.size());
            Offer offer = new Offer();
            offer.setOfferId((int)Math.random());
            offer.setOfferName(companies.get(companiesPtr));
            offer.setOfferDescription(categories.get(catPtr));
            offer.setOfferValue((categories.size()-catPtr)*150);
            offer.setCreditRating((categories.size()-catPtr)*100);
            offer.setValidity(new Date(2019,11,random.nextInt(28)+1));
            offers.add(offer);
            count--;
        }
        System.out.println("Generated offers");
        return offers;
    }

}
