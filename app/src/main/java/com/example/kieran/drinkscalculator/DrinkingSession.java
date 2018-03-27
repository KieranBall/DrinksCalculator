package com.example.kieran.drinkscalculator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;

public class DrinkingSession implements Serializable {
    public int weight;
    public boolean isMale;
    public String bac = "0.000";
    public BigDecimal totalStandardDrinks = new BigDecimal("0.0");
    public int numDrinks = 0;
    public BigDecimal alcoholConsumedGrams;
    public Calendar timeFirstDrink;


    public DrinkingSession(int userWeight, boolean isUserMale) {
        this.weight = userWeight;
        this.isMale = isUserMale;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void addDrink(String standardDrink) {
        numDrinks++;
        totalStandardDrinks = totalStandardDrinks.add(new BigDecimal(standardDrink));

        if (numDrinks == 1) {
            timeFirstDrink = Calendar.getInstance();
        }
    }

    public void calculateBac() {
        alcoholConsumedGrams = totalStandardDrinks.multiply(new BigDecimal("10"));

        String genderConstant = "";

        if (isMale) {
            genderConstant = "0.68";
        } else if (!isMale) {
            genderConstant = "0.58";
        }

        BigDecimal weightTimesR = new BigDecimal(weight).multiply(new BigDecimal(genderConstant));

        alcoholConsumedGrams = totalStandardDrinks.multiply(new BigDecimal("10"));
        BigDecimal alcoholDivideWeight = alcoholConsumedGrams.divide(weightTimesR, 10, BigDecimal.ROUND_UP);
        BigDecimal grossBac = alcoholDivideWeight.multiply(new BigDecimal("100"));


        Calendar currentTime = Calendar.getInstance();

        long timeDifference = currentTime.getTimeInMillis() - timeFirstDrink.getTimeInMillis();
        long hoursDifference = (timeDifference / (1000 * 60 * 60)) % 24;

        BigDecimal elapsedTimeCalc = new BigDecimal(hoursDifference).multiply(new BigDecimal("0.015"));

        BigDecimal finalBac = grossBac.subtract(elapsedTimeCalc);

        BigDecimal rounded = finalBac.round(new MathContext(3, RoundingMode.HALF_UP));
        bac = rounded.toString();
    }

    public String calcTimeTill(String percentage) {

        BigDecimal controlBac = new BigDecimal(bac);
        int hours = 0;
        Calendar currentTime = Calendar.getInstance();

        while (true) {

            controlBac = controlBac.subtract(new BigDecimal("0.015"));

            int result = controlBac.compareTo(new BigDecimal(percentage));
            if (result == 0 || result == -1) {
                break;

            }
            hours++;
        }

        if (hours == 0) {
            double bacDouble = Double.parseDouble(bac);
            if (bacDouble >= 0.05) {

            } else {
                return "-";
            }

        }


        currentTime.add(Calendar.HOUR_OF_DAY, hours + 1);

        int hour24 = currentTime.get(Calendar.HOUR_OF_DAY);
        int hour12 = 0;
        String amPm = "am";

        if (hour24 == 0) {
            hour12 = 12;
            amPm = "am";
        } else if (hour24 < 12) {
            hour12 = hour24;
            amPm = "am";
        } else if (hour24 == 12) {
            hour12 = 12;
            amPm = "pm";
        } else if (hour24 > 12) {
            hour12 = hour24 - 12;
            amPm = "pm";
        }

        int rawMinute = currentTime.get(Calendar.MINUTE);
        String formattedMinute = Integer.toString(rawMinute);
        if (rawMinute < 10) {
            formattedMinute = String.format("0%d", rawMinute);
        }

        return String.format("%d:%s%s", hour12, formattedMinute, amPm);


    }


}
