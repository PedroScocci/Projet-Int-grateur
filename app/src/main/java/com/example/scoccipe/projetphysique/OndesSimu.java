package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class OndesSimu extends AppCompatActivity {

    private Intent intent;
    private int images[];
    private double mode;
    private boolean started = false;
    private long temps,tempsPasser;
    private Button start;
    private ImageView corde;

    private  Handler customHandler = new Handler();
    private long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff=0L,updateTime=0L;
    private long tempSec = 0;
    private int imageActuel = 0, sensImage = 0, reverseImage = 0;
    TextView txtTimer;

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() -startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs=(int)(updateTime/1000);
            int mins= secs/60;
            secs%=60;
            int miliseconds=(int)(updateTime%1000);

            txtTimer.setText(""+mins+":"+String.format("%2d",secs) + ":" + String.format("%3d",miliseconds));

            if(secs == (tempSec + 1)){
                tempSec = secs;

                if(reverseImage == 0){

                    if(sensImage == 0) {
                        if (imageActuel < 26) {
                            imageActuel++;
                            corde.setImageResource(images[imageActuel]);
                        } else if (imageActuel == 26) {
                            sensImage++;
                            imageActuel--;
                            corde.setImageResource(images[imageActuel]);
                        }
                    } else if(sensImage == 1) {
                        if (imageActuel > 0) {
                            imageActuel--;
                            corde.setImageResource(images[imageActuel]);
                        } else if (imageActuel == 0) {
                            sensImage--;
                            reverseImage++;
                            imageActuel = 27;
                            corde.setImageResource(images[imageActuel]);
                        }
                    }

                } else if(reverseImage == 1){

                    if(sensImage == 0) {
                        if (imageActuel < 51) {
                            imageActuel++;
                            corde.setImageResource(images[imageActuel]);
                        } else if (imageActuel == 51) {
                            sensImage++;
                            imageActuel--;
                            corde.setImageResource(images[imageActuel]);
                        }
                    } else if(sensImage == 1) {
                        if (imageActuel > 0) {
                            imageActuel--;
                            corde.setImageResource(images[imageActuel]);
                        } else if (imageActuel == 0) {
                            sensImage--;
                            reverseImage++;
                            imageActuel = 27;
                            corde.setImageResource(images[imageActuel]);
                        }
                    }

                } else {
                    corde.setImageResource(R.drawable.message_erreur);
                }


            }

            customHandler.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_simu);

        intent = getIntent();
        mode = Double.parseDouble(intent.getStringExtra(OndesParam.MODE_STATIONNAIRE));

        Toast.makeText(OndesSimu.this, String.valueOf(mode), Toast.LENGTH_LONG).show();
        corde = (ImageView) findViewById(R.id.cordeAnimation);

        images = new int[]{R.drawable.corde_onde_1,R.drawable.corde_onde_2,R.drawable.corde_onde_3, R.drawable.corde_onde_4,  R.drawable.corde_onde_5,
                R.drawable.corde_onde_6, R.drawable.corde_onde_7, R.drawable.corde_onde_8, R.drawable.corde_onde_9, R.drawable.corde_onde_10, R.drawable.corde_onde_11,
                R.drawable.corde_onde_12, R.drawable.corde_onde_13, R.drawable.corde_onde_14, R.drawable.corde_onde_15, R.drawable.corde_onde_16,
                R.drawable.corde_onde_17, R.drawable.corde_onde_18, R.drawable.corde_onde_19, R.drawable.corde_onde_20, R.drawable.corde_onde_21,
                R.drawable.corde_onde_22, R.drawable.corde_onde_23, R.drawable.corde_onde_24, R.drawable.corde_onde_25, R.drawable.corde_onde_26,
                R.drawable.corde_onde_27, R.drawable.corde_onde_r_2, R.drawable.corde_onde_r_3, R.drawable.corde_onde_r_4, R.drawable.corde_onde_r_5,
                R.drawable.corde_onde_r_6, R.drawable.corde_onde_r_7, R.drawable.corde_onde_r_8, R.drawable.corde_onde_r_9, R.drawable.corde_onde_r_10,
                R.drawable.corde_onde_r_11, R.drawable.corde_onde_r_12, R.drawable.corde_onde_r_13, R.drawable.corde_onde_r_14, R.drawable.corde_onde_r_15,
                R.drawable.corde_onde_r_16, R.drawable.corde_onde_r_17, R.drawable.corde_onde_r_18, R.drawable.corde_onde_r_19, R.drawable.corde_onde_r_20,
                R.drawable.corde_onde_r_21, R.drawable.corde_onde_r_22, R.drawable.corde_onde_r_23, R.drawable.corde_onde_r_24, R.drawable.corde_onde_r_25,
                R.drawable.corde_onde_r_26}; // 52 cases

        txtTimer = (TextView) findViewById(R.id.timerValue);


        start = (Button) findViewById(R.id.bOndesSimu);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!started){
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);

                    //corde.setImageResource(images[26]);

                    start.setText("ARRÊTER");
                    started = true;
                }
                else if(started){

                    corde.setImageResource(images[0]);
                    imageActuel = 0;
                    sensImage = 0;
                    reverseImage = 0;
                    tempSec = 0;
                    customHandler.removeCallbacks(updateTimerThread);

                    //Toast.makeText(OndesSimu.this, txtTimer.getText().toString(), Toast.LENGTH_LONG).show();


                    start.setText("COMMENCER");
                    started = false;
                }
                /*while (!started){
                    for(int i = 1; i<=27; i++)
                    {
                        String fileName = "drawable/corde_onde_" + i + ".png";
                        int imageResource = getResources().getIdentifier(fileName, null, getPackageName());
                        ImageView bob = (ImageView) findViewById(R.id.cordeAnimation);
                        Drawable image = getResources().getDrawable(imageResource);
                        bob.setImageDrawable(image);
                    }
                    for(int i = 27; i<=1; i--)
                    {
                        String fileName = "drawable/corde_onde_" + i + ".png";
                        int imageResource = getResources().getIdentifier(fileName, null, getPackageName());
                        ImageView bob = (ImageView) findViewById(R.id.cordeAnimation);
                        Drawable image = getResources().getDrawable(imageResource);
                        bob.setImageDrawable(image);
                    }
                    for(int i = 1; i<=27; i++)
                    {
                        String fileName = "drawable/corde_onde_r_" + i + ".png";
                        int imageResource = getResources().getIdentifier(fileName, null, getPackageName());
                        ImageView bob = (ImageView) findViewById(R.id.cordeAnimation);
                        Drawable image = getResources().getDrawable(imageResource);
                        bob.setImageDrawable(image);
                    }
                    for(int i = 27; i<=1; i--)
                    {
                        String fileName = "drawable/corde_onde_r_" + i + ".png";
                        int imageResource = getResources().getIdentifier(fileName, null, getPackageName());
                        ImageView bob = (ImageView) findViewById(R.id.cordeAnimation);
                        Drawable image = getResources().getDrawable(imageResource);
                        bob.setImageDrawable(image);
                    }
                }*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ondes_simu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iOndesMenu:
                setResult(MenuPrincipal.RETOUR_MENU_PRINCIPAL);
                finish();
                return true;

            case R.id.iOndesParam:
                setResult(RESULT_OK);
                finish();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

}
