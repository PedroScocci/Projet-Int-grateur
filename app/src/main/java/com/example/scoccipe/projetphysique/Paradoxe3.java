package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.animation.Animation;


public class Paradoxe3 extends AppCompatActivity {
    private boolean animationCast = true;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxe3);
        intent = getIntent();

        if(animationCast){
            animationRealise();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 3){
            if(resultCode == MenuPrincipal.RETOUR_MENU_PRINCIPAL) {
                setResult(MenuPrincipal.RETOUR_MENU_PRINCIPAL);
                finish();
            }
        }
    }

    protected void animationRealise() {
        final ImageView fusee = findViewById(R.id.fus);
        final ImageView perso = findViewById(R.id.pers);

        ObjectAnimator Yperso = ObjectAnimator.ofFloat(perso, "translationY", perso.getY(), -250 * Animation.RELATIVE_TO_PARENT);
        Yperso.setDuration(2250);
        Yperso.setInterpolator(new AccelerateInterpolator());
        Yperso.start();

        Yperso.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator Yrebond = ObjectAnimator.ofFloat(perso, "translationY", Animation.RELATIVE_TO_PARENT);
                Yrebond.setDuration(1750);
                Yrebond.setInterpolator(new BounceInterpolator());
                Yrebond.start();
            }
        });
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(fusee,"translationY",fusee.getY(), -750 * Animation.RELATIVE_TO_PARENT);

        ObjectAnimator x = ObjectAnimator.ofFloat(fusee,"translationX", fusee.getX(), 500 * Animation.RELATIVE_TO_PARENT);

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

                ObjectAnimator xRetour = ObjectAnimator.ofFloat(fusee, "translationX", fusee.getX(), Animation.RELATIVE_TO_PARENT);
                xRetour.setDuration(1000);
                xRetour.start();

                xRetour.addListener(new AnimatorListenerAdapter(){
                    public void onAnimationEnd(Animator animation){
                        ObjectAnimator yRetour = ObjectAnimator.ofFloat(fusee, "translationY", Animation.RELATIVE_TO_PARENT);
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
                                animationCast = false;
                                Intent myIntent = new Intent(Paradoxe3.this, Paradoxe4.class);
                                myIntent.putExtra("ageN", intent.getStringExtra("ageN"));
                                myIntent.putExtra("ageD", intent.getStringExtra("ageD"));
                                startActivityForResult(myIntent, 3);
                            }
                        });
                    }
                });
            }
        });
    }
}
