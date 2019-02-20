package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.List;
import java.util.ArrayList;

public class Paradoxe2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxe2);
        EditText age = (EditText) findViewById(R.id.age);
        EditText vitesse = (EditText) findViewById(R.id.vitesse);
        Button but = (Button) findViewById(R.id.paradoxe2);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double age_nombre ;
                Intent myIntent = new Intent(Paradoxe2.this, Paradoxe3.class);
                startActivity(myIntent);
            }
        });

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Jupiter");
        spinnerArray.add("Saturne");
        spinnerArray.add("Mars");
        spinnerArray.add("Galaxie d'Andremede");
        spinnerArray.add("Pluton");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
    }
}
