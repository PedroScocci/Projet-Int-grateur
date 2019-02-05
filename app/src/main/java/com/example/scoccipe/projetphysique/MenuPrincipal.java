package com.example.scoccipe.projetphysique;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button boutonGrav = (Button) findViewById(R.id.Gravite);
        boutonGrav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPrincipal.this, "GRAVITÉ", Toast.LENGTH_LONG).show();
            }
        });

        Button boutonCentri = (Button) findViewById(R.id.ForceCentripete);
        boutonCentri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPrincipal.this, "CENTRIPÈTE", Toast.LENGTH_LONG).show();
            }
        });

        Button boutonParadoxe = (Button) findViewById(R.id.Paradoxe);
        boutonParadoxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPrincipal.this, "PARADOXE", Toast.LENGTH_LONG).show();
            }
        });

        Button boutonOndes = (Button) findViewById(R.id.Onde);
        boutonOndes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPrincipal.this, "ONDES", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.maintoolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_apropos:
                Toast.makeText(MenuPrincipal.this, "A PROPOS", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
