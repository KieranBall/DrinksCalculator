package com.example.kieran.drinkscalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DrinkingSessionUnitTest {


    @Test
    public void check_weight() throws Exception {
        DrinkingSession session1 = new DrinkingSession(45000,true);
        session1.setWeight(55000);
        assertEquals(55000,session1.weight);
    }

    @Test
    public void check_gender() throws Exception {
        DrinkingSession session1 = new DrinkingSession(45000,true);
        session1.setMale(false);
        assertEquals(false, session1.isMale);
        session1.setMale(true);
        assertEquals(true, session1.isMale);
    }

    @Test
    public void check_drink() throws Exception {
        DrinkingSession session1 = new DrinkingSession(45000,true);
        session1.addDrink("1.0");
        assertEquals(1,session1.numDrinks);
    }

    @Test
    public void check_bac() throws Exception {
        DrinkingSession session1 = new DrinkingSession(95000,true);
        DrinkingSession session2 = new DrinkingSession(45000,false);
        DrinkingSession session3 = new DrinkingSession(95000,false);
        session1.addDrink("2.0");
        session1.calculateBac();
        session2.addDrink("2.0");
        session2.calculateBac();
        session3.addDrink("2.0");
        session3.calculateBac();
        assertEquals("0.0310", session1.bac);
        assertEquals("0.0766", session2.bac);
        assertEquals("0.0363", session3.bac);
    }






}