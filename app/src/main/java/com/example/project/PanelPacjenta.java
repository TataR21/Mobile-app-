package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PanelPacjenta extends AppCompatActivity {
    private static final String PREFS_NAME = "";
    String cwiczenie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_pacjenta);
    }

    public void rozpocznijCwiczenie(View view) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        cwiczenie = prefs.getString("cwiczenie","");
        if(cwiczenie.equals("")) {
            Toast.makeText(this, "Musisz najpierw dodać ćwiczenie w panelu lekarza!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, RozpocznijCw.class);
            startActivity(intent);
        }


    }

    public void zobaczStatystyki(View view) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        cwiczenie = prefs.getString("cwiczenie","");
        if(cwiczenie.equals("")) {
            Toast.makeText(this, "Musisz najpierw dodać ćwiczenie w panelu lekarza!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, ZobaczStatystyki.class);
            startActivity(intent);
        }
    }

    public void kontaktDoLekarz(View view) {
        Intent intent = new Intent(this, KontaktLekarz.class);
        startActivity(intent);
    }
}
