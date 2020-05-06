package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lekarzButtonClick(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, PanelLekarza.class);
        startActivity(intent);
    }

    public void pacjentButtonClick(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, PanelPacjenta.class);
        startActivity(intent);
    }

}
