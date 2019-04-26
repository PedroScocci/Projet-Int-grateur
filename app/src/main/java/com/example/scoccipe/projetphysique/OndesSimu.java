package com.example.scoccipe.projetphysique;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OndesSimu extends AppCompatActivity {

    private LinearLayout layout;
    private TextView txtProb;
    private Button start;
    private ImageView machine, extremite, cordes[];
    private  Handler customHandler = new Handler();

    private String extrem;
    private boolean started = false, nonMode = false;
    private long tempsDepart = 0,tempsTemporaire = 0;
    private int modeStatio, imageActuel[], imageMachine[], sensImage[], reverseImage[], images[], imagesSemiOuvert[], position[], imagesNon[],
            imagesExtremites[];


    Runnable updateTimerThread = new Runnable() {
        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        @Override
        public void run() {

            long tempsMiliSecs = SystemClock.uptimeMillis() - tempsDepart;

            if(!nonMode){
                if(tempsMiliSecs >= tempsTemporaire + 1 && tempsMiliSecs <= tempsTemporaire + 60  ) { //Si le téléphone est performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    for(int i = 0; i < modeStatio; i++) {
                        if(modeStatio - 1 == i && extrem.equals("Semi-ouvert")){
                            changerImage(i , imagesSemiOuvert);
                            changerImageExtremite(i+1);
                        } else{
                            changerImage(i, images);
                        }
                    }

                } else if(tempsMiliSecs >= tempsTemporaire + 61 && tempsMiliSecs <= tempsTemporaire + 200 ) { //Si le téléphone est plus ou moins performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    for(int i = 0; i < modeStatio; i++) {
                        if(modeStatio - 1 == i && extrem.equals("Semi-ouvert")){
                            changerImage(i , imagesSemiOuvert);
                            changerImageExtremite(i+1);
                        } else{
                            changerImage(i, images);
                        }
                    }

                } else if(tempsMiliSecs >= tempsTemporaire + 201 && tempsMiliSecs <= tempsTemporaire + 500 ){//Si le téléphone n'est pas très performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    for(int i = 0; i < modeStatio; i++) {
                        if(modeStatio - 1 == i && extrem.equals("Semi-ouvert")){
                            changerImage(i , imagesSemiOuvert);
                            changerImageExtremite(i+1);
                        } else{
                            changerImage(i, images);
                        }
                    }

                } else if(tempsMiliSecs >= tempsTemporaire + 501 && tempsMiliSecs <= tempsTemporaire + 1500 ) { //Si le téléphone n'est pas performant
                    tempsTemporaire = tempsMiliSecs;
                    changerMachine();
                    for(int i = 0; i < modeStatio; i++) {
                        if(modeStatio - 1 == i && extrem.equals("Semi-ouvert")){
                            changerImage(i , imagesSemiOuvert);
                            changerImageExtremite(i+1);
                        } else{
                            changerImage(i, images);
                        }
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_simu);

        Intent intent = getIntent();
        double mode = Double.parseDouble(intent.getStringExtra(OndesParam.MODE_STATIONNAIRE));
        extrem = intent.getStringExtra(OndesParam.EXTREMITES);

        TextView textModeStatio = findViewById(R.id.ondesSimuModeStatio);
        textModeStatio.setText(getText(R.string.ondesSimuModeStatio) + " " + intent.getStringExtra(OndesParam.MODE_STATIONNAIRE));
        TextView textVentres = findViewById(R.id.ondesSimuVentres);
        if(mode == Math.round(mode)) {
            textVentres.setText(getText(R.string.ondesSimuNbVentres) + " " + intent.getStringExtra(OndesParam.MODE_STATIONNAIRE));
        } else {
            textVentres.setText(getText(R.string.ondesSimuNbVentres) + " 0");
        }
        TextView textouverture = findViewById(R.id.ondesSimuOuverture);
        textouverture.setText(getText(R.string.ondesSimuOuverture) + " " + intent.getStringExtra(OndesParam.EXTREMITES));

        machine = new ImageView(this);
        machine.setImageResource(R.drawable.machine_corde_fermer);
        machine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        extremite = new ImageView(this);

        txtProb = findViewById(R.id.ondesSimuProb);
        layout = findViewById(R.id.cordeLayout);
        layout.addView(machine);

        if(extrem.equals("Semi-ouvert")) {
            if(mode == Math.round(mode) && mode > 14) {
                modeStatio = (int)(mode)%2 + 14;
                txtProb.setText(R.string.ondesSimuProb1);
                initialiserVariableSemiOuvert(modeStatio);
            } else if(mode == Math.round(mode)){
                modeStatio = (int) mode;
                initialiserVariableSemiOuvert(modeStatio);
            } else {
                nonMode = true;
                initialiserVariableNonMode();
                txtProb.setText(R.string.ondesSimuProb2);
            }

            extremite.setImageResource(R.drawable.extremite_semi_fermer_1);
            extremite.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        } else if(extrem.equals("Fermé")) {
            if(mode == Math.round(mode) && mode > 14) {
                modeStatio = (int)(mode)%2 + 14;
                txtProb.setText(R.string.ondesSimuProb1);
                initialiserVariableFermer(modeStatio);
            } else if(mode == Math.round(mode)){
                modeStatio = (int) mode;
                initialiserVariableFermer(modeStatio);
            } else {
                nonMode = true;
                initialiserVariableNonMode();
                txtProb.setText(R.string.ondesSimuProb2);
            }

            extremite.setImageResource(R.drawable.extremite_fermer);
            extremite.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        }


        layout.addView(extremite);

        start = findViewById(R.id.bOndesSimu);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtProb.setText("");
                if(!nonMode){
                    if(!started){
                        tempsDepart = SystemClock.uptimeMillis();

                        customHandler.postDelayed(updateTimerThread, 0);

                        start.setText(R.string.boutonArreter);
                        started = true;
                    }
                    else {
                        for(int y = 0; y < modeStatio; y++){
                            cordes[y].setImageResource(images[0]);

                            if(y%2 == 1){
                                imageActuel[y] = 27;
                                reverseImage[y] = 1;
                            }
                            else if(y%2 == 0){
                                imageActuel[y] = 0;
                                reverseImage[y] = 0;
                            }

                            sensImage[y] = 0;
                        }
                        tempsTemporaire = 0;
                        machine.setImageResource(R.drawable.machine_corde_fermer);
                        if(extrem.equals("Semi-ouvert")){
                            extremite.setImageResource(R.drawable.extremite_semi_fermer_1);
                            sensImage[modeStatio]=0;
                            imageActuel[modeStatio] = imageActuel[modeStatio-1];
                            reverseImage[modeStatio] = reverseImage[modeStatio-1];
                        }
                        customHandler.removeCallbacks(updateTimerThread);

                        start.setText(R.string.boutonCommencer);
                        started = false;
                    }
                } else {
                    if(!started){
                        tempsDepart = SystemClock.uptimeMillis();

                        customHandler.postDelayed(updateTimerThread, 0);

                        start.setText(R.string.boutonArreter);
                        started = true;
                    }
                    else {
                        cordes[0].setImageResource(imagesNon[0]);
                        tempsTemporaire = 0;
                        machine.setImageResource(R.drawable.machine_corde_fermer);
                        customHandler.removeCallbacks(updateTimerThread);

                        start.setText(R.string.boutonCommencer);
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

    public void changerImage(int location, int tabImages[]) {
        if(reverseImage[location] == 0){
            if(sensImage[location] == 0) {
                if (imageActuel[location] < 25) {
                    imageActuel[location]++;
                    cordes[location].setImageResource(tabImages[imageActuel[location]]);
                } else if (imageActuel[location] == 25) {
                    sensImage[location]++;
                    imageActuel[location]--;
                    cordes[location].setImageResource(tabImages[imageActuel[location]]);
                }
            } else if(sensImage[location] == 1) {
                if (imageActuel[location] > 0) {
                    imageActuel[location]--;
                    cordes[location].setImageResource(tabImages[imageActuel[location]]);
                } else if (imageActuel[location] == 0) {
                    sensImage[location]--;
                    reverseImage[location]++;
                    imageActuel[location] = 26;
                    cordes[location].setImageResource(tabImages[imageActuel[location]]);
                }
            }

        } else if(reverseImage[location] == 1){
            if(sensImage[location] == 0) {
                if (imageActuel[location] < 50) {
                    imageActuel[location]++;
                    cordes[location].setImageResource(tabImages[imageActuel[location]]);
                } else if (imageActuel[location] == 50) {
                    sensImage[location]++;
                    imageActuel[location]--;
                    cordes[location].setImageResource(tabImages[imageActuel[location]]);
                }
            } else if(sensImage[location] == 1) {
                if (imageActuel[location] > 26) {
                    imageActuel[location]--;
                    cordes[location].setImageResource(tabImages[imageActuel[location]]);
                } else if (imageActuel[location] == 26) {
                    sensImage[location]--;
                    reverseImage[location]--;
                    imageActuel[location] = 0;
                    cordes[location].setImageResource(tabImages[imageActuel[location]]);
                }
            }
        } else cordes[location].setImageResource(R.drawable.message_erreur);
    }

    public void changerImageExtremite(int location){
        if(reverseImage[location] == 0){
            if(sensImage[location] == 0) {
                if (imageActuel[location] < 25) {
                    imageActuel[location]++;
                    extremite.setImageResource(imagesExtremites[imageActuel[location]]);
                } else if (imageActuel[location] == 25) {
                    sensImage[location]++;
                    imageActuel[location]--;
                    extremite.setImageResource(imagesExtremites[imageActuel[location]]);
                }
            } else if(sensImage[location] == 1) {
                if (imageActuel[location] > 0) {
                    imageActuel[location]--;
                    extremite.setImageResource(imagesExtremites[imageActuel[location]]);
                } else if (imageActuel[location] == 0) {
                    sensImage[location]--;
                    reverseImage[location]++;
                    imageActuel[location] = 26;
                    extremite.setImageResource(imagesExtremites[imageActuel[location]]);
                }
            }

        } else if(reverseImage[location] == 1){
            if(sensImage[location] == 0) {
                if (imageActuel[location] < 50) {
                    imageActuel[location]++;
                    extremite.setImageResource(imagesExtremites[imageActuel[location]]);
                } else if (imageActuel[location] == 50) {
                    sensImage[location]++;
                    imageActuel[location]--;
                    extremite.setImageResource(imagesExtremites[imageActuel[location]]);
                }
            } else if(sensImage[location] == 1) {
                if (imageActuel[location] > 26) {
                    imageActuel[location]--;
                    extremite.setImageResource(imagesExtremites[imageActuel[location]]);
                } else if (imageActuel[location] == 26) {
                    sensImage[location]--;
                    reverseImage[location]--;
                    imageActuel[location] = 0;
                    extremite.setImageResource(imagesExtremites[imageActuel[location]]);
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

    private void initialiserVariableFermer(int mode) {
        imageActuel = new int[mode];
        sensImage = new int[mode];
        reverseImage = new int[mode];
        cordes = new ImageView[mode];
        position = new int[]{0};

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
                R.drawable.corde_onde_r_2, R.drawable.corde_onde_r_3, R.drawable.corde_onde_r_4, R.drawable.corde_onde_r_5,
                R.drawable.corde_onde_r_6, R.drawable.corde_onde_r_7, R.drawable.corde_onde_r_8, R.drawable.corde_onde_r_9, R.drawable.corde_onde_r_10,
                R.drawable.corde_onde_r_11, R.drawable.corde_onde_r_12, R.drawable.corde_onde_r_13, R.drawable.corde_onde_r_14, R.drawable.corde_onde_r_15,
                R.drawable.corde_onde_r_16, R.drawable.corde_onde_r_17, R.drawable.corde_onde_r_18, R.drawable.corde_onde_r_19, R.drawable.corde_onde_r_20,
                R.drawable.corde_onde_r_21, R.drawable.corde_onde_r_22, R.drawable.corde_onde_r_23, R.drawable.corde_onde_r_24, R.drawable.corde_onde_r_25,
                R.drawable.corde_onde_r_26}; // 51 cases

        imageMachine = new int[]{R.drawable.machine_corde_ouvert, R.drawable.machine_corde_ouvert_2, R.drawable.machine_corde_ouvert_3,
                R.drawable.machine_corde_ouvert_4, R.drawable.machine_corde_ouvert_3, R.drawable.machine_corde_ouvert_2,
                R.drawable.machine_corde_ouvert, R.drawable.machine_corde_ouvert_r_2, R.drawable.machine_corde_ouvert_r_3,
                R.drawable.machine_corde_ouvert_r_4, R.drawable.machine_corde_ouvert_r_3, R.drawable.machine_corde_ouvert_r_2}; // 12 cases
    }

    private void initialiserVariableSemiOuvert(int mode) {
        imageActuel = new int[mode+1];
        sensImage = new int[mode+1];
        reverseImage = new int[mode+1];
        cordes = new ImageView[mode];
        position = new int[]{0};

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

        imageActuel[mode] = imageActuel[mode-1];
        reverseImage[mode] = reverseImage[mode-1];
        sensImage[mode] = 0;

        images = new int[]{R.drawable.corde_onde_1, R.drawable.corde_onde_2, R.drawable.corde_onde_3, R.drawable.corde_onde_4,  R.drawable.corde_onde_5,
                R.drawable.corde_onde_6, R.drawable.corde_onde_7, R.drawable.corde_onde_8, R.drawable.corde_onde_9, R.drawable.corde_onde_10, R.drawable.corde_onde_11,
                R.drawable.corde_onde_12, R.drawable.corde_onde_13, R.drawable.corde_onde_14, R.drawable.corde_onde_15, R.drawable.corde_onde_16,
                R.drawable.corde_onde_17, R.drawable.corde_onde_18, R.drawable.corde_onde_19, R.drawable.corde_onde_20, R.drawable.corde_onde_21,
                R.drawable.corde_onde_22, R.drawable.corde_onde_23, R.drawable.corde_onde_24, R.drawable.corde_onde_25, R.drawable.corde_onde_26,
                R.drawable.corde_onde_r_2, R.drawable.corde_onde_r_3, R.drawable.corde_onde_r_4, R.drawable.corde_onde_r_5,
                R.drawable.corde_onde_r_6, R.drawable.corde_onde_r_7, R.drawable.corde_onde_r_8, R.drawable.corde_onde_r_9, R.drawable.corde_onde_r_10,
                R.drawable.corde_onde_r_11, R.drawable.corde_onde_r_12, R.drawable.corde_onde_r_13, R.drawable.corde_onde_r_14, R.drawable.corde_onde_r_15,
                R.drawable.corde_onde_r_16, R.drawable.corde_onde_r_17, R.drawable.corde_onde_r_18, R.drawable.corde_onde_r_19, R.drawable.corde_onde_r_20,
                R.drawable.corde_onde_r_21, R.drawable.corde_onde_r_22, R.drawable.corde_onde_r_23, R.drawable.corde_onde_r_24, R.drawable.corde_onde_r_25,
                R.drawable.corde_onde_r_26}; // 51 cases

        imagesSemiOuvert = new int[]{R.drawable.corde_onde_1, R.drawable.corde_demi_onde_2, R.drawable.corde_demi_onde_3, R.drawable.corde_demi_onde_4,  R.drawable.corde_demi_onde_5,
                R.drawable.corde_demi_onde_6, R.drawable.corde_demi_onde_7, R.drawable.corde_demi_onde_8, R.drawable.corde_demi_onde_9, R.drawable.corde_demi_onde_10, R.drawable.corde_demi_onde_11,
                R.drawable.corde_demi_onde_12, R.drawable.corde_demi_onde_13, R.drawable.corde_demi_onde_14, R.drawable.corde_demi_onde_15, R.drawable.corde_demi_onde_16,
                R.drawable.corde_demi_onde_17, R.drawable.corde_demi_onde_18, R.drawable.corde_demi_onde_19, R.drawable.corde_demi_onde_20, R.drawable.corde_demi_onde_21,
                R.drawable.corde_demi_onde_22, R.drawable.corde_demi_onde_23, R.drawable.corde_demi_onde_24, R.drawable.corde_demi_onde_25, R.drawable.corde_demi_onde_26,
                R.drawable.corde_demi_onde_r_2, R.drawable.corde_demi_onde_r_3, R.drawable.corde_demi_onde_r_4, R.drawable.corde_demi_onde_r_5,
                R.drawable.corde_demi_onde_r_6, R.drawable.corde_demi_onde_r_7, R.drawable.corde_demi_onde_r_8, R.drawable.corde_demi_onde_r_9, R.drawable.corde_demi_onde_r_10,
                R.drawable.corde_demi_onde_r_11, R.drawable.corde_demi_onde_r_12, R.drawable.corde_demi_onde_r_13, R.drawable.corde_demi_onde_r_14, R.drawable.corde_demi_onde_r_15,
                R.drawable.corde_demi_onde_r_16, R.drawable.corde_demi_onde_r_17, R.drawable.corde_demi_onde_r_18, R.drawable.corde_demi_onde_r_19, R.drawable.corde_demi_onde_r_20,
                R.drawable.corde_demi_onde_r_21, R.drawable.corde_demi_onde_r_22, R.drawable.corde_demi_onde_r_23, R.drawable.corde_demi_onde_r_24, R.drawable.corde_demi_onde_r_25,
                R.drawable.corde_demi_onde_r_26}; // 51 cases

        imageMachine = new int[]{R.drawable.machine_corde_ouvert, R.drawable.machine_corde_ouvert_2, R.drawable.machine_corde_ouvert_3,
                R.drawable.machine_corde_ouvert_4, R.drawable.machine_corde_ouvert_3, R.drawable.machine_corde_ouvert_2,
                R.drawable.machine_corde_ouvert, R.drawable.machine_corde_ouvert_r_2, R.drawable.machine_corde_ouvert_r_3,
                R.drawable.machine_corde_ouvert_r_4, R.drawable.machine_corde_ouvert_r_3, R.drawable.machine_corde_ouvert_r_2}; // 12 cases

        imagesExtremites = new int[]{R.drawable.extremite_semi_fermer_1, R.drawable.extremite_semi_fermer_2, R.drawable.extremite_semi_fermer_3,
                R.drawable.extremite_semi_fermer_4, R.drawable.extremite_semi_fermer_5, R.drawable.extremite_semi_fermer_6, R.drawable.extremite_semi_fermer_7,
                R.drawable.extremite_semi_fermer_8, R.drawable.extremite_semi_fermer_9, R.drawable.extremite_semi_fermer_10, R.drawable.extremite_semi_fermer_11,
                R.drawable.extremite_semi_fermer_12, R.drawable.extremite_semi_fermer_13, R.drawable.extremite_semi_fermer_14, R.drawable.extremite_semi_fermer_15,
                R.drawable.extremite_semi_fermer_16, R.drawable.extremite_semi_fermer_17, R.drawable.extremite_semi_fermer_18, R.drawable.extremite_semi_fermer_19,
                R.drawable.extremite_semi_fermer_20, R.drawable.extremite_semi_fermer_21, R.drawable.extremite_semi_fermer_22, R.drawable.extremite_semi_fermer_23,
                R.drawable.extremite_semi_fermer_24, R.drawable.extremite_semi_fermer_25, R.drawable.extremite_semi_fermer_26, R.drawable.extremite_semi_fermer_r_2,
                R.drawable.extremite_semi_fermer_r_3, R.drawable.extremite_semi_fermer_r_4, R.drawable.extremite_semi_fermer_r_5, R.drawable.extremite_semi_fermer_r_6,
                R.drawable.extremite_semi_fermer_r_7, R.drawable.extremite_semi_fermer_r_8, R.drawable.extremite_semi_fermer_r_9, R.drawable.extremite_semi_fermer_r_10,
                R.drawable.extremite_semi_fermer_r_11, R.drawable.extremite_semi_fermer_r_12, R.drawable.extremite_semi_fermer_r_13, R.drawable.extremite_semi_fermer_r_14,
                R.drawable.extremite_semi_fermer_r_15, R.drawable.extremite_semi_fermer_r_16, R.drawable.extremite_semi_fermer_r_17, R.drawable.extremite_semi_fermer_r_18,
                R.drawable.extremite_semi_fermer_r_19, R.drawable.extremite_semi_fermer_r_20, R.drawable.extremite_semi_fermer_r_21, R.drawable.extremite_semi_fermer_r_22,
                R.drawable.extremite_semi_fermer_r_23, R.drawable.extremite_semi_fermer_r_24, R.drawable.extremite_semi_fermer_r_25, R.drawable.extremite_semi_fermer_r_26}; //51 cases
    }

    private void initialiserVariableNonMode() {
        imageActuel = new int[]{0};
        sensImage = new int[]{0};
        reverseImage = new int[]{0};
        cordes = new ImageView[1];
        position = new int[]{0,0};


        cordes[0] = new ImageView(this);
        cordes[0].setImageResource(R.drawable.corde_onde_1);
        cordes[0].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        layout.addView(cordes[0]);

        imageMachine = new int[]{R.drawable.machine_corde_ouvert, R.drawable.machine_corde_ouvert_2, R.drawable.machine_corde_ouvert_3,
                R.drawable.machine_corde_ouvert_4, R.drawable.machine_corde_ouvert_3, R.drawable.machine_corde_ouvert_2,
                R.drawable.machine_corde_ouvert, R.drawable.machine_corde_ouvert_r_2, R.drawable.machine_corde_ouvert_r_3,
                R.drawable.machine_corde_ouvert_r_4, R.drawable.machine_corde_ouvert_r_3, R.drawable.machine_corde_ouvert_r_2}; // 12 cases

        imagesNon = new int[]{R.drawable.corde_onde_1, R.drawable.corde_non_onde_2, R.drawable.corde_non_onde_3, R.drawable.corde_non_onde_4,
                R.drawable.corde_non_onde_5, R.drawable.corde_non_onde_6, R.drawable.corde_non_onde_7, R.drawable.corde_non_onde_8,
                R.drawable.corde_non_onde_9, R.drawable.corde_non_onde_10, R.drawable.corde_non_onde_11, R.drawable.corde_non_onde_12,
                R.drawable.corde_non_onde_13, R.drawable.corde_non_onde_14, R.drawable.corde_non_onde_15, R.drawable.corde_non_onde_16,
                R.drawable.corde_non_onde_15, R.drawable.corde_non_onde_14, R.drawable.corde_non_onde_13, R.drawable.corde_non_onde_12,
                R.drawable.corde_non_onde_11, R.drawable.corde_non_onde_10, R.drawable.corde_non_onde_9, R.drawable.corde_non_onde_8,
                R.drawable.corde_non_onde_7, R.drawable.corde_non_onde_6}; //26 cases
    }
}