package com.example.kieran.drinkscalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

public class Settings extends Activity {
    DrinkingSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        session = (DrinkingSession) intent.getSerializableExtra("object");


        final TextView weightLabel = (TextView) findViewById(R.id.weight_display);

        SeekBar weightSeek = (SeekBar)findViewById(R.id.weight_seek);
        weightSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String label = Integer.toString(progress);
                weightLabel.setText(label);
                session.weight = progress*1000;

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}});


        final Spinner genderSpin = (Spinner) findViewById(R.id.gender_select);
        genderSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = genderSpin.getSelectedItem().toString();
                if (gender.equals("Male"))session.setMale(true);
                else if (gender.equals("Female"))session.setMale(false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});





    }

    public void onSaveClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("object", session);
        startActivity(intent);
    }

    public void onResetSessionClick(View view){
        session = new DrinkingSession(session.weight,session.isMale);
    }


}
