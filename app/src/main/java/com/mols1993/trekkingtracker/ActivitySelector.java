package com.mols1993.trekkingtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        trekking = (ImageButton) findViewById(R.id.trekkingButton);
        food = (ImageButton) findViewById(R.id.foodButton);
        rafting = (ImageButton) findViewById(R.id.raftingButton);
        kultrun = (ImageButton) findViewById(R.id.kultrunButton);

        trekking.setOnClickListener(new trekkingListener());
        food.setOnClickListener(new foodListener());
        rafting.setOnClickListener(new raftingListener());
        kultrun.setOnClickListener(new kultrunListener());
    }

    public void cualquiera(View v){
        ImageButton c = (ImageButton) v;
        Intent intent = new Intent(this, SubActivity_Selector.class);
        intent.putExtra("ciudad", ciudad);
        intent.putExtra("actividad",c.getContentDescription());
        startActivity(intent);
    }

    class trekkingListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(this, SubActivity_Selector.class);
            intent.putExtra("ciudad", ciudad);
            intent.putExtra("actividad","trekking");
            startActivity(intent);*/
        }

    }

    class foodListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(this, SubActivity_Selector.class);
            intent.putExtra("ciudad", ciudad);
            intent.putExtra("actividad","trekking");
            startActivity(intent);*/
        }

    }

    class raftingListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(this, SubActivity_Selector.class);
            intent.putExtra("ciudad", ciudad);
            intent.putExtra("actividad","trekking");
            startActivity(intent);*/
        }

    }
    class kultrunListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(this, SubActivity_Selector.class);
            intent.putExtra("ciudad", ciudad);
            intent.putExtra("actividad","trekking");
            startActivity(intent);*/
        }

    }

    //startActivity(new Intent(this, Actividad2.class)); pasar a otra actividad;
}