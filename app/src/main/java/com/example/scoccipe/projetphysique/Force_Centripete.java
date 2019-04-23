package com.example.scoccipe.projetphysique;

import android.animation.Animator;
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
    private ImageView iv ;
    private ImageView iv2;
    private ImageView iv3;
    private Button b;
    private RelativeLayout.LayoutParams lp;
    private int derapcompteur;

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
                b.setClickable(false);
                if(derapage){
                    faireUnTourDerap();
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
                startActivityForResult(intent2, FORCE);
                finish();
                return true;
            case R.id.menu_Parametres:
                intent2 = new Intent(Force_Centripete.this, ParametresCentripete.class);
                startActivityForResult(intent2, FORCE);
                finish();
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
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                b.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    public void faireUnTourDerap(){
        derapcompteur++;
        if(derapcompteur > 1)
        {
            relativeLayout.addView(iv,lp);
        }

        Path path = new Path();
        path.arcTo(0f,0f,905f,850f,0f,-60,true);
        iv.animate().rotationBy(-60).setDuration(temps/6);
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv,View.X,View.Y,path);
        animator.setDuration(temps/6).start();
        AnimationSet as = new AnimationSet(true);
        TranslateAnimation animator2 = new TranslateAnimation(0,-200,0,-275);
        animator2.setDuration(temps/8);
        animator2.setStartOffset(temps/6);
        as.addAnimation(animator2);
        iv.startAnimation(as);
        animator2.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.clearAnimation();
                lp = new RelativeLayout.LayoutParams(iv.getWidth(), iv.getHeight());
                lp.setMargins(1105, 750, 700, 0);
                iv.setLayoutParams(lp);
                iv.animate().rotationBy(60).setDuration(1);
                relativeLayout.removeView(iv);
                b.setClickable(true);
            }
        });
    }
}
