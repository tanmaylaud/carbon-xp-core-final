package com.db.carbonXP.evaluations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EvaluationMain {
    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(EvaluationMain.class, args);
        context.getBean(EvaluatorEngine.class).evaluate();
    }
}

