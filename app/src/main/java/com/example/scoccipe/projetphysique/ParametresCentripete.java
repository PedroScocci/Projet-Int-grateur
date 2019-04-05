package com.example.scoccipe.projetphysique;

import android.content.Intent;
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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.routes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView tv1 = (TextView) findViewById(R.id.textView2);
        final TextView tv2 = (TextView) findViewById(R.id.textView3);
        final TextView tv3 = (TextView) findViewById(R.id.textView4);
        final TextView tv4 = (TextView) findViewById(R.id.textView5);
        final EditText et1 = (EditText) findViewById(R.id.edittext1);
        final EditText et2 = (EditText) findViewById(R.id.edittext2);
        final EditText et3 = (EditText) findViewById(R.id.edittext3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                switch(pos)
                {
                    case 0: coef = 0.2;
                    break;
                    case 1: coef = 0.899;
                    break;
                    case 2: coef = 0.876;
                    break;
                    case 3: coef = 0.745;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    masse = Double.parseDouble(et1.getText().toString());
                    vitesse = (Double.parseDouble(et2.getText().toString()))*3.6;
                    rayon = Double.parseDouble(et3.getText().toString());
                    friction = masse*9.81*coef;
                    centripete = (masse*Math.pow(vitesse,2))/rayon;
                    if(friction < centripete) {
                        derap = true;
                    }
                    else {
                        derap = false;

                    }
                    tv1.setTextColor(getResources().getColor(R.color.noir));
                    tv2.setTextColor(getResources().getColor(R.color.noir));
                    tv3.setTextColor(getResources().getColor(R.color.noir));

                    Intent intent = new Intent(ParametresCentripete.this, Force_Centripete.class);
                    intent.putExtra(PARAMETRES, derap);
                    intent.putExtra(PARAMETRES2,String.valueOf(vitesse));
                    intent.putExtra(PARAMETRES3,String.valueOf(rayon));

                    startActivityForResult(intent, PARAM);
                }
                catch (NumberFormatException e){
                    Toast.makeText(ParametresCentripete.this, "Des paramètres sont manquants", Toast.LENGTH_LONG).show();
                    if(et1.getText().toString().equals(""))
                    {
                        tv1.setTextColor(getResources().getColor(R.color.rouge));
                    }
                    else {
                        tv1.setTextColor(getResources().getColor(R.color.noir));
                    }
                    if(et2.getText().toString().equals(""))
                    {
                        tv2.setTextColor(getResources().getColor(R.color.rouge));
                    }
                    else {
                        tv2.setTextColor(getResources().getColor(R.color.noir));
                    }
                    if(et3.getText().toString().equals(""))
                    {
                        tv3.setTextColor(getResources().getColor(R.color.rouge));
                    }
                    else {
                        tv3.setTextColor(getResources().getColor(R.color.noir));
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
