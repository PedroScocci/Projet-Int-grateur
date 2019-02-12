package com.example.scoccipe.projetphysique;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class OndesSimu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_simu);

        Button start = (Button) findViewById(R.id.bOndesSimu);
    }
}
