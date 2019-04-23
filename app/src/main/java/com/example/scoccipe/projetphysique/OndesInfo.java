package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OndesInfo extends AppCompatActivity {
    final static int ALLER_ONDES_PARAM = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_info);

        Button param = findViewById(R.id.bOndesInfoParam);
        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OndesInfo.this, OndesParam.class);
                startActivityForResult(intent, ALLER_ONDES_PARAM);
            }
        });

        Button retour = findViewById(R.id.bOndesInfoRetour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ALLER_ONDES_PARAM && resultCode == MenuPrincipal.RETOUR_MENU_PRINCIPAL) {
            finish();
        }
    }
}
