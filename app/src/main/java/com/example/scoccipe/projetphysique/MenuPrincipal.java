package com.example.scoccipe.projetphysique;

import android.graphics.Typeface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {
    final static int RETOUR_MENU_PRINCIPAL = 42;
    private Button boutonGrav, boutonCentri, boutonParadoxe, boutonOndes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal2);

        modifierPoliceTextes();
        ajoutListenerBoutons();
        Button onde = (Button) findViewById(R.id.Onde);
        onde.setOnClickListener(new View.OnClickListener() {
            @Override
                Intent intent = new Intent(MenuPrincipal.this, OndesInfo.class);
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    public void modifierPoliceTextes(){
        Typeface font = Typeface.createFromAsset(getAssets(), "CooperB.ttf");
        TextView texte = (TextView) findViewById(R.id.Titre_menuP);
        texte.setTypeface(font);
        texte = (TextView) findViewById(R.id.Onde);
        texte.setTypeface(font);
        texte = (TextView) findViewById(R.id.Paradoxe);
        texte.setTypeface(font);
        texte = (TextView) findViewById(R.id.ForceCentripete);
        texte.setTypeface(font);
    }

    public void ajoutListenerBoutons(){
        /*boutonGrav = (Button) findViewById(R.id.Gravite);
        boutonGrav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPrincipal.this, "GRAVITÉ", Toast.LENGTH_LONG).show();
            }
        });*/

        boutonCentri = (Button) findViewById(R.id.ForceCentripete);
        boutonCentri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPrincipal.this, "CENTRIPÈTE", Toast.LENGTH_LONG).show();
            }
        });

        boutonParadoxe = (Button) findViewById(R.id.Paradoxe);
        boutonParadoxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPrincipal.this, "PARADOXE", Toast.LENGTH_LONG).show();
            }
        });

        boutonOndes = (Button) findViewById(R.id.Onde);
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
