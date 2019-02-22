package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ParametresCentripete extends AppCompatActivity {
    public static final String PARAMETRES = "param";
    public static double masse;
    public static double vitesse;
    public static double rayon;
    public double coef;
    public double friction;
    public double centripete;
    public boolean derap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_centripete);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.routes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

       final EditText tv1 = (EditText) findViewById(R.id.edittext1);
       final EditText tv2 = (EditText) findViewById(R.id.edittext2);
       final EditText tv3 = (EditText) findViewById(R.id.edittext3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                switch(pos)
                {
                    case 0: coef = 0.2;
                    break;
                    case 1: coef = 0.899;
                    break;
                    case 2: coef = 0.876;
                    break;
                    case 3: coef = 0.745;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masse = Double.parseDouble(tv1.getText().toString());
                vitesse = Double.parseDouble(tv2.getText().toString());
                rayon = Double.parseDouble(tv3.getText().toString());
                friction = masse*9.81*coef;
                centripete = (masse*Math.pow(vitesse,2))/rayon;
                if(friction < centripete) {
                    derap = true;
                }
                else {
                    derap = false;
                }
                Intent intent = new Intent(ParametresCentripete.this, Force_Centripete.class);
                intent.putExtra(PARAMETRES, derap);
                startActivity(intent);
            }
        });
    }
}
