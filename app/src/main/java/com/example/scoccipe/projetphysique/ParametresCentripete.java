package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ParametresCentripete extends AppCompatActivity {
    public static final int PARAM = 1;
    public static final String PARAMETRES = "param";
    public static final String PARAMETRES2 = "param2";
    public static final String PARAMETRES3 = "param3";
    public static final String PARAMETRES4 = "param4";
    private int route;
    private double masse;
    private double vitesse;
    private double rayon;
    private double coef;
    private double friction;
    private double centripete;
    private boolean derap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_centripete);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.routes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView tv1 = findViewById(R.id.textView2);
        final TextView tv2 = findViewById(R.id.textView3);
        final TextView tv3 = findViewById(R.id.textView4);
        final EditText et1 = findViewById(R.id.edittext1);
        final EditText et2 = findViewById(R.id.edittext2);
        final EditText et3 = findViewById(R.id.edittext3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                switch(pos)
                {
                    case 0: coef = 0.72;
                    route = 0;
                    break;
                    case 1: coef = 0.5;
                    route = 1;
                    break;
                    case 2: coef = 0.725;
                    route = 2;
                    break;
                    case 3: coef = 0.6;
                    route = 3;
                    break;
                    case 4: coef = 0.65;
                    route = 4;
                    break;
                    case 5: coef = 0.35;
                    route = 5;
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    masse = Double.parseDouble(et1.getText().toString());
                    vitesse = (Double.parseDouble(et2.getText().toString()))*3.6;
                    rayon = Double.parseDouble(et3.getText().toString());
                    friction = masse*9.81*coef;
                    centripete = (masse*Math.pow(vitesse,2))/rayon;
                    derap = friction < centripete;
                    tv1.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.noir));
                    tv2.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.noir));
                    tv3.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.noir));

                    Intent intent = new Intent(ParametresCentripete.this, Force_Centripete.class);
                    intent.putExtra(PARAMETRES, derap);
                    intent.putExtra(PARAMETRES2,vitesse);
                    intent.putExtra(PARAMETRES3,rayon);
                    intent.putExtra(PARAMETRES4,route);

                    startActivityForResult(intent, PARAM);
                }
                catch (NumberFormatException e){
                    Toast.makeText(ParametresCentripete.this, "Des paramètres sont manquants", Toast.LENGTH_LONG).show();
                    if(et1.getText().toString().equals(""))
                    {
                        tv1.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.rouge));
                    }
                    else {
                        tv1.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.noir));
                    }
                    if(et2.getText().toString().equals(""))
                    {
                        tv2.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.rouge));
                    }
                    else {
                        tv2.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.noir));
                    }
                    if(et3.getText().toString().equals(""))
                    {
                        tv3.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.rouge));
                    }
                    else {
                        tv3.setTextColor(ContextCompat.getColor(ParametresCentripete.this, R.color.noir));
                    }
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_parametrescentripete, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_principal:
                setResult(MenuPrincipal.RETOUR_MENU_PRINCIPAL);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PARAM){
            if(resultCode == MenuPrincipal.RETOUR_MENU_PRINCIPAL){
                setResult(MenuPrincipal.RETOUR_MENU_PRINCIPAL);
                finish();
            }
        }
    }
}
