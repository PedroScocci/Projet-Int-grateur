package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class OndesParam extends AppCompatActivity {
    final static int ALLER_ONDES_SIMULATIONS = 2;
    private double tension = 0;
    private double frequence = 0;
    private double longueur = 0;
    private double masse = 0;
    private String extremite = "";
    private String [] list = getResources().getStringArray(R.array.extremite_array);


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

                if(extremite == list[0]){

                }
                else if (extremite == list[1]) {

                }

                Toast.makeText(OndesParam.this, extremite, Toast.LENGTH_LONG).show();

                startActivityForResult(intent, ALLER_ONDES_SIMULATIONS);
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

    //class AdapterView
}
