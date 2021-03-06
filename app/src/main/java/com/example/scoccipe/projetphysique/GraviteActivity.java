package com.example.scoccipe.projetphysique;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GraviteActivity extends AppCompatActivity {
    private String planete, masse, hauteur;
    private Double temps_de_chute;
    private Double acceleration_grav;
    private ImageView imageObjet;
    private Drawable imagePlanete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravite);

        imageObjet = findViewById(R.id.objet_chute);

        gestionBoutons();

        recevoirParametres();
    }

    public void recevoirParametres(){
        Intent intent = getIntent();

        planete = intent.getStringExtra("planete");
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
                imagePlanete = getDrawable(R.drawable.mercure);
                break;

            case "Venus":
                acceleration_grav = 8.87;
                imagePlanete = getDrawable(R.drawable.venus);
                break;

            case "Terre":
                acceleration_grav = 9.80665;
                imagePlanete = getDrawable(R.drawable.terre);
                break;

            case "Mars":
                acceleration_grav = 3.711;
                imagePlanete = getDrawable(R.drawable.mars);
                break;

            case "Jupiter":
                acceleration_grav = 24.79642;
                imagePlanete = getDrawable(R.drawable.jupiter);
                break;

            case "Saturne":
                acceleration_grav = 10.44;
                imagePlanete = getDrawable(R.drawable.saturne);
                break;

            case "Uranus":
                acceleration_grav = 8.87;
                imagePlanete = getDrawable(R.drawable.uranus);
                break;

            case "Neptune":
                acceleration_grav = 11.15;
                imagePlanete = getDrawable(R.drawable.neptune);
                break;
        }

        LinearLayout layout_planete = findViewById(R.id.layout_planete);
        layout_planete.setBackground(imagePlanete);

        calculTempsDeChute();
    }

    public void calculTempsDeChute(){
        temps_de_chute = Math.sqrt(2.0 * Double.parseDouble(hauteur) / acceleration_grav);
        temps_de_chute = Math.round(temps_de_chute * 100.0d) / 100.0d;

        definirEchelle();

        calculForceImpact();
    }

    @SuppressLint("SetTextI18n")
    public void definirEchelle(){
        TextView h_max = findViewById(R.id.hauteur_max);
        double temp = Double.parseDouble(hauteur);
        temp = Math.round(temp*10.0d)/10.0d;
        h_max.setText(Double.toString(temp) + " m");

        TextView h_moy = findViewById(R.id.hauteur_moy);
        double hauteur_moy = Double.valueOf(hauteur) / 2;
        hauteur_moy = Math.round(hauteur_moy *10.0d)/10.0d;
        h_moy.setText(Double.toString(hauteur_moy) + " m");
    }

    @SuppressLint("SetTextI18n")
    public void calculForceImpact(){
        double vitesse_finale = acceleration_grav * temps_de_chute;
        vitesse_finale = Math.round(vitesse_finale * 100d) / 100d;
        double energie_impact = 0.5 * Double.parseDouble(masse) * Math.pow(vitesse_finale, 2);
        TextView texte_tnt = findViewById(R.id.energie_tnt);

        String texte_energie;
        if(energie_impact >= (4.184*(Math.pow(10,9)))){
            energie_impact /= 4.184*(Math.pow(10,9));
            energie_impact = Math.round(energie_impact * 100d) / 100d;
            texte_energie = Double.toString(energie_impact) + " tonnes de TNT";
            texte_tnt.setText(texte_energie);
        }
        else if(energie_impact >= (4.184*(Math.pow(10,6)))){
            energie_impact /= 4.184*(Math.pow(10,6));
            energie_impact = Math.round(energie_impact * 100d) / 100d;
            texte_energie = Double.toString(energie_impact) + " kilogrammes de TNT";
            texte_tnt.setText(texte_energie);
        }
        else{
            energie_impact /= 4.184*(Math.pow(10,3));
            energie_impact = Math.round(energie_impact * 100d) / 100d;
            texte_energie = Double.toString(energie_impact) + " grammes de TNT";
            texte_tnt.setText(texte_energie);
        }

        TextView v_finale = findViewById(R.id.vitesse_finale);
        v_finale.setText(Double.toString(vitesse_finale) + " m/s");
    }

    public void animationTest(Button bouton){
        Double temps_temp = temps_de_chute * 1000; //pour l'avoir en ms

        bouton.setClickable(false);
        imageObjet.animate()
                .y(1050)
                .setInterpolator(new AccelerateInterpolator(1.5f))
                .setDuration(temps_temp.longValue())
                .start();
    }

    public void gestionBoutons(){
        final Button bouton_debut = findViewById(R.id.button_debut_anim);
        bouton_debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationTest(bouton_debut);
            }
        });

        Button bouton_retour = findViewById(R.id.bouton_retourSG_PG);
        bouton_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
