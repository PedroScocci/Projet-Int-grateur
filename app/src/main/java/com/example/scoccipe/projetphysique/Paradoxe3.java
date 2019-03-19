package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationSet;
import android.webkit.WebView;
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

    private Intent intent;
    WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myBrowser = (WebView)findViewById(R.id.myBrowser);
        myBrowser.loadUrl(("file:///C:/Users/Utilisateur/Desktop/Cegep/Session4/Programmation/Intgration/Logs/WebGl/index.html"));
        myBrowser.getSettings().setJavaScriptEnabled(true);


        setContentView(R.layout.activity_paradoxe3);
        intent = getIntent();

        final ImageView fusee = (ImageView) findViewById(R.id.fus);
        //fusee.setImageResource(R.drawable.fusee);
        Toast.makeText(this, intent.getStringExtra("ageD"), Toast.LENGTH_SHORT).show();
        AnimatorSet animSetXY = new AnimatorSet();

        ObjectAnimator y = ObjectAnimator.ofFloat(fusee,"translationY",fusee.getY(), 2000);

        ObjectAnimator x = ObjectAnimator.ofFloat(fusee,"translationX", fusee.getX(), 1000);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(fusee, "alpha",  1f, 0f);
        fadeOut.setDuration(2000);

        animSetXY.playTogether(x, y, fadeOut);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(7000);
        animSetXY.start();

        animSetXY.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(Paradoxe3.this, "DONE", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(Paradoxe3.this, Paradoxe4.class);
                myIntent.putExtra("ageN", intent.getStringExtra("ageN"));
                myIntent.putExtra("ageD", intent.getStringExtra("ageD"));
                //startActivity(myIntent);
                }
            });

        }}
