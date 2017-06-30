package com.mols1993.trekkingtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivitySelector extends AppCompatActivity {
    ImageButton trekking, food, rafting, kultrun;
    String ciudad;
    TextView nombreCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        Bundle extras = getIntent().getExtras();
        ciudad = extras.getString("ciudad");

        nombreCiudad = (TextView) findViewById(R.id.nombreCiudad);
        nombreCiudad.setText(ciudad);
    }

    public void cualquiera(View v){

        ImageButton c = (ImageButton) v;
        Intent intent = new Intent(this, SubActivity_Selector.class);
        intent.putExtra("ciudad", ciudad);
        intent.putExtra("actividad",c.getContentDescription());
        startActivity(intent);
    }


}
