package com.example.kieran.drinkscalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class MainActivity extends Activity {
    DrinkingSession session = new DrinkingSession(65000, true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        try {
            session = (DrinkingSession) intent.getSerializableExtra("object");
            TextView bacDisplay = (TextView) findViewById(R.id.bac_display);
            String formatedBacDisplay = session.bac.substring(1, 5);
            bacDisplay.setText(formatedBacDisplay);
            setTimesText();
            System.out.println("Try");

        } catch (Exception e){
            session = new DrinkingSession(65000, true);
            System.out.println("Catch");
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
}
