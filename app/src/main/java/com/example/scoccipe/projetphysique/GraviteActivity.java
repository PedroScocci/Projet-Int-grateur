package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GraviteActivity extends AppCompatActivity {
    private Intent intent;
    private String planete, objet, masse, hauteur;
    private Double temps_de_chute, acceleration_grav, energie_impact, hauteur_moy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravite);

        ajoutBoutonRetour();

        recevoirParametres();

        animationTest();



        //creerButtonsTest();
    }

    public void recevoirParametres(){
        intent = getIntent();

        planete = intent.getStringExtra("planete");
        objet = intent.getStringExtra("objet");
        masse = intent.getStringExtra("masse");
        hauteur = intent.getStringExtra("hauteur");

        definirAccelerationGrav();
    }

    public void definirAccelerationGrav(){
        /*
            Améliorations possibles: Transformer les planètes en des objets pour faciliter la réitération
         */
        switch (planete){

            case "Mercure":
                acceleration_grav = 3.701;
                break;

            case "Venus":
                acceleration_grav = 8.87;
                break;

            case "Terre":
                acceleration_grav = 9.80665;
                break;

            case "Mars":
                acceleration_grav = 3.711;
                break;

            case "Jupiter":
                acceleration_grav = 24.79642;
                break;

            case "Saturne":
                acceleration_grav = 10.44;
                break;

            case "Uranus":
                acceleration_grav = 8.87;
                break;

            case "Neptune":
                acceleration_grav = 11.15;
                break;
        }

        calculTempsDeChute();
    }

    public void calculTempsDeChute(){
        temps_de_chute = Math.sqrt(2.0 * Double.parseDouble(hauteur) / acceleration_grav);
        temps_de_chute = Math.round(temps_de_chute * 100.0d) / 100.0d;

        definirEchelle();

        calculForceImpact();
    }

    public void definirEchelle(){
        TextView h_max = (TextView) findViewById(R.id.hauteur_max);
        Double temp = Double.valueOf(hauteur);
        temp = Math.round(temp*10.0d)/10.0d;
        h_max.setText(temp.toString() + " m");

        TextView h_moy = (TextView) findViewById(R.id.hauteur_moy);
        hauteur_moy = Double.valueOf(hauteur)/2;
        hauteur_moy = Math.round(hauteur_moy*10.0d)/10.0d;
        h_moy.setText(hauteur_moy.toString() + " m");

    }

    public void calculForceImpact(){
        Double vitesse_finale = acceleration_grav * temps_de_chute;
        vitesse_finale = Math.round(vitesse_finale * 100d) / 100d;
        energie_impact = 0.5 * Double.parseDouble(masse) * Math.pow(vitesse_finale, 2);
        energie_impact = Math.round(energie_impact * 100d) / 100d;

        TextView v_finale = (TextView) findViewById(R.id.vitesse_finale);
        v_finale.setText(vitesse_finale.toString() + " m/s");

        //Toast.makeText(GraviteActivity.this, tonne_tnt.toString() + " tonnes de TNT", Toast.LENGTH_SHORT).show();

    }

    public void animationTest(){
        ImageView imageCercle = (ImageView) findViewById(R.id.image_test);

        Double temps_temp = temps_de_chute * 1000; //pour l'avoir en ms

        imageCercle.animate()
                .y(1075)
                .setInterpolator(new AccelerateInterpolator(1.5f))
                .setDuration(temps_temp.longValue())
                .start();


    }

    public void ajoutBoutonRetour(){
        Button bouton_retour = (Button) findViewById(R.id.bouton_retourSG_PG);
        bouton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*public void creerButtonsTest(){

        Button bouton_planete = (Button) findViewById(R.id.bouton_planete);
        bouton_planete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GraviteActivity.this, planete, Toast.LENGTH_SHORT).show();
            }
        });

        Button bouton_masse = (Button) findViewById(R.id.bouton_masse);
        bouton_masse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GraviteActivity.this, masse, Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
