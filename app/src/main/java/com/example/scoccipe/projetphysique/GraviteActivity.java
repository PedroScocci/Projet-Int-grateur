package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GraviteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravite);
        final Intent intenteru = getIntent();




        Button bouton_planete = (Button) findViewById(R.id.bouton_planete);
        bouton_planete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GraviteActivity.this,
                        intenteru.getStringExtra("test"),
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button bouton_masse = (Button) findViewById(R.id.bouton_masse);
        bouton_masse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(GraviteActivity.this,
                        intenteru.getStringExtra("masse"),
                        Toast.LENGTH_SHORT).show();*/
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
