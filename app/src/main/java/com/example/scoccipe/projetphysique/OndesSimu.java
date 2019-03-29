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
    long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff=0L,updateTime=0L;
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

        images = new int[]{R.drawable.corde_onde_1, R.drawable.corde_onde_27, R.drawable.corde_onde_r_26};



        txtTimer = (TextView) findViewById(R.id.timerValue);


        start = (Button) findViewById(R.id.bOndesSimu);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!started){
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);


                    start.setText("ARRÊTER");
                    started = true;
                }
                else if(started){

                    customHandler.removeCallbacks(updateTimerThread);

                    Toast.makeText(OndesSimu.this, txtTimer.getText().toString(), Toast.LENGTH_LONG).show();

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
