package com.example.scoccipe.projetphysique;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

public class OndesParam extends AppCompatActivity {
    final static int ALLER_ONDES_SIMULATIONS = 2;
    final static String MODE_STATIONNAIRE = "mode";
    private String extremite = "";
    private double modeStationnaire = 0, tension = 0, frequence = 0, longueur = 0, masse = 0;
    private boolean calculEffectuer;
    private TextView txtModeStatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_param);

        calculEffectuer = false;

        final Button calcul = (Button) findViewById(R.id.bOndesParamCalcul);
        calcul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calculEffectuer = true;

                Spinner extrem = (Spinner) findViewById(R.id.ondesExtrem);
                extremite = extrem.getSelectedItem().toString();

                if(Objects.equals(extremite, "Type d'extrémité")) {
                    Toast.makeText(OndesParam.this, "Chosir un type d'extrémité à la corde!", Toast.LENGTH_LONG).show();

                } else {
                    modeStationnaire = calculModeStationnaire();

                    if(calculEffectuer) {
                        txtModeStatio = (TextView) findViewById(R.id.ondesModeStatio);
                        double temp = (double) Math.round(modeStationnaire*1000)/1000;
                        txtModeStatio.setText(R.string.ondesParamModeStatio + String.valueOf(temp));
                    }
                }
            }
        });

        Button param = (Button) findViewById(R.id.bOndesParamDemarrer);
        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OndesParam.this, OndesSimu.class);

                if (!calculEffectuer) {
                    Toast.makeText(OndesParam.this, "Le mode stationnaire n'a pas été calculé", Toast.LENGTH_LONG).show();
                } else {
                    intent.putExtra(MODE_STATIONNAIRE, String.valueOf(modeStationnaire));
                    startActivityForResult(intent, ALLER_ONDES_SIMULATIONS);
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
            calculEffectuer = false;
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
            else if(resultCode == RESULT_OK){
                calculEffectuer = false;
                txtModeStatio.setText(R.string.ondesParamModeStatio);
            }
            else {
                calculEffectuer = false;
                txtModeStatio.setText(R.string.ondesParamModeStatio);
            }
        }
        else {
            Toast.makeText(OndesParam.this, "Erreur dans la saisie", Toast.LENGTH_LONG).show();
        }
    }
}
