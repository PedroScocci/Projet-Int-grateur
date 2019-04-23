package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Paradoxe4 extends AppCompatActivity {

    private Intent intent;
    private String agen, aged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxe4);
        intent = getIntent();
        agen = intent.getStringExtra("ageN");
        aged = intent.getStringExtra("ageD");
        TextView txtn = (TextView) findViewById(R.id.ageno);
        TextView txtd = (TextView) findViewById(R.id.agedi);

        txtn.setText(" " + agen + " ");
        txtd.setText(" " + aged + " ");
    }
}
