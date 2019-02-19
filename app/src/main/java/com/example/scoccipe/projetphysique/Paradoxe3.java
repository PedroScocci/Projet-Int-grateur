package com.example.scoccipe.projetphysique;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Animator;
import android.widget.Toast;
import android.animation.AnimatorSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


public class Paradoxe3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxe3);
        final ImageView fusee = (ImageView) findViewById(R.id.fus);
        //fusee.setImageResource(R.drawable.fusee);

        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(fusee,"translationY",fusee.getY(), 2000);

        ObjectAnimator x = ObjectAnimator.ofFloat(fusee,"translationX", fusee.getX(), 1000);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(fusee, "alpha",  1f, 0f);
        fadeOut.setDuration(2000);

        animSetXY.playTogether(x, y, fadeOut);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(7000);
        animSetXY.start();

        /*ObjectAnimator animation = ObjectAnimator.ofFloat(fusee, "translationY", 2000f);
        animation.setDuration(5000);
        animation.start();

        animation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(Paradoxe3.this, "DONE", Toast.LENGTH_LONG).show();
                ObjectAnimator animation2 = ObjectAnimator.ofFloat(fusee, "translationX", 1200f);
                animation2.setDuration(5000);
                animation2.start();
                }
            });*/

        }}
