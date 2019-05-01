package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.graphics.Typeface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        setContentView(R.layout.activity_menu_principal3);

        //modifierPoliceTextes();
        ajoutListenerBoutons();
    }

   /* public void modifierPoliceTextes(){
        Typeface font = Typeface.createFromAsset(getAssets(), "CooperB.ttf");
        TextView texte = (TextView) findViewById(R.id.Titre_menuP);
        texte.setTypeface(font);
        texte = (TextView) findViewById(R.id.Onde);
        texte.setTypeface(font);
        texte = (TextView) findViewById(R.id.Paradoxe);
        texte.setTypeface(font);
        texte = (TextView) findViewById(R.id.ForceCentripete);
        texte.setTypeface(font);
    }*/

    public void ajoutListenerBoutons(){
        boutonGrav = (Button) findViewById(R.id.Gravite);
        boutonGrav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        boutonCentri = (Button) findViewById(R.id.ForceCentripete);
        boutonCentri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, Informations.class);
                startActivity(intent);
            }
        });

        boutonParadoxe = (Button) findViewById(R.id.Paradoxe);
        boutonParadoxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MenuPrincipal.this, Paradoxe.class);
                startActivity(myIntent);
            }
        });

        boutonOndes = (Button) findViewById(R.id.Onde);
        boutonOndes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, OndesInfo.class);
                startActivity(intent);
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
        switch(item.getItemId()) {
            case R.id.menu_avantpropos:
                Intent intent1 = new Intent(MenuPrincipal.this, Avant_propos.class);
                startActivityForResult(intent1, RETOUR_MENU_PRINCIPAL);
                finish();
                return true;
            case R.id.menu_developpement:
                Intent intent2 = new Intent(MenuPrincipal.this, Developpement.class);
                startActivityForResult(intent2, RETOUR_MENU_PRINCIPAL);
                finish();
                return true;
            case R.id.menu_notions:
                Intent intent3 = new Intent(MenuPrincipal.this, Notions.class);
                startActivityForResult(intent3, RETOUR_MENU_PRINCIPAL);
                finish();
                return true;
            case R.id.menu_options:
                Intent intent4 = new Intent(MenuPrincipal.this, Options.class);
                startActivityForResult(intent4, RETOUR_MENU_PRINCIPAL);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

  /*  @Override
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
    }*/

}