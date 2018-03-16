package com.example.kieran.drinkscalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String testString = "Nausea\nVomiting\nSevere motor impairment\nLoss of consciousness\nMemory blackout";
        TextView test = (TextView) findViewById(R.id.posible_effects_display);
        test.setText(testString);

    }

    public void onSettingsClick(View view){
        Intent intent = new Intent(this, Settings.class);
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
        int newDrink = Integer.parseInt(editTextString);




    }


}
