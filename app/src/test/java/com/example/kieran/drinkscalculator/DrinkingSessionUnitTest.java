package com.example.kieran.drinkscalculator;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DrinkingSessionUnitTest {


    @Test
    public void checkWeight() throws Exception {
        DrinkingSession session1 = new DrinkingSession(45000,true);
        session1.setWeight(55000);
        assertEquals(55000,session1.getWeight());
    }

    @Test
    public void checkGender() throws Exception {
        DrinkingSession session1 = new DrinkingSession(45000,true);
        session1.setMale(false);
        assertEquals(false, session1.getIsMale());
        session1.setMale(true);
        assertEquals(true, session1.getIsMale());
    }

    @Test
    public void checkDrink() throws Exception {
        DrinkingSession session1 = new DrinkingSession(45000,true);
        session1.addDrink("1.0");
        assertEquals(1,session1.getNumDrinks());
    }

    @Test
    public void checkBac() throws Exception {
        DrinkingSession session1 = new DrinkingSession(95000,true);
        DrinkingSession session2 = new DrinkingSession(45000,false);
        DrinkingSession session3 = new DrinkingSession(95000,false);
        session1.addDrink("2.0");
        session1.calculateBac();
        session2.addDrink("2.0");
        session2.calculateBac();
        session3.addDrink("2.0");
        session3.calculateBac();
        assertEquals("0.0310", session1.getBac());
        assertEquals("0.0766", session2.getBac());
        assertEquals("0.0363", session3.getBac());
    }

    @Test
    public void checkTotalDrinks(){
        DrinkingSession session1 = new DrinkingSession(95000,true);
        session1.addDrink("1.0");
        session1.addDrink("2.0");
        session1.addDrink("1.5");
        assertEquals(new BigDecimal("4.5"), session1.getTotalStandardDrinks());
    }

    @Test
    public void checkAlcohol(){
        DrinkingSession session1 = new DrinkingSession(95000,true);
        session1.addDrink("1.0");
        session1.calculateBac();

        DrinkingSession session2 = new DrinkingSession(95000,true);
        session2.addDrink("2.0");
        session2.calculateBac();

        DrinkingSession session3 = new DrinkingSession(95000,true);
        session3.addDrink("5.0");
        session3.calculateBac();

        assertEquals(new BigDecimal("10.0"), session1.getAlcoholConsumedGrams());
        assertEquals(new BigDecimal("20.0"), session2.getAlcoholConsumedGrams());
        assertEquals(new BigDecimal("50.0"), session3.getAlcoholConsumedGrams());


    }

    @Test
    public void checkTimeFirstDrink(){
        DrinkingSession session1 = new DrinkingSession(95000,true);
        session1.addDrink("1.0");
        DrinkingSession session2 = new DrinkingSession(95000,true);

        assertTrue(session1.getTimeFirstDrink() != null);
        assertTrue(session2.getTimeFirstDrink() == null);
    }

    @Test
    public void checkTestCase1(){
        DrinkingSession session1 = new DrinkingSession(65000,true);
        session1.addDrink("1.2");
        session1.calculateBac();
        session1.addDrink("2.4");
        session1.calculateBac();
        session1.addDrink("1.6");
        session1.calculateBac();
        session1.addDrink("0.4");
        session1.calculateBac();
        session1.addDrink("0.8");
        session1.calculateBac();


        System.out.println(session1.getBac());
        System.out.println(session1.getAlcoholConsumedGrams());
        System.out.println(session1.getNumDrinks());
        System.out.println(session1.getTotalStandardDrinks());

        assertEquals("0.145", session1.getBac());
        assertEquals(new BigDecimal("64.0"), session1.getAlcoholConsumedGrams());
        assertEquals(5, session1.getNumDrinks());
        assertEquals(new BigDecimal("6.4"), session1.getTotalStandardDrinks());

    }

    @Test
    public void checkTestCase2(){
        DrinkingSession session1 = new DrinkingSession(65000,true);
        session1.addDrink("3.0");
        session1.calculateBac();
        session1.addDrink("1.4");
        session1.calculateBac();
        session1.addDrink("2.3");
        session1.calculateBac();


        System.out.println(session1.getBac());
        System.out.println(session1.getAlcoholConsumedGrams());
        System.out.println(session1.getNumDrinks());
        System.out.println(session1.getTotalStandardDrinks());

        assertEquals("0.152", session1.getBac());
        assertEquals(new BigDecimal("67.0"), session1.getAlcoholConsumedGrams());
        assertEquals(3, session1.getNumDrinks());
        assertEquals(new BigDecimal("6.7"), session1.getTotalStandardDrinks());

    }

    @Test
    public void checkTestCase3(){
        DrinkingSession session1 = new DrinkingSession(65000,true);
        session1.addDrink("1.2");
        session1.calculateBac();
        session1.addDrink("2.4");
        session1.calculateBac();
        session1.addDrink("1.6");
        session1.calculateBac();
        session1.addDrink("1.4");
        session1.calculateBac();
        session1.addDrink("1.8");
        session1.calculateBac();
        session1.addDrink("2.2");
        session1.calculateBac();
        session1.addDrink("1.4");
        session1.calculateBac();
        session1.addDrink("2.7");
        session1.calculateBac();
        session1.addDrink("1.8");
        session1.calculateBac();



        System.out.println(session1.getBac());
        System.out.println(session1.getAlcoholConsumedGrams());
        System.out.println(session1.getNumDrinks());
        System.out.println(session1.getTotalStandardDrinks());

        assertEquals("0.373", session1.getBac());
        assertEquals(new BigDecimal("165.0"), session1.getAlcoholConsumedGrams());
        assertEquals(9, session1.getNumDrinks());
        assertEquals(new BigDecimal("16.5"), session1.getTotalStandardDrinks());

    }





}