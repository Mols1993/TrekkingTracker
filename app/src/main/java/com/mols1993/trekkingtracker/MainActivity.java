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

public class MainActivity extends AppCompatActivity {

    ArrayAdapter aa;
    List<String> items = new ArrayList<>();
    Context mainContext;

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
        new FetchSQL().execute();
    }
    private class FetchSQL extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            String retval = "";
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                retval = e.toString();
            }
            String url = "jdbc:postgresql://plop.inf.udec.cl/PryI-1?user=pryi1&password=1p268q";
            Connection conn;
            try {
                DriverManager.setLoginTimeout(5);
                conn = DriverManager.getConnection(url);
                Statement st = conn.createStatement();
                String sql;
                sql = "SELECT * FROM trekkingtracker.ciudades";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()) {
                    retval = rs.getString("nombre");
                    items.add(retval);
                }
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                retval = e.toString();
            }
            return retval;
        }
        @Override
        protected void onPostExecute(String value) {
            Log.i("SQL", value);
            String[] ciudades = new String[items.size()];

            for(int i = 0; i < ciudades.length; i++){
                ciudades[i] = items.get(i);
            }

            Spinner ciudadeSpinner = (Spinner) findViewById(R.id.spinnerCiudades);
            ArrayAdapter aa = new ArrayAdapter(mainContext, android.R.layout.simple_spinner_item,items);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ciudadeSpinner.setAdapter(aa);
        }
    }
}
