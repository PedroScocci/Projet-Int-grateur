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
import android.widget.Toast;


import java.util.List;
import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Paradoxe2 extends AppCompatActivity {
    public static double age_nombre ;
    public static double vitesse_nombre;
    public static double temps;
    public static String dest;
    public static double temps_dilate, temps_dilatea, temps_dilater;
    private Spinner sItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxe2);

        /*=====================Spinner=======================*/
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Jupiter");
        spinnerArray.add("Saturne");
        spinnerArray.add("Mars");
        spinnerArray.add("Galaxie d'Andremede");
        spinnerArray.add("Pluton");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        /*=======================Fin Spinner=======================*/


        Button but = (Button) findViewById(R.id.paradoxe2);

        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText age = (EditText) findViewById(R.id.age);
                EditText vitesse = (EditText) findViewById(R.id.vitesse);
                //age_nombre = Double.parseDouble(age.toString());
                try{
                    vitesse_nombre = Double.valueOf(vitesse.getText().toString());
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }
                addSpinerListener();
                Toast.makeText(Paradoxe2.this, String.valueOf(temps), Toast.LENGTH_SHORT).show();
                temps_dilatea = temps*(sqrt((1-((vitesse_nombre/100))/(1+((vitesse_nombre/100))))));
                temps_dilater = temps*(sqrt((1+((vitesse_nombre/100))/(1-((vitesse_nombre/100))))));
                //Toast.makeText(Paradoxe2.this, String.valueOf(temps_dilatea), Toast.LENGTH_SHORT).show();
                //temps_dilate = temps_dilatea+temps_dilater;
                TextView txt = (TextView) findViewById(R.id.vv) ;
                txt.setText(String.valueOf(temps_dilatea));
                //Intent myIntent = new Intent(Paradoxe2.this, Paradoxe3.class);
                //startActivity(myIntent);
            }
        });
    }
    public void addSpinerListener(){
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dest = parent.getItemAtPosition(position).toString();
                switch (dest){
                    case "Jupiter":
                        temps = ((41.32*pow(10,15))/((vitesse_nombre*(3*pow(10,8)))/100))/31536000;
                        break;
                    case "Saturne":
                        temps = (((vitesse_nombre*(3*pow(10,8)))/100)*3.6*1350000000);
                        break;
                    case "Mars":
                        temps = (((vitesse_nombre*(3*pow(10,8)))/100)*3.6*56000000);
                        break;
                    case "Galaxie d'Andremede":
                        temps = (((vitesse_nombre*(3*pow(10,8)))/100)*3.6*(2.401*pow(10,19)));
                        break;
                    case "Pluton":
                        temps = (((vitesse_nombre*(3*pow(10,8)))/100)*3.6*(5.766*pow(10,9)));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
