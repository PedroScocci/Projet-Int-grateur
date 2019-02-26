package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

public class OndesParam extends AppCompatActivity {
    final static int ALLER_ONDES_SIMULATIONS = 2;
    final static String MODE_STATIONNAIRE = "mode";
    private double modeStationnaire = 0;
    private double tension = 0;
    private double frequence = 0;
    private double longueur = 0;
    private double masse = 0;
    private String extremite = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_param);

        Button param = (Button) findViewById(R.id.bOndesParam);

        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OndesParam.this, OndesSimu.class);

                Spinner extrem = (Spinner) findViewById(R.id.ondesExtrem);
                extremite = extrem.getSelectedItem().toString();


                if(Objects.equals(extremite, "Type d'extrémité")) {
                    //extrem.setBackgroundColor(getResources().getColor(R.color.rouge));
                    Toast.makeText(OndesParam.this, "Chosir un type d'extrémité à la corde!", Toast.LENGTH_LONG).show();

                } else {
                    //extrem.setBackgroundColor(getResources().getColor(R.color.blanc));
                    if(modeStationnaire == -1) {

                    }
                    else{
                        modeStationnaire = calculModeStationnaire();
                        //Toast.makeText(OndesParam.this,   String.valueOf(modeStationnaire), Toast.LENGTH_LONG).show();
                        intent.putExtra(MODE_STATIONNAIRE, String.valueOf(modeStationnaire));
                        startActivityForResult(intent, ALLER_ONDES_SIMULATIONS);
                    }

                }
            }
        });
    }

    double calculModeStationnaire() {
        EditText tens = (EditText) findViewById(R.id.ondesTension);
        EditText freq = (EditText) findViewById(R.id.ondesFrequence);
        EditText longu = (EditText) findViewById(R.id.ondesLongueur);
        EditText mass = (EditText) findViewById(R.id.ondesMasse);
        double mode = -1;

        try {

            tension = Double.parseDouble(tens.getText().toString());
            frequence = Double.parseDouble(freq.getText().toString());
            longueur = Double.parseDouble(longu.getText().toString());
            masse = Double.parseDouble(mass.getText().toString());

            if(Objects.equals(extremite, "Semi-ouvert")){
                mode =  ( 4 * frequence * ( Math.sqrt( masse * longueur / tension) )  - 1 ) / 2 ;
            }
            else if (Objects.equals(extremite, "Fermé")) {
                mode =  2 * frequence * ( Math.sqrt( masse * longueur / tension) );
            }
        } catch (NumberFormatException e) {
            Toast.makeText(OndesParam.this,   "Vous avez oubliez de saisir des paramètres", Toast.LENGTH_LONG).show();
        }

        return mode;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ALLER_ONDES_SIMULATIONS ) {
            if(resultCode == MenuPrincipal.RETOUR_MENU_PRINCIPAL) {
                finish();
            }
            if(requestCode == RESULT_OK) {
                Toast.makeText(OndesParam.this, "lol", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(OndesParam.this, "Erreur dans la saisie", Toast.LENGTH_LONG).show();
        }
    }
}
