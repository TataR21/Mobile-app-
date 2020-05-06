package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ZobaczStatystyki extends AppCompatActivity {

    private static final String PREFS_NAME = "" ;
    private String liczbaCykli;
    private String iloscPowtorzen;
    private String minVal;
    private String maxVal;
    private String cwiczenie;
    private String wykonaneCykle;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zobacz_statystyki);
        TextView textView = findViewById(R.id.textView8);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        liczbaCykli = prefs.getString("maxCykli","");
        iloscPowtorzen = prefs.getString("iloscPowtorzen","");
        minVal = prefs.getString("minVal","");
        maxVal = prefs.getString("maxVal","");
        cwiczenie = prefs.getString("cwiczenie","");
        wykonaneCykle = prefs.getString("wykonaneCykle","Unoszenie nóg leżąc");
        textView.setText("Wykonujesz teraz ćwiczenie "+ cwiczenie.toLowerCase() + ". Ćwiczenie wykonano " + wykonaneCykle+" raz/razy.");
    }
}
