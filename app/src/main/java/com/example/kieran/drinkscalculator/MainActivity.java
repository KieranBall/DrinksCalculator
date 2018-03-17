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

    String under029 = "Average individual appears normal";
    String under059 = "Concentration\nMild euphoria\nTalkativeness\nDecreased inhibition";
    String under099 = "Reasoning\nDepth perception\nPeripheral vision\nBlunted feelings\nEuphoria\nDisinhibition\nExtraversion";
    String under199 = "Reflex impairment\nReaction time\nGross motor control\nStaggering\nSlurred speech\nPossibility of nausea and vomiting";
    String under299 = "Severe motor impairment\nLoss of consciousness\nMemory blackout\nNausea\nVomiting\nEmotional swings\nPartial loss of understanding";
    String under399 = "Bladder function\nBreathing\nDysequilibrium\nStupor\nCentral nervous system depression\nLapses in and out of consciousness";
    String under500 = "Breathing\nHeart rate\nComa\nPositional alcohol nystagmus\nPossibility of death";
    String over500 = "Death";

    DrinkingSession session = new DrinkingSession(65000, true);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setEffectsText();
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
        session.addDrink(editTextString);
        session.calculateBac();
        TextView bacDisplay = (TextView) findViewById(R.id.bac_display);
        String formatedBacDisplay = session.bac.substring(0, 5);
        bacDisplay.setText(formatedBacDisplay);
        setEffectsText();
        setTimesText();

    }

    public void setEffectsText(){
        TextView effectsText = (TextView)findViewById(R.id.posible_effects_display);
        Double bac = Double.parseDouble(session.bac);
        if (bac == 0)effectsText.setText("-");
        else if (bac >= 0.001 && bac <= 0.029)effectsText.setText(under029);
        else if (bac >= 0.030 && bac <= 0.059)effectsText.setText(under059);
        else if (bac >= 0.060 && bac <= 0.099)effectsText.setText(under099);
        else if (bac >= 0.100 && bac <= 0.199)effectsText.setText(under199);
        else if (bac >= 0.200 && bac <= 0.299)effectsText.setText(under299);
        else if (bac >= 0.300 && bac <= 0.399)effectsText.setText(under399);
        else if (bac >= 0.400 && bac <= 0.500)effectsText.setText(under500);
        else if (bac > 0.500)effectsText.setText(over500);
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
        String formatedBacDisplay = session.bac.substring(0, 5);
        bacDisplay.setText(formatedBacDisplay);
        setEffectsText();
        setTimesText();

    }



}
