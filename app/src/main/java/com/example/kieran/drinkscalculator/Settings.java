package com.example.kieran.drinkscalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends Activity {
    public int weight = 65000;
    public boolean isMale = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final TextView weightLabel = (TextView) findViewById(R.id.weight_display);
        weightLabel.setText(Integer.toString(weight/1000));
        final SeekBar weightBar = (SeekBar)findViewById(R.id.weight_seek);
        final Spinner genderSpinner = (Spinner)findViewById(R.id.gender_select);
        weightBar.setProgress(weight/1000);
        weightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final TextView weightLabel = (TextView) findViewById(R.id.weight_display);
                weightLabel.setText(Integer.toString(progress));
                weight = progress*1000;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}});

        if (isMale) genderSpinner.setSelection(0);
        else if (!isMale) genderSpinner.setSelection(1);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String genderSelect = genderSpinner.getSelectedItem().toString();
                if (genderSelect.equals("Male"))isMale = true;
                else if (genderSelect.equals("Female"))isMale = false;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});
    }

    public void onSaveClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        Bundle intentBundle = new Bundle();
        intentBundle.putInt("weight", weight);
        intentBundle.putBoolean("ismale", isMale);
        intent.putExtras(intentBundle);
        startActivity(intent);
    }


}
