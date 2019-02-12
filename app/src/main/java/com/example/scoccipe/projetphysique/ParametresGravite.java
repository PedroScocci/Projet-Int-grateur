package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ParametresGravite extends AppCompatActivity {
    private Button bouton_ok;
    private Spinner spinner_planete, spinner_objets;
    private String planete, objet, masse, hauteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_gravite);

        ajoutListenerButton();
        ajoutListenerPlanete();
        ajoutListenerObjets();
    }

    public void ajoutListenerButton(){
        bouton_ok = (Button) findViewById(R.id.bouton_ok);
        bouton_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ParametresGravite.this, GraviteActivity.class);

                EditText masse_text = (EditText)  findViewById(R.id.masse_obj1);
                masse = masse_text.getText().toString();

                Toast.makeText(ParametresGravite.this, masse, Toast.LENGTH_SHORT).show();

                EditText hauteur_text = (EditText) findViewById(R.id.hauteur);
                hauteur = hauteur_text.getText().toString();


                intent.putExtra("masse", masse);
                intent.putExtra("planete", planete);

                startActivity(intent);
            }
        });
    }

    public void ajoutListenerPlanete(){
        spinner_planete = (Spinner) findViewById(R.id.spinner_planete);
        spinner_planete.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                planete = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void ajoutListenerObjets(){
        spinner_objets = (Spinner) findViewById(R.id.spinner_objets);
        spinner_objets.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                objet = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
