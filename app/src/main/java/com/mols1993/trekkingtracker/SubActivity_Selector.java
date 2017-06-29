package com.mols1993.trekkingtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SubActivity_Selector extends AppCompatActivity {
    String ciudad, activ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__selector);

        Bundle extras = getIntent().getExtras();
        ciudad = extras.getString("ciudad");
        activ = extras.getString("actividad");


    }
}
