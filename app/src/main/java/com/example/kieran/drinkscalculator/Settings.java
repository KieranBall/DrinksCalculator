package com.example.kieran.drinkscalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends Activity {
    DrinkingSession session;
    float initialX, initialY;
    String weightKey = "com.kieran.drinkscalculator.weight";
    String genderKey = "com.kieran.drinkscalculator.gender";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // create shared preferences
        preferences = this.getSharedPreferences(
                "com.kieran.drinkscalculator", Context.MODE_PRIVATE);


        Intent intent = getIntent();
        session = (DrinkingSession) intent.getSerializableExtra("object");


        SeekBar weightSeek = findViewById(R.id.weight_seek);
        weightSeek.setProgress(session.getWeight() / 1000);
        TextView weightLabel = findViewById(R.id.weight_display);
        weightLabel.setText(String.format("%s", session.getWeight() / 1000));
        Spinner genderSpin = findViewById(R.id.gender_select);

        if (session.getIsMale()) genderSpin.setSelection(0);
        else if (!session.getIsMale()) genderSpin.setSelection(1);


        weightSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView weightLabel = findViewById(R.id.weight_display);
                String label = Integer.toString(progress);
                weightLabel.setText(label);
                session.setWeight(progress * 1000);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        genderSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner genderSpin = findViewById(R.id.gender_select);
                String gender = genderSpin.getSelectedItem().toString();
                if (gender.equals("Male")) session.setMale(true);
                else if (gender.equals("Female")) session.setMale(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void onSaveClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("object", session);
        startActivity(intent);
    }

    public void onResetSessionClick(View view) {
        int saveWeight = session.getWeight();
        boolean saveIsMale = session.getIsMale();

        session = new DrinkingSession(saveWeight, saveIsMale);
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

                if (initialX < finalX) {
                    Intent intent = new Intent(this, MainActivity.class);
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

}
