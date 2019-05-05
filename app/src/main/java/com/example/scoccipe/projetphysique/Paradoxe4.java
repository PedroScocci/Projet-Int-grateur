package com.example.scoccipe.projetphysique;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Paradoxe4 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxe4);
        Intent intent = getIntent();
        String agen = intent.getStringExtra("ageN");
        String aged = intent.getStringExtra("ageD");
        TextView txtn = findViewById(R.id.ageno);
        TextView txtd = findViewById(R.id.agedi);

        txtn.setText(" " + agen + " ");
        txtd.setText(" " + aged + " ");
    }
}
