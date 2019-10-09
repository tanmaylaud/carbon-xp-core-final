package com.db.carbonXP.transactions;

import java.util.Random;

public class RateCalculator{


    private static double currentRate = 70.000000;
    private static final Random generator = new Random();
    public static double random(double min, double max) {
    return min + (generator.nextDouble() * (max - min));
    }

    public static double getCurrentRate(){
        
        currentRate =currentRate + random(-1,1);
        return currentRate;
     }
}