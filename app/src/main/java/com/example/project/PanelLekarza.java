package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class PanelLekarza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_lekarza);
    }

    public void podajDaneDlaPacjenta(View view) {
        Intent intent = new Intent(this, DlaPacjenta.class);
        startActivity(intent);
    }

    public void podajSwojeDane(View view) {
        Intent intent = new Intent(this, PodajSwojeDane.class);
        startActivity(intent);
    }
}
