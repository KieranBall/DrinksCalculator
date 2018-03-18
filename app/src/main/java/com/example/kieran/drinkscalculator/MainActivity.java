package com.example.kieran.drinkscalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Calendar;

public class MainActivity extends Activity {
    DrinkingSession session = new DrinkingSession(75000, true);
    public int weight = 65000;
    public boolean isMale = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle unpackBundle = getIntent().getExtras();


        try {
            weight = unpackBundle.getInt("weight", weight);
            isMale = unpackBundle.getBoolean("isMale", isMale);
            System.out.println(weight);
            System.out.println(isMale);;
        }catch (Exception e){

        }
        session.weight = weight;
        session.isMale = isMale;





    }

    public void onSettingsClick(View view){
        Intent intent = new Intent(this, Settings.class);
        Bundle valuesBundle = new Bundle();
        valuesBundle.putInt("weight", session.weight);
        valuesBundle.putBoolean("gender", session.isMale);
        intent.putExtras(valuesBundle);
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
        System.out.println(session.weight);
        System.out.println(session.isMale);
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
        session.calculateBac();
        TextView bacDisplay = (TextView) findViewById(R.id.bac_display);
        String formatedBacDisplay = session.bac.substring(1, 5);
        bacDisplay.setText(formatedBacDisplay);

    }
}
