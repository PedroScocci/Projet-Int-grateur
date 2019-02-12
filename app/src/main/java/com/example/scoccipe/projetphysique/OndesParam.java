package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OndesParam extends AppCompatActivity {
    final static int ALLER_ONDES_SIMULATIONS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_param);

        Button param = (Button) findViewById(R.id.bOndesParam);

        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OndesParam.this, OndesSimu.class);
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
}
