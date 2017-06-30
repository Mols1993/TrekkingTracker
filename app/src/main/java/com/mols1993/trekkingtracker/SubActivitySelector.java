package com.mols1993.trekkingtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.LinearLayout.LayoutParams;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubActivitySelector extends AppCompatActivity {
    String ciudad, activ;
    DB db;
    ResultSet rs = null;
    List<String> actList = new ArrayList<>();
    List<String> descList = new ArrayList<>();
    LinearLayout scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_selector);

        scroll = (LinearLayout) findViewById(R.id.scrollLayout);

        Bundle extras = getIntent().getExtras();
        ciudad = extras.getString("ciudad");
        activ = extras.getString("actividad");

        db = new DB();

        LinearLayout auxLayout = new LinearLayout(this);
        auxLayout.setOrientation(LinearLayout.VERTICAL);
        auxLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        try {
            rs = db.execute(generaQuery()).get();
            if(db.conn != null) {
                String retval;
                while (rs.next()) {
                    retval = rs.getString("nombre");
                    actList.add(retval);
                    retval = rs.getString("descripcion");
                    descList.add(retval);
                }
                String[] nombre = new String[actList.size()];
                String[] desc = new String[descList.size()];

                Log.v("Largo nombre", new Integer(nombre.length).toString());
                for (int i = 0; i < nombre.length; i++) {
                    nombre[i] = actList.get(i);
                    desc[i] = descList.get(i);
                    Button btnTag = new Button(this);
                    btnTag.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                    btnTag.setText(nombre[i] + "\n" + desc[i]);
                    btnTag.setId(i);
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

        Button c = (Button) v;
        Intent intent = new Intent(this, ActivityDetails.class);
        intent.putExtra("idsub",c.getId());
        intent.putExtra("tipoact",activ);
        startActivity(intent);
    }

    protected String generaQuery(){
        String q=null;
        q = "SELECT * from trekkingtracker."+ activ +" WHERE ciudad= '" + ciudad + "'";
        Log.v("query", q);
        return q;
    }
}
