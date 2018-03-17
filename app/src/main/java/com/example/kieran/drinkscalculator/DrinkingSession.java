package com.example.kieran.drinkscalculator;

import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;

public class DrinkingSession {
    public int weight = 65000;
    public boolean isMale = false;
    public String bac = "0.0";
    public BigDecimal totalStandardDrinks = new BigDecimal("0.0");
    public int numDrinks = 0;
    public BigDecimal alcoholConsumedGrams;


    public DrinkingSession(int userWeight, boolean isUserMale){
        this.weight = userWeight;
        this.isMale = isUserMale;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMale(boolean male) {
        isMale = male;
    }


    public void addDrink(String standardDrink){
        numDrinks++;
        totalStandardDrinks = totalStandardDrinks.add(new BigDecimal(standardDrink));

        if (numDrinks == 1){

            Calendar currentTime = Calendar.getInstance();
            Calendar newTime = Calendar.getInstance();
            newTime.add(Calendar.HOUR_OF_DAY, 1);

            System.out.println(currentTime.get(Calendar.HOUR_OF_DAY));
            System.out.println(newTime.get(Calendar.HOUR_OF_DAY));




        }
    }

    public void calculateBac(){
        alcoholConsumedGrams = totalStandardDrinks.multiply(new BigDecimal("10"));

        String genderConstant = "";

        if (isMale){
            genderConstant = "0.68";
        }else if (!isMale){
            genderConstant = "0.58";
        }

        BigDecimal weightTimesR = new BigDecimal(weight).multiply(new BigDecimal(genderConstant));

        alcoholConsumedGrams = totalStandardDrinks.multiply(new BigDecimal("10"));
        BigDecimal alcoholDivideWeight = alcoholConsumedGrams.divide(weightTimesR,10,BigDecimal.ROUND_UP);
        BigDecimal finalBac = alcoholDivideWeight.multiply(new BigDecimal("100"));
        BigDecimal rounded = finalBac.round(new MathContext(3, RoundingMode.UP));
        bac = rounded.toString();
    }

}
