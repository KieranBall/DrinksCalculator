package com.example.kieran.drinkscalculator;

import java.math.BigDecimal;

public class DrinkingSession {
    public int weight;
    public boolean isMale;
    public BigDecimal bac = new BigDecimal("0.0");
    public BigDecimal totalStandardDrinks = new BigDecimal("0.0");
    public int numDrinks = 0;

    // number of drinks
    // standard drinks consumed


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

    public void setBac(BigDecimal bac) {
        this.bac = bac;
    }

    public BigDecimal getBac() {
        return bac;
    }

    public void calculateBac(){


    }

    public void addDrink(String standardDrink){
        numDrinks++;
        totalStandardDrinks = totalStandardDrinks.add(new BigDecimal(standardDrink));
        calculateBac();
    }

}
