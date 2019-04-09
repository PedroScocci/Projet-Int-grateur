package com.example.scoccipe.projetphysique;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Force_Centripete extends AppCompatActivity {
    public static final int FORCE = 2;
    private Intent intent;
    private Intent intent2;
    private RelativeLayout relativeLayout;
    private boolean derapage;
    private double vitesse;
    private double rayon;
    private int route;
    private long temps;
    private long tempsderap;
    private ImageView iv ;
    private ImageView iv2;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force__centripete);

        relativeLayout = findViewById(R.id.back);
        iv = findViewById(R.id.voit);

        intent = getIntent();
        derapage = intent.getBooleanExtra(ParametresCentripete.PARAMETRES, false);
        vitesse = intent.getDoubleExtra(ParametresCentripete.PARAMETRES2, 0);
        rayon = intent.getDoubleExtra(ParametresCentripete.PARAMETRES3, 0);
        route = intent.getIntExtra(ParametresCentripete.PARAMETRES4, 0);
        temps = Math.round((2*Math.PI*rayon)/(vitesse)*1000);
        tempsderap = Math.round(rayon/vitesse*1000);



        if(derapage) {
            Toast.makeText(Force_Centripete.this,"feef" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(Force_Centripete.this,"deg" , Toast.LENGTH_LONG).show();
        }

        b = findViewById(R.id.buttonsim);
        switch(route){
            case 0: relativeLayout.setBackground(getDrawable(R.drawable.route));
                break;
            case 1: relativeLayout.setBackground(getDrawable(R.drawable.route));
                break;
            case 2: relativeLayout.setBackground(getDrawable(R.drawable.route2));
                break;
            case 3: relativeLayout.setBackground(getDrawable(R.drawable.route2));
                break;
            case 4: relativeLayout.setBackground(getDrawable(R.drawable.route3));
                break;
            case 5: relativeLayout.setBackground(getDrawable(R.drawable.route4));
                break;
        }


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(derapage){
                    faireUnTour();
                    AnimationSet as = new AnimationSet(true);
                    TranslateAnimation animator2 = new TranslateAnimation(0,0,0,-700);
                    //RotateAnimation animation2 = new RotateAnimation(0,-360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                    //animation2.setDuration(6000);
                    animator2.setDuration(1000);
                    animator2.setStartOffset(temps);
                    //animation2.setInterpolator(new LinearInterpolator());
                    //as.addAnimation(animation2);
                    as.addAnimation(animator2);
                    iv.startAnimation(as);
                }
                else{
                    faireUnTour();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_centripete, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_principal2:
                intent2 = new Intent(Force_Centripete.this, MenuPrincipal.class);
                startActivityForResult(intent, FORCE);
                return true;
            case R.id.menu_Parametres:
                intent2 = new Intent(Force_Centripete.this, ParametresCentripete.class);
                startActivityForResult(intent, FORCE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void faireUnTour() {

        Path path = new Path();
        path.addCircle(450,411, 450, Path.Direction.CCW);

        iv.animate().rotationBy(-360).setDuration(temps);
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv,View.X,View.Y,path);
        animator.setDuration(temps).start();
    }
}
