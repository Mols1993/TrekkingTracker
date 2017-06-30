package com.mols1993.trekkingtracker;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cypher on 6/29/17.
 */

public class DB extends AsyncTask<String,Void,ResultSet> {
    Connection conn;

    public DB(){

    }

    @Override
    protected ResultSet doInBackground(String... query) {
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://plop.inf.udec.cl/PryI-1?user=pryi1&password=1p268q";
        try {
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(url);
            Statement st = null;
            st = conn.createStatement();rs = st.executeQuery(query[0]);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
