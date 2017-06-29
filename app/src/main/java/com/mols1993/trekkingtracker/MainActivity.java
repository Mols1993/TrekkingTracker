package com.mols1993.trekkingtracker;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter aa;
    List<String> items = new ArrayList<>();
    Context mainContext;
    DB db;
    ResultSet rs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("DB", "PostgreSQL Connect Example.");
        Connection conn = null;
        String url = "jdbc:postgresql://http://plop.inf.udec.cl/";
        String dbName = "PryI-1";
        String driver = "org.postgresql.Driver";
        String userName = "pryi1";
        String password = "1p268q";
        mainContext = this;
        db = new DB();
        try {
            rs = db.execute("SELECT * FROM trekkingtracker.ciudades").get();
            if(db.conn != null) {
                String retval;
                while (rs.next()) {
                    retval = rs.getString("nombre");
                    items.add(retval);
                }
                String[] ciudades = new String[items.size()];

                for (int i = 0; i < ciudades.length; i++) {
                    ciudades[i] = items.get(i);
                }

                Spinner ciudadeSpinner = (Spinner) findViewById(R.id.spinnerCiudades);
                ArrayAdapter aa = new ArrayAdapter(mainContext, android.R.layout.simple_spinner_item, items);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ciudadeSpinner.setAdapter(aa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
