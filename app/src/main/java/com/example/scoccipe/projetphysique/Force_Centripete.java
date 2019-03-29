package com.example.scoccipe.projetphysique;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Force_Centripete extends AppCompatActivity {
    private Intent intent;
    private boolean derapage;
    private double vitesse;
    private double rayon;
    private long temps;
    private ImageView iv ;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force__centripete);

        intent = getIntent();
        derapage = intent.getBooleanExtra(ParametresCentripete.PARAMETRES, false);
        vitesse = Double.parseDouble(intent.getStringExtra(ParametresCentripete.PARAMETRES2));
        rayon = Double.parseDouble(intent.getStringExtra(ParametresCentripete.PARAMETRES3));
        temps = Math.round((2*Math.PI*rayon)/(vitesse)*1000);


        if(derapage) {
            Toast.makeText(Force_Centripete.this,"feef" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(Force_Centripete.this,"deg" , Toast.LENGTH_LONG).show();
        }

        b = findViewById(R.id.buttonsim);
        iv = findViewById(R.id.voit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(derapage){


                }
                else{
                    Path path = new Path();
                    path.addCircle(0,411, 450, Path.Direction.CCW);

                    iv.animate().rotationBy(-360).setDuration(temps);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(iv,View.X,View.Y,path);
                    animator.setDuration(temps).start();
                }
            }
        });
    }
}
