package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ParametresCentripete extends AppCompatActivity {
    public static final String PARAMETRES = "param";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_centripete);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.routes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView tv1 = (TextView) findViewById(R.id.edittext1);
        int masse  = Integer.valueOf(tv1.toString());
        TextView tv2 = (TextView) findViewById(R.id.edittext2);
        int vitesse = Integer.valueOf(tv2.toString());
        TextView tv3 = (TextView) findViewById(R.id.edittext3);
        int rayon = Integer.valueOf(tv3.toString());

        final double[] def = new double[1];
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                switch(pos)
                {
                    case 0: def[0] = 0.2;
                    break;
                    case 1: def[0] = 0.899;
                    break;
                    case 2: def[0] = 0.876;
                    break;
                    case 3: def[0] = 0.745;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        double test = def[0]*3;

        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParametresCentripete.this, Force_Centripete.class);
                intent.putExtra(PARAMETRES, masse);
                startActivity(intent);
            }
        });
    }
}
