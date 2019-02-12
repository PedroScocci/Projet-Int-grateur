package com.example.scoccipe.projetphysique;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ParametresGravite extends AppCompatActivity {
    private Button bouton_ok;
    private Spinner spinner_planete, spinner_objets;

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
                Toast.makeText(ParametresGravite.this, "OK", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ajoutListenerPlanete(){
        spinner_planete = (Spinner) findViewById(R.id.spinner_planete);
        spinner_planete.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void ajoutListenerObjets(){
        spinner_objets = (Spinner) findViewById(R.id.spinner_objets);
        spinner_planete.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
