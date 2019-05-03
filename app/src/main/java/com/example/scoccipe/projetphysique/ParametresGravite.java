package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;


public class ParametresGravite extends AppCompatActivity {

    private Button bouton_ok, bouton_retour;
    private Spinner spinner_planete;
    private String planete, masse, hauteur;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_gravite);

        intent = new Intent(ParametresGravite.this, GraviteActivity.class);

        ajoutListenerButton();
        ajoutListenerPlanete();
    }

    public void ajoutListenerButton(){
        bouton_ok = (Button) findViewById(R.id.bouton_okPG_SG);
        bouton_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText masse_text = (EditText)  findViewById(R.id.masse_obj1);
                EditText hauteur_text = (EditText) findViewById(R.id.hauteur);
                AlertDialog.Builder builder = new AlertDialog.Builder(ParametresGravite.this);

                if(masse_text.getText().toString().isEmpty() == true || hauteur_text.getText().toString().isEmpty() == true){
                    builder.setMessage("Veuillez remplir toutes les cases.")
                            .setTitle("ERREUR :");

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    if(Double.parseDouble(hauteur_text.getText().toString()) > 1000.0)
                    {
                        builder.setMessage("Hauteur maximale: 1000m")
                                .setTitle("ERREUR :");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    if(Double.parseDouble(masse_text.getText().toString()) > 10000.0){
                        builder.setMessage("Masse maximale: 10 000kg")
                                .setTitle("ERREUR :");
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
            }
        });

        bouton_retour = (Button) findViewById(R.id.bouton_retourPG_M);
        bouton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void ajoutListenerPlanete(){
        spinner_planete = (Spinner) findViewById(R.id.spinner_planete);
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Mercure");
        spinnerArray.add("Venus");
        spinnerArray.add("Terre");
        spinnerArray.add("Mars");
        spinnerArray.add("Jupiter");
        spinnerArray.add("Saturne");
        spinnerArray.add("Uranus");
        spinnerArray.add("Neptune");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.layout_spinner_planete, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_planete.setAdapter(spinnerAdapter);

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
}
