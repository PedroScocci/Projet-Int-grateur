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
import android.widget.Toast;

import java.util.Timer;

public class OndesSimu extends AppCompatActivity {

    private Intent intent;
    private int images[];
    private double mode;
    private Chronometer time;
    private boolean started = false;
    private long temps,tempsPasser;
    private Button start;
    ImageView corde;
    Handler custom = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_simu);

        intent = getIntent();
        mode = Double.parseDouble(intent.getStringExtra(OndesParam.MODE_STATIONNAIRE));

        Toast.makeText(OndesSimu.this, String.valueOf(mode), Toast.LENGTH_LONG).show();

        time = (Chronometer) findViewById(R.id.timer);
        corde = (ImageView) findViewById(R.id.cordeAnimation);

        images = new int[]{R.drawable.corde_onde_1, R.drawable.corde_onde_27, R.drawable.corde_onde_r_26};

        start = (Button) findViewById(R.id.bOndesSimu);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!started){
                    

                    start.setText("ARRÊTER");
                }
                else if(started){

                    start.setText("COMMENCER");

                    Toast.makeText(OndesSimu.this, String.valueOf(time.getBase()), Toast.LENGTH_LONG).show();
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

    void chronoCommencer(){
        started = true;

        time.setBase(SystemClock.elapsedRealtime());
        time.start();

        //tempsPasser = SystemClock.elapsedRealtime();

        corde.setImageResource(images[1]);
    }

    void chronoArreter(){
        started = false;

        time.stop();
    }


}
