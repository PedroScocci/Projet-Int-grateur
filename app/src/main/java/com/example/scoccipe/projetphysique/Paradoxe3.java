package com.example.scoccipe.projetphysique;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.animation.ObjectAnimator;


public class Paradoxe3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradoxe3);
        ImageView fusee = (ImageView) findViewById(R.id.fus);
        fusee.setImageResource(R.drawable.fusee);

        ObjectAnimator animation = ObjectAnimator.ofFloat(fusee, "translationY", 1000f);
        animation.setDuration(5000);
        animation.start();

    }
}
