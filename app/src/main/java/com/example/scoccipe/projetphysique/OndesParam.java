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
    private long modeStationnaire = 0;
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
                System.err.printf("LOOOOOOOOOOOOOOOOOOOOLLLLLLLLLLLLLLLLLLL");
                try {
                    EditText tens = (EditText) findViewById(R.id.ondesTension);
                    tension = Double.parseDouble(tens.getText().toString());
                    EditText freq = (EditText) findViewById(R.id.ondesFrequence);
                    frequence = Double.parseDouble(freq.getText().toString());
                    EditText longu = (EditText) findViewById(R.id.ondesLongueur);
                    longueur = Double.parseDouble(longu.getText().toString());
                    EditText mass = (EditText) findViewById(R.id.ondesMasse);
                    masse = Double.parseDouble(mass.getText().toString());

                } catch (NumberFormatException e) {

                }

                Toast.makeText(OndesParam.this, String.valueOf(frequence), Toast.LENGTH_LONG).show();

                double mode = 0;

                if(Objects.equals(extremite, "Semi-ouvert")){

                }
                else if (Objects.equals(extremite, "Fermé")) {

                    mode =  2 * frequence;
                    Toast.makeText(OndesParam.this, String.valueOf(mode), Toast.LENGTH_LONG).show();
                    mode = mode * ( Math.sqrt( masse * longueur / tension) );

                    modeStationnaire = Math.round(mode);
                }

                if(mode != modeStationnaire) {

                } else {
                    startActivityForResult(intent, ALLER_ONDES_SIMULATIONS);
                }
            }
        });
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
