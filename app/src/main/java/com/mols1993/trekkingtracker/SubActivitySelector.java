package com.mols1993.trekkingtracker;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubActivitySelector extends AppCompatActivity {
    String ciudad, activ;
    DB db;
    ResultSet rs = null;
    List<String> actList = new ArrayList<>();
    List<String> descList = new ArrayList<>();
    List<String> idList = new ArrayList<>();
    LinearLayout scroll;
    ImageView mini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_selector);

        scroll = (LinearLayout) findViewById(R.id.scrollLayout);
        mini = (ImageView) findViewById(R.id.imageView3);

        Bundle extras = getIntent().getExtras();
        ciudad = extras.getString("ciudad");
        activ = extras.getString("actividad");

        imageSelect();

        TextView txtCiudad = (TextView) findViewById(R.id.txtCiudad);
        txtCiudad.setText(ciudad);

        db = new DB();

        LinearLayout auxLayout = new LinearLayout(this);
        auxLayout.setOrientation(LinearLayout.VERTICAL);
        auxLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        try {
            //Obtenemos la lista de sub-actividades que tiene la actividad seleccionada por el usuario.
            rs = db.execute(generaQuery()).get();
            if(db.conn != null) {
                String retval;
                while (rs.next()) {
                    retval = rs.getString("nombre");
                    actList.add(retval);
                    retval = rs.getString("descripcion");
                    descList.add(retval);
                    retval = rs.getString("id");
                    idList.add(retval);
                }
                String[] nombre = new String[actList.size()];
                String[] desc = new String[descList.size()];

                Log.v("Largo nombre", new Integer(nombre.length).toString());
                //Creamos u botón por cada sub-actividad
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = actList.get(i);
                    desc[i] = descList.get(i);
                    Button btnTag = new Button(this);
                    btnTag.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                    btnTag.setText(nombre[i] + "\n" + desc[i]);
                    btnTag.setId(Integer.parseInt(idList.get(i)));
                    btnTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cualquiera(v);
                        }
                    });
                    auxLayout.addView(btnTag);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        scroll.addView(auxLayout);

    }

    public void cualquiera(View v){
        Intent intent = new Intent(this, ActivityDetails.class);
        String id = String.valueOf(v.getId());
        intent.putExtra("idsub",id);
        intent.putExtra("tipoact",activ);
        startActivity(intent);
    }

    protected String generaQuery(){
        String q=null;
        q = "SELECT * from trekkingtracker."+ activ +" WHERE ciudad= '" + ciudad + "'";
        Log.v("query", q);
        return q;
    }

    protected  void imageSelect(){
        //Elige la imagen correcta para mostrar en la esquina superior derecha.
        if(activ.equals("trekking")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.trekking);
            mini.setImageDrawable(myDrawable);
        }else if(activ.equals("food")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.food);
            mini.setImageDrawable(myDrawable);
        }else if(activ.equals("rafting")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.rafting);
            mini.setImageDrawable(myDrawable);
        }else if(activ.equals("kultrun")){
            Drawable myDrawable = getResources().getDrawable(R.drawable.kultrun);
            mini.setImageDrawable(myDrawable);
        }
    }
}
