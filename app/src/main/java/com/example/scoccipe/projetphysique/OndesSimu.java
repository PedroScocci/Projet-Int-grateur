package com.example.scoccipe.projetphysique;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class OndesSimu extends AppCompatActivity {

    private Intent intent;

    private LinearLayout layout;
    private TextView txtTimer, txtProb;
    private Button start;
    private ImageView machine, extremite, cordes[];
    private  Handler customHandler = new Handler();

    private boolean started = false, nonMode = false;
    private long tempsDepart = 0, tempsMiliSecs = 0,tempsTemporaire = 0;
    private int modeStatio, imageActuel[], imageMachine[], sensImage[], reverseImage[], images[], position[], imagesNon[];


    Runnable updateTimerThread = new Runnable() {
        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        @Override
        public void run() {

            tempsMiliSecs = SystemClock.uptimeMillis() - tempsDepart;
            int secs=(int)(tempsMiliSecs/1000);
            int mins= secs/60;
            secs%=60;
            int miliseconds=(int)(tempsMiliSecs%1000);

            txtTimer.setText(""+mins+":"+String.format("%2d",secs) + ":" + String.format("%3d",miliseconds));

            if(!nonMode){
                if(tempsMiliSecs >= tempsTemporaire + 1 && tempsMiliSecs <= tempsTemporaire + 60  ) { //Si le téléphone est performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    for(int i = 0; i < modeStatio; i++) {
                        changerImage(i);
                    }

                } else if(tempsMiliSecs >= tempsTemporaire + 61 && tempsMiliSecs <= tempsTemporaire + 200 ) { //Si le téléphone est plus ou moins performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    for(int i = 0; i < modeStatio; i++) {
                        changerImage(i);
                    }

                } else if(tempsMiliSecs >= tempsTemporaire + 201 && tempsMiliSecs <= tempsTemporaire + 500 ){//Si le téléphone n'est pas très performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    for(int i = 0; i < modeStatio; i++) {
                        changerImage(i);
                    }

                } else if(tempsMiliSecs >= tempsTemporaire + 501 && tempsMiliSecs <= tempsTemporaire + 1500 ) { //Si le téléphone n'est pas performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    for(int i = 0; i < modeStatio; i++) {
                        changerImage(i);
                    }
                }
            } else {
                if(tempsMiliSecs >= tempsTemporaire + 1 && tempsMiliSecs <= tempsTemporaire + 60  ) { //Si le téléphone est performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    changerImageNonMode();

                } else if(tempsMiliSecs >= tempsTemporaire + 61 && tempsMiliSecs <= tempsTemporaire + 200 ) { //Si le téléphone est plus ou moins performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    changerImageNonMode();

                } else if(tempsMiliSecs >= tempsTemporaire + 201 && tempsMiliSecs <= tempsTemporaire + 500 ){//Si le téléphone n'est pas très performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    changerImageNonMode();

                } else if(tempsMiliSecs >= tempsTemporaire + 501 && tempsMiliSecs <= tempsTemporaire + 1500 ) { //Si le téléphone n'est pas performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    changerImageNonMode();
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
        double mode = Double.parseDouble(intent.getStringExtra(OndesParam.MODE_STATIONNAIRE));

        machine = new ImageView(this);
        machine.setImageResource(R.drawable.machine_corde_fermer);
        machine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        extremite = new ImageView(this);
        extremite.setImageResource(R.drawable.extremite_fermer);
        extremite.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        txtTimer = (TextView) findViewById(R.id.timerValue);
        txtProb = (TextView) findViewById(R.id.ondesSimuProb);
        layout = (LinearLayout) findViewById(R.id.cordeLayout);
        layout.addView(machine);

        if(mode == Math.round(mode) && mode > 20) {
            modeStatio = (int)(mode)%2 + 20;
            txtProb.setText(R.string.ondesSimuProb1);
            Toast.makeText(OndesSimu.this, String.valueOf(modeStatio), Toast.LENGTH_LONG).show();
            initialiserVariable(modeStatio);
        } else if(mode == Math.round(mode)){
            modeStatio = (int) mode;
            Toast.makeText(OndesSimu.this, "Nope", Toast.LENGTH_LONG).show();
            initialiserVariable(modeStatio);
        } else {
            nonMode = true;
            initialiserVariable(1);
            txtProb.setText(R.string.ondesSimuProb2);
        }

        layout.addView(extremite);

        start = (Button) findViewById(R.id.bOndesSimu);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtProb.setText("");
                if(!nonMode){
                    if(!started){
                        tempsDepart = SystemClock.uptimeMillis();

                        customHandler.postDelayed(updateTimerThread, 0);

                        start.setText("ARRÊTER");
                        started = true;
                    }
                    else if(started){
                        for(int y = 0; y < modeStatio; y++){
                            cordes[y].setImageResource(images[0]);

                            if(y%2 == 1){
                                reverseImage[y] = 1;
                                imageActuel[y] = 27;
                            }
                            else if(y%2 == 0){
                                reverseImage[y] = 0;
                                imageActuel[y] = 0;
                            }

                            sensImage[y] = 0;
                        }
                        tempsTemporaire = 0;
                        machine.setImageResource(R.drawable.machine_corde_fermer);
                        customHandler.removeCallbacks(updateTimerThread);

                        start.setText("COMMENCER");
                        started = false;
                    }
                } else {
                    if(!started){
                        tempsDepart = SystemClock.uptimeMillis();

                        customHandler.postDelayed(updateTimerThread, 0);

                        start.setText("ARRÊTER");
                        started = true;
                    }
                    else if(started){
                        cordes[0].setImageResource(imagesNon[0]);
                        tempsTemporaire = 0;
                        machine.setImageResource(R.drawable.machine_corde_fermer);
                        customHandler.removeCallbacks(updateTimerThread);

                        start.setText("COMMENCER");
                        started = false;
                    }
                }
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

    public  void changerMachine(){
        machine.setImageResource(imageMachine[position[0]]);
        position[0]++;
        if(position[0] == 12){
            position[0] = 0;
        }
    }

    public void changerImage(int location) {
        if(reverseImage[location] == 0){
            if(sensImage[location] == 0) {
                if (imageActuel[location] < 26) {
                    imageActuel[location]++;
                    cordes[location].setImageResource(images[imageActuel[location]]);
                } else if (imageActuel[location] == 26) {
                    sensImage[location]++;
                    imageActuel[location]--;
                    cordes[location].setImageResource(images[imageActuel[location]]);
                }
            } else if(sensImage[location] == 1) {
                if (imageActuel[location] > 0) {
                    imageActuel[location]--;
                    cordes[location].setImageResource(images[imageActuel[location]]);
                } else if (imageActuel[location] == 0) {
                    sensImage[location]--;
                    reverseImage[location]++;
                    imageActuel[location] = 27;
                    cordes[location].setImageResource(images[imageActuel[location]]);
                }
            }

        } else if(reverseImage[location] == 1){
            if(sensImage[location] == 0) {
                if (imageActuel[location] < 51) {
                    imageActuel[location]++;
                    cordes[location].setImageResource(images[imageActuel[location]]);
                } else if (imageActuel[location] == 51) {
                    sensImage[location]++;
                    imageActuel[location]--;
                    cordes[location].setImageResource(images[imageActuel[location]]);
                }
            } else if(sensImage[location] == 1) {
                if (imageActuel[location] > 27) {
                    imageActuel[location]--;
                    cordes[location].setImageResource(images[imageActuel[location]]);
                } else if (imageActuel[location] == 27) {
                    sensImage[location]--;
                    reverseImage[location]--;
                    imageActuel[location] = 0;
                    cordes[location].setImageResource(images[imageActuel[location]]);
                }
            }
        } else cordes[location].setImageResource(R.drawable.message_erreur);
    }

    public void changerImageNonMode() {
        cordes[0].setImageResource(imagesNon[position[1]]);
        position[1]++;
        if(position[1] == 26) {
            position[1] = 5;
        }
    }

    private void initialiserVariable(int mode) {
        imageActuel = new int[mode];
        sensImage = new int[mode];
        reverseImage = new int[mode];
        cordes = new ImageView[mode];
        position = new int[]{0,0,0};

        for(int i = 0; i < mode; i++) {
            sensImage[i] = 0;

            if(i%2 == 0){
                imageActuel[i] = 0;
                reverseImage[i] = 0;
            } else if(i%2 == 1){
                imageActuel[i] = 27;
                reverseImage[i] = 1;
            }

            cordes[i] = new ImageView(this);
            cordes[i].setImageResource(R.drawable.corde_onde_1);
            cordes[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            layout.addView(cordes[i]);
        }

        images = new int[]{R.drawable.corde_onde_1, R.drawable.corde_onde_2, R.drawable.corde_onde_3, R.drawable.corde_onde_4,  R.drawable.corde_onde_5,
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

        imageMachine = new int[]{R.drawable.machine_corde_ouvert, R.drawable.machine_corde_ouvert_2, R.drawable.machine_corde_ouvert_3,
                R.drawable.machine_corde_ouvert_4, R.drawable.machine_corde_ouvert_3, R.drawable.machine_corde_ouvert_2,
                R.drawable.machine_corde_ouvert, R.drawable.machine_corde_ouvert_r_2, R.drawable.machine_corde_ouvert_r_3,
                R.drawable.machine_corde_ouvert_r_4, R.drawable.machine_corde_ouvert_r_3, R.drawable.machine_corde_ouvert_r_2}; // 12 cases

        imagesNon = new int[]{R.drawable.corde_non_onde, R.drawable.corde_non_onde_2, R.drawable.corde_non_onde_3, R.drawable.corde_non_onde_4,
                R.drawable.corde_non_onde_5, R.drawable.corde_non_onde_6, R.drawable.corde_non_onde_7, R.drawable.corde_non_onde_8,
                R.drawable.corde_non_onde_9, R.drawable.corde_non_onde_10, R.drawable.corde_non_onde_11, R.drawable.corde_non_onde_12,
                R.drawable.corde_non_onde_13, R.drawable.corde_non_onde_14, R.drawable.corde_non_onde_15, R.drawable.corde_non_onde_16,
                R.drawable.corde_non_onde_15, R.drawable.corde_non_onde_14, R.drawable.corde_non_onde_13, R.drawable.corde_non_onde_12,
                R.drawable.corde_non_onde_11, R.drawable.corde_non_onde_10, R.drawable.corde_non_onde_9, R.drawable.corde_non_onde_8,
                R.drawable.corde_non_onde_7, R.drawable.corde_non_onde_6}; //26 cases
    }
}