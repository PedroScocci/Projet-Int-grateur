package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.net.Uri;
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
    public static double temps_dilate, temps_dilatea, temps_dilater, temps_normal;
    private Spinner sItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxe2);

        /*=====================Spinner=======================*/
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Alpha Centauri C");
        spinnerArray.add("Étoile Polaire");
        spinnerArray.add("Sirius");
        spinnerArray.add("Galaxie d'Andromède");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        /*=======================Fin Spinner=======================*/


        Button but = (Button) findViewById(R.id.paradoxe2);

        addSpinerListener();

        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText age = (EditText) findViewById(R.id.age);
                EditText vitesse = (EditText) findViewById(R.id.vitesse);

                try{
                    vitesse_nombre = Double.valueOf(vitesse.getText().toString());
                    age_nombre = Double.parseDouble(age.getText().toString());
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }

                if(vitesse_nombre > 0 && vitesse_nombre < 100) {
                    calculerTemps();

                    temps_dilatea = temps*(sqrt((1-((vitesse_nombre/100))/(1+((vitesse_nombre/100))))));
                    temps_dilater = temps*(sqrt((1+((vitesse_nombre/100))/(1-((vitesse_nombre/100))))));

                    temps_dilate = temps_dilatea + temps_dilater + age_nombre;
                    temps_normal = (2*temps) + age_nombre;

                    Intent myIntent = new Intent(Paradoxe2.this, Paradoxe3.class);
                    myIntent.putExtra("ageN", String.valueOf(temps_normal));
                    myIntent.putExtra("ageD", String.valueOf(temps_dilate));
                    startActivity(myIntent);
                }
                else if(vitesse_nombre <=0) {
                    Toast.makeText(Paradoxe2.this, "La vitesse choisie est plus petite ou égale à 0. Alors, il est impossible pour le voyageur de se rendre à destination!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Paradoxe2.this, "La vitesse choisie est plus grande ou égale à 100. Alors, il est impossible pour le voyageur de se rendre à destination!", Toast.LENGTH_SHORT).show();
                }
                if(vitesse_nombre > 0 && vitesse_nombre <100) {
                    calculerTemps();

                    temps_dilatea = temps*(sqrt((1-((vitesse_nombre/100))/(1+((vitesse_nombre/100))))));
                    temps_dilater = temps*(sqrt((1+((vitesse_nombre/100))/(1-((vitesse_nombre/100))))));

                    temps_dilate = temps_dilatea + temps_dilater + age_nombre;
                    temps_normal = (2*temps) + age_nombre;

                    Intent myIntent = new Intent(Paradoxe2.this, Paradoxe3.class);

                    myIntent.putExtra("ageN", String.valueOf(Math.round(temps_normal)));
                    myIntent.putExtra("ageD", String.valueOf(Math.round(temps_dilate)));
                    startActivity(myIntent);
                    //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("file:///C:/Users/Utilisateur/Desktop/Cegep/Session%204/Programmation/Intgration/Logs/WebGl/index.html"));
                    //startActivity(browserIntent);
                }
                else if (vitesse_nombre <=0) {
                    Toast.makeText(Paradoxe2.this, "La vitesse choisie est plus petite ou égale à 0. Alors, il est impossible pour le voyageur de se rendre à destination!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Paradoxe2.this, "La vitesse choisie est plus grande ou égale à 100. Alors, il est impossible pour le voyageur de se rendre à destination!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void addSpinerListener(){
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dest = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void calculerTemps() {
        switch (dest){
            case "Alpha Centauri C":
                temps = (4.367* 9.461 * pow(10,15)) / (((vitesse_nombre*(3*pow(10,8)))/100) * 31536000);
                //temps = ((41.32*pow(10,15))/((vitesse_nombre*(3*pow(10,8)))/100))/31536000;
                break;
            case "Étoile Polaire":
                temps = (433 * 9.461 * pow(10,15)) / (((vitesse_nombre*(3*pow(10,8)))/100) * 31536000);
                //temps = ((4.08*pow(10,18))/((vitesse_nombre*(3*pow(10,8)))/100))/31536000;
                break;
            case "Sirius":
                temps = (8.611 * 9.461 * pow(10,15)) / (((vitesse_nombre*(3*pow(10,8)))/100) * 31536000);
                //temps = ((81.46*pow(10,15))/((vitesse_nombre*(3*pow(10,8)))/100))/31536000;
                break;
            case "Galaxie d'Andromède":
                temps = (2.537 * pow(10,6) * 9.461 * pow(10,15)) / (((vitesse_nombre*(3*pow(10,8)))/100) * 31536000);
                //temps = ((2.401*pow(10,22))/((vitesse_nombre*(3*pow(10,8)))/100))/31536000;
                break;
        }
    }
}
