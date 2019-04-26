package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Informations extends AppCompatActivity {
    public static final int INFO = 3;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        Button button = findViewById(R.id.buttoninfo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Informations.this, ParametresCentripete.class);
                startActivityForResult(intent, INFO);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_informations, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_principal3:
                intent = new Intent(Informations.this, MenuPrincipal.class);
                startActivityForResult(intent, INFO);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
