package com.example.scoccipe.projetphysique;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class OndesParam extends AppCompatActivity {
    final static int ALLER_ONDES_SIMULATIONS = 2;
    final static String MODE_STATIONNAIRE = "mode";
    final static String EXTREMITES = "extremités";
    private String extremite = "";
    private double modeStationnaire = 0;
    private boolean calculEffectuer;
    private TextView txtModeStatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_param);

        calculEffectuer = false;

        final Button calcul = findViewById(R.id.bOndesParamCalcul);
        calcul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calculEffectuer = true;

                Spinner extrem = findViewById(R.id.ondesExtrem);
                extremite = extrem.getSelectedItem().toString();

                if(Objects.equals(extremite, "Type d'extrémité")) {
                    Toast.makeText(OndesParam.this, R.string.ondesParamChoixExtremite, Toast.LENGTH_LONG).show();

                } else {
                    modeStationnaire = calculModeStationnaire();

                    if(calculEffectuer) {
                        txtModeStatio = findViewById(R.id.ondesModeStatio);
                        double temp = (double) Math.round(modeStationnaire*1000)/1000;
                        txtModeStatio.setText(getText(R.string.ondesParamModeStatio)+ " " + String.valueOf(temp));
                    }
                }
            }
        });

        Button param = findViewById(R.id.bOndesParamDemarrer);
        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OndesParam.this, OndesSimu.class);

                if (!calculEffectuer) {
                    Toast.makeText(OndesParam.this, R.string.ondesParamCalculOublier, Toast.LENGTH_LONG).show();
                } else {
                    intent.putExtra(EXTREMITES, extremite);
                    intent.putExtra(MODE_STATIONNAIRE, String.valueOf(modeStationnaire));
                    startActivityForResult(intent, ALLER_ONDES_SIMULATIONS);
                }
            }
        });

        Button quitter = findViewById(R.id.bOndesParamQuitter);
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    double calculModeStationnaire() {
        EditText tens = findViewById(R.id.ondesTension);
        EditText freq = findViewById(R.id.ondesFrequence);
        EditText longu = findViewById(R.id.ondesLongueur);
        EditText mass = findViewById(R.id.ondesMasse);
        double mode = -1;

        try {

            double tension = Double.parseDouble(tens.getText().toString());
            double frequence = Double.parseDouble(freq.getText().toString());
            double longueur = Double.parseDouble(longu.getText().toString());
            double masse = Double.parseDouble(mass.getText().toString());

            if(Objects.equals(extremite, "Semi-ouvert")){
                mode =  ( 4 * frequence * ( Math.sqrt( masse * longueur / tension) )  - 1 ) / 2 ;
            }
            else if (Objects.equals(extremite, "Fermé")) {
                mode =  2 * frequence * ( Math.sqrt( masse * longueur / tension) );
            }
        } catch (NumberFormatException e) {
            Toast.makeText(OndesParam.this,   R.string.ondesParamOublier, Toast.LENGTH_LONG).show();
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
            Toast.makeText(OndesParam.this, R.string.ondesParamErreurRetour, Toast.LENGTH_LONG).show();
        }
    }
}
