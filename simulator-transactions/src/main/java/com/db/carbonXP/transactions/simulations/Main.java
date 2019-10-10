package com.db.carbonXP.transactions.simulations;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;

@SpringBootApplication
public class Main {


    public static void main(String[] args) throws FileNotFoundException, ServiceBusException, InterruptedException {
      SpringApplication.run(Main.class, args);

    }
}
