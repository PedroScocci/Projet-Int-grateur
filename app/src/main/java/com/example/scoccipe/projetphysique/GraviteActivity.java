package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GraviteActivity extends AppCompatActivity {
    private Intent intent;
    private String planete, objet, masse, hauteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravite);

        recevoirParametres();
        crerButtonsTest();

    }

    public void recevoirParametres(){
        intent = getIntent();

        planete = intent.getStringExtra("planete");
        objet = intent.getStringExtra("objet");
        masse = intent.getStringExtra("masse");
        hauteur = intent.getStringExtra("hauteur");
    }

    public void crerButtonsTest(){

        Button bouton_planete = (Button) findViewById(R.id.bouton_planete);
        bouton_planete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GraviteActivity.this, planete, Toast.LENGTH_SHORT).show();
            }
        });

        Button bouton_masse = (Button) findViewById(R.id.bouton_masse);
        bouton_masse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GraviteActivity.this, masse, Toast.LENGTH_SHORT).show();
            }
        });

        Button bouton_retour = (Button) findViewById(R.id.bouton_retour);
        bouton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
