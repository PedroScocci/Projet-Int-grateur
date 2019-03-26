package com.example.scoccipe.projetphysique;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Force_Centripete extends AppCompatActivity {
    public Intent intent;
    public boolean derapage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force__centripete);

        float x = 500, y = 500, r = 500, angle = 50;

        intent = getIntent();
        derapage = intent.getBooleanExtra("derap", ParametresCentripete.derap);
        if(derapage) {
            Toast.makeText(Force_Centripete.this,"feef" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(Force_Centripete.this,"deg" , Toast.LENGTH_LONG).show();
        }

        Button b = findViewById(R.id.buttonsim);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(derapage){
                    ImageView iv = findViewById(R.id.voit);
                    Path path = new Path();
                    iv.animate().rotation(-360);
                    iv.animate().setDuration(10000);

                    path.arcTo(0f,30f,200f,1050f,-90f,-359,true);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(iv,View.X,View.Y,path);
                    animator.setDuration(10000);
                    animator.start();
                }
                else{

                }
            }
        });


    }
}
