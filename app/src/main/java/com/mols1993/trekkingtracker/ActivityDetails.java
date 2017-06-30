package com.mols1993.trekkingtracker;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class ActivityDetails extends AppCompatActivity {

    String ciudad = "", lugar = "";
    TextView nombreCiudad, nombreLugar, infoLugar, dificultadLugar;
    ImageView imgDestino;
    LinearLayout descLayout;
    ResultSet rs = null;
    Bitmap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nombreCiudad = (TextView) findViewById(R.id.nombreCiudad);
        nombreLugar = (TextView) findViewById(R.id.nombreLugar);
        infoLugar = (TextView) findViewById(R.id.infoLugar);
        dificultadLugar = (TextView) findViewById(R.id.dificultadLugar);
        imgDestino = (ImageView) findViewById(R.id.imgDestino);
        descLayout = (LinearLayout) findViewById(R.id.descLayout);

        DB db = new DB();
        try {
            rs = db.execute("Select * FROM trekkingtracker.trekking WHERE ciudad='Ciudad 1'").get();
            rs.next();
            String urlFoto = null;
            String urlMapa = null;
            urlFoto = rs.getString("foto");
            urlMapa = rs.getString("mapa");
            UrlImg urlImg = new UrlImg();
            UrlImg urlImgMapa = new UrlImg();
            Bitmap bmp = urlImg.execute(urlFoto).get();
            mapa = urlImgMapa.execute(urlMapa).get();
            imgDestino.setImageBitmap(bmp);
            nombreCiudad.setText(rs.getString("ciudad"));
            nombreLugar.setText(rs.getString("nombre"));
            infoLugar.setText("Información: " + rs.getString("descripcion"));
            dificultadLugar.setText("Dificultad: " + rs.getString("dificultad"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickFlora(View v){
        TextView txt = new TextView(this);
        try {
            txt.setText(rs.getString("flora"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        descLayout.removeAllViews();
        descLayout.addView(txt);
    }
    public void clickFauna(View v){
        TextView txt = new TextView(this);
        try {
            txt.setText(rs.getString("fauna"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        descLayout.removeAllViews();
        descLayout.addView(txt);
    }
    public void clickHistoria(View v){
        TextView txt = new TextView(this);
        try {
            txt.setText(rs.getString("datosHistoricos"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        descLayout.removeAllViews();
        descLayout.addView(txt);
    }
    public void clickMapa(View v){
        ImageView img = new ImageView(this);
        img.setImageBitmap(mapa);
        descLayout.removeAllViews();
        descLayout.addView(img);
    }
}
