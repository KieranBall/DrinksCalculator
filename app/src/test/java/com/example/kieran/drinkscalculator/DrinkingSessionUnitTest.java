package com.example.kieran.drinkscalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DrinkingSessionUnitTest {
    DrinkingSession session1 = new DrinkingSession(45000,true);
    DrinkingSession session2 = new DrinkingSession(95000,true);
    DrinkingSession session3 = new DrinkingSession(45000,false);
    DrinkingSession session4 = new DrinkingSession(95000,false);

    @Test
    public void check_weight() throws Exception {
        session1.weight = 55000;
        assertEquals(55000,session1.weight);
    }

    @Test
    public void check_gender() throws Exception {
        session1.isMale = false;
        assertEquals(false, session1.isMale);
        session1.isMale = true;
        assertEquals(true, session1.isMale);
    }

    @Test
    public void check_drink() throws Exception {
        session1.addDrink("1.0");
        assertEquals(1,session1.numDrinks);
    }

    @Test
    public void check_bac() throws Exception {
        session2.addDrink("2.0");
        session2.calculateBac();
        session3.addDrink("2.0");
        session3.calculateBac();
        session4.addDrink("2.0");
        session4.calculateBac();
        assertEquals("0.0310", session2.bac);
        assertEquals("0.0766", session3.bac);
        assertEquals("0.0363", session4.bac);
    }


}