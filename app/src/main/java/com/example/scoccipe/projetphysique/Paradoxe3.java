package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Animator;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.animation.AnimatorSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


public class Paradoxe3 extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_paradoxe3);
        intent = getIntent();

        final ImageView fusee = (ImageView) findViewById(R.id.fus);
        final ImageView perso = (ImageView) findViewById(R.id.pers);
        //fusee.setImageResource(R.drawable.fusee);

        ObjectAnimator Yperso = ObjectAnimator.ofFloat(perso, "translationY", perso.getY(), -2000);
        Yperso.setDuration(8000);
        Yperso.setInterpolator(new AccelerateInterpolator());
        Yperso.start();

        Yperso.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator Yrebond = ObjectAnimator.ofFloat(perso, "translationY",200);
                Yrebond.setDuration(5000);
                Yrebond.setInterpolator(new BounceInterpolator());
                Yrebond.start();
            }
        });

        Toast.makeText(this, intent.getStringExtra("ageD"), Toast.LENGTH_SHORT).show();
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(fusee,"translationY",fusee.getY(), -2000);

        ObjectAnimator x = ObjectAnimator.ofFloat(fusee,"translationX", fusee.getX(), 1000);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(fusee, "alpha",  1f, 0f);
        fadeOut.setDuration(2000);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(fusee,"scaleX", 1f, 0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(fusee,"scaleY", 1f, 0f);

        animSetXY.playTogether(x, y, scaleX, scaleY);
        animSetXY.setInterpolator(new AccelerateInterpolator());
        animSetXY.setDuration(7000);
        animSetXY.start();

        animSetXY.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {

                Toast.makeText(Paradoxe3.this, String.valueOf(perso.getY()), Toast.LENGTH_LONG).show();

                ObjectAnimator xRetour = ObjectAnimator.ofFloat(fusee, "translationX", fusee.getX(), -75);
                xRetour.setDuration(1000);
                xRetour.start();

                xRetour.addListener(new AnimatorListenerAdapter(){
                    public void onAnimationEnd(Animator animation){
                        ObjectAnimator yRetour = ObjectAnimator.ofFloat(fusee, "translationY",200);
                        ObjectAnimator scaleXRetour = ObjectAnimator.ofFloat(fusee, "scaleX", 0f,1f);
                        ObjectAnimator scaleYRetour = ObjectAnimator.ofFloat(fusee, "scaleY", 0f,1f);
                        AnimatorSet animRetour = new AnimatorSet();
                        animRetour.playTogether(yRetour, scaleXRetour, scaleYRetour);
                        animRetour.setInterpolator(new DecelerateInterpolator());
                        animRetour.setDuration(5000);
                        animRetour.start();

                        animRetour.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                Intent myIntent = new Intent(Paradoxe3.this, Paradoxe4.class);
                                myIntent.putExtra("ageN", intent.getStringExtra("ageN"));
                                myIntent.putExtra("ageD", intent.getStringExtra("ageD"));
                                startActivity(myIntent);
                            }
                        });
                    }
                });
                }
            });
        }}
