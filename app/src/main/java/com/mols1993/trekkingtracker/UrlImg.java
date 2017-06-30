package com.mols1993.trekkingtracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by cypher on 6/29/17.
 */

public class UrlImg extends AsyncTask<String, Void, Bitmap>{

    //Esta clase recibe una url de una imagen y devuelve un bitmap que la contiene, esto se ejecuta en una hebra secundaria.

    public UrlImg(){

    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }
}
