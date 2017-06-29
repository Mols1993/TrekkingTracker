package com.mols1993.trekkingtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SubActivitySelector extends AppCompatActivity {
    String ciudad, activ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_selector);

        Bundle extras = getIntent().getExtras();
        ciudad = extras.getString("ciudad");
        activ = extras.getString("actividad");


    }
}
