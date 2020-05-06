package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KoniecCwiczenia extends AppCompatActivity {

    private static final String PREFS_NAME = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koniec_cwiczenia);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        String myVariable = prefs.getString("wykonaneCykle","");
        int cykl = Integer.parseInt(myVariable) + 1;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("wykonaneCykle", String.valueOf(cykl));
        editor.commit();
        TextView textView = findViewById(R.id.textView);

        textView.setText("Koniec ćwiczenia. Ćwiczenie wykonano "+cykl+" raz/y.");
    }

    public void menuGlowne(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void zakoncz(View view) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
