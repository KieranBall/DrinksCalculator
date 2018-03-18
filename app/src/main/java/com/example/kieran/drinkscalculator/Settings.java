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
    public int weight = 65000;
    public boolean isMale = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        DrinkingSession testSer = (DrinkingSession) intent.getSerializableExtra("object");
        System.out.println(testSer.bac);





    }

    public void onSaveClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
