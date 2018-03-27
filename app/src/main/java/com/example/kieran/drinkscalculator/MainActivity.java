package com.example.kieran.drinkscalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class MainActivity extends Activity {
    DrinkingSession session = new DrinkingSession(65000, true);
    float initialX, initialY;
    String weightKey ="com.kieran.drinkscalculator.weight";
    String genderKey = "com.kieran.drinkscalculator.gender";
    SharedPreferences prefrences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefrences = this.getSharedPreferences(
                "com.kieran.drinkscalculator", Context.MODE_PRIVATE);


        Intent intent = getIntent();

        try {
            session = (DrinkingSession) intent.getSerializableExtra("object");
            TextView bacDisplay = (TextView) findViewById(R.id.bac_display);
            String formatedBacDisplay = session.bac.substring(1, 5);
            bacDisplay.setText(formatedBacDisplay);
            setTimesText();

        } catch (Exception e){

            int weight = prefrences.getInt(weightKey, 65000);
            boolean isMale = prefrences.getBoolean(genderKey, true);
            session = new DrinkingSession(weight, isMale);
            System.out.println(weight);
            System.out.println(isMale);

        }





    }

    public void onSettingsClick(View view){
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("object", session);
        startActivity(intent);
    }

    public void  onPlusClick(View view){
        EditText plusButton = (EditText) findViewById(R.id.drinkUnits);
        String editTextString = plusButton.getText().toString();
        BigDecimal drinksNumber = new BigDecimal(editTextString);
        BigDecimal newDrinksNumber = drinksNumber.add(new BigDecimal("0.1"));
        plusButton.setText(newDrinksNumber.toString());
    }

    public void  onMinusClick(View view){
        EditText plusButton = (EditText) findViewById(R.id.drinkUnits);
        String editTextString = plusButton.getText().toString();
        BigDecimal drinksNumber = new BigDecimal(editTextString);
        BigDecimal newDrinksNumber = drinksNumber.subtract(new BigDecimal("0.1"));
        plusButton.setText(newDrinksNumber.toString());
    }

    public void onAddDrinkClick(View view){
        EditText plusButton = (EditText) findViewById(R.id.drinkUnits);
        String editTextString = plusButton.getText().toString();
        session.addDrink(editTextString);
        session.calculateBac();
        TextView bacDisplay = (TextView) findViewById(R.id.bac_display);
        String formatedBacDisplay = session.bac.substring(1, 5);
        bacDisplay.setText(formatedBacDisplay);
        setTimesText();

    }

    public void setTimesText(){

        String soberTime = session.calcTimeTill("0.0");
        TextView soberTimeDisplay = (TextView) findViewById(R.id.time_until_sober_display);
        soberTimeDisplay.setText(soberTime);

        String driveTime = session.calcTimeTill("0.05");
        TextView driveTimeDisplay = (TextView) findViewById(R.id.time_until_drive_display);
        driveTimeDisplay.setText(driveTime);

    }



    public void onRefreshClick(View view){

        if (Double.parseDouble(session.bac) > 0.0) {
            session.calculateBac();
            TextView bacDisplay = (TextView) findViewById(R.id.bac_display);
            String formatedBacDisplay = session.bac.substring(1, 5);
            bacDisplay.setText(formatedBacDisplay);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                initialY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                float finalY = event.getY();

                if (initialX > finalX) {

                    Intent intent = new Intent(this, Settings.class);
                    intent.putExtra("object", session);
                    startActivity(intent);
                }
                break;

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("destory");
        prefrences.edit().putInt(weightKey, session.weight).apply();
        prefrences.edit().putBoolean(genderKey, session.isMale).apply();
    }
}
