package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_gravite);

        intent = new Intent(ParametresGravite.this, GraviteActivity.class);

        ajoutListenerButton();
        ajoutListenerPlanete();
        ajoutListenerObjets();
    }

    public void ajoutListenerButton(){
        bouton_ok = (Button) findViewById(R.id.bouton_ok);
        bouton_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText masse_text = (EditText)  findViewById(R.id.masse_obj1);
                EditText hauteur_text = (EditText) findViewById(R.id.hauteur);

                if(masse_text.getText().toString().isEmpty() == true || hauteur_text.getText().toString().isEmpty() == true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ParametresGravite.this);

                    builder.setMessage("Veuillez remplir toutes les cases.")
                            .setTitle("ERREUR");

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    masse = masse_text.getText().toString();
                    hauteur = hauteur_text.getText().toString();

                    intent.putExtra("masse", masse);
                    intent.putExtra("hauteur", hauteur);

                    startActivity(intent);
                }
            }
        });
    }

    public void ajoutListenerPlanete(){
        spinner_planete = (Spinner) findViewById(R.id.spinner_planete);
        spinner_planete.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                planete = parent.getItemAtPosition(pos).toString();
                intent.putExtra("planete", planete);
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
                objet = parent.getItemAtPosition(pos).toString();
                intent.putExtra("objet", objet);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
