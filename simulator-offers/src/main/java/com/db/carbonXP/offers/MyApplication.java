package com.db.carbonXP.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = SpringApplication.run(MyApplication.class, args);
        context.getBean(OfferGenerator.class).runMe();
    }
   /* @Bean
    public void run() throws IOException, InterruptedException {
      OfferGenerator.runMe();
    }*/
}
