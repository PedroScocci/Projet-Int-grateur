package com.example.scoccipe.projetphysique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OndesSimu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondes_simu);

        Button start = (Button) findViewById(R.id.bOndesSimu);
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
