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

import java.math.BigDecimal;

public class MainActivity extends Activity {
    DrinkingSession session = new DrinkingSession(65000, true);
    float initialX, initialY;
    String weightKey = "com.kieran.drinkscalculator.weight";
    String genderKey = "com.kieran.drinkscalculator.gender";
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create shared preferences
        preferences = this.getSharedPreferences(
                "com.kieran.drinkscalculator", Context.MODE_PRIVATE);


        // Get DrinkingSession "session" or create new one
        if (savedInstanceState == null) {

            // Get session from intent
            Intent intent = getIntent();
            try {
                session = (DrinkingSession) intent.getSerializableExtra("object");
                TextView bacDisplay = findViewById(R.id.bac_display);
                String formatedBacDisplay = session.getBac().substring(1, 5);
                bacDisplay.setText(formatedBacDisplay);
                setTimesText();
            } catch (Exception e) {
                int weight = preferences.getInt(weightKey, 65000);
                boolean isMale = preferences.getBoolean(genderKey, true);
                session = new DrinkingSession(weight, isMale);

            }
        } else {
            // Get session from save instance
            session = (DrinkingSession) savedInstanceState.getSerializable("object");
            TextView bacDisplay = findViewById(R.id.bac_display);
            String formatedBacDisplay = session.getBac().substring(1, 5);
            bacDisplay.setText(formatedBacDisplay);
            setTimesText();
        }
    }


    public void onSettingsClick(View view) {
        // Starts the settings activity
        // Used when the settings button is clicked
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("object", session);
        startActivity(intent);
    }


    public void onPlusClick(View view) {
        // Adds .1 to the standard drinks when the plus button is clicked
        EditText editText = findViewById(R.id.drinkUnits);
        String editTextString = editText.getText().toString();
        if (Double.parseDouble(editTextString) < 5.0){
            BigDecimal drinksNumber = new BigDecimal(editTextString);
            BigDecimal newDrinksNumber = drinksNumber.add(new BigDecimal("0.1"));
            editText.setText(String.format("%s", newDrinksNumber));
        }
    }


    public void onMinusClick(View view) {
        // Subtracts .1 from the standard drinks when the plus button is clicked
        EditText editText = findViewById(R.id.drinkUnits);
        String editTextString = editText.getText().toString();
        if (Double.parseDouble(editTextString) > 0.1) {
            BigDecimal drinksNumber = new BigDecimal(editTextString);
            BigDecimal newDrinksNumber = drinksNumber.subtract(new BigDecimal("0.1"));
            editText.setText(String.format("%s", newDrinksNumber));
        }
    }


    public void onAddDrinkClick(View view) {
        // Runs the addDrink and calculateBac function from DrinkingSession
        // Updates the labels on the layout
        EditText editText = findViewById(R.id.drinkUnits);
        String editTextString = editText.getText().toString();
        if (Double.parseDouble(editTextString) >= 0.1 && Double.parseDouble(editTextString) <= 5.0) {
            session.addDrink(editTextString);
            session.calculateBac();
            TextView bacDisplay = findViewById(R.id.bac_display);
            String formatedBacDisplay = session.getBac().substring(1, 5);
            bacDisplay.setText(formatedBacDisplay);
            setTimesText();
        }else {
            editText.setText("1.0");
        }



    }


    public void setTimesText() {
        // Runs the calcTimeTill function from DrinkingSession
        // Updates the time labels on the layout
        String soberTime = session.calcTimeTill("0.0");
        TextView soberTimeDisplay = findViewById(R.id.time_until_sober_display);
        soberTimeDisplay.setText(soberTime);

        String driveTime = session.calcTimeTill("0.05");
        TextView driveTimeDisplay = findViewById(R.id.time_until_drive_display);
        driveTimeDisplay.setText(driveTime);

    }


    public void onRefreshClick(View view) {
        // Recalculates the Bac and updates the layout
        if (Double.parseDouble(session.getBac()) > 0.0) {
            session.calculateBac();
            TextView bacDisplay = findViewById(R.id.bac_display);
            String formatedBacDisplay = session.getBac().substring(1, 5);
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
        preferences.edit().putInt(weightKey, session.getWeight()).apply();
        preferences.edit().putBoolean(genderKey, session.getIsMale()).apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        preferences.edit().putInt(weightKey, session.getWeight()).apply();
        preferences.edit().putBoolean(genderKey, session.getIsMale()).apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences.edit().putInt(weightKey, session.getWeight()).apply();
        preferences.edit().putBoolean(genderKey, session.getIsMale()).apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("object", session);
    }
}
