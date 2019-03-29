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
    public Intent intent;
    public boolean derapage;
    public ImageView iv = findViewById(R.id.voit);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force__centripete);

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
                    Path path = new Path();
                    path.addCircle(0,411, 450, Path.Direction.CCW);
                    iv.animate().rotationBy(-360).setDuration(2000);

                    //ObjectAnimator animRot = ObjectAnimator.ofFloat(iv, "rotation", -360);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(iv,View.X,View.Y,path);

                    //AnimatorSet xy = new AnimatorSet();
                    //xy.playTogether(animRot,animator);

                    animator.setDuration(2000).start();
                }
                else{

                }
            }
        });


    }
}
