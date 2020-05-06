package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KontaktLekarz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontakt_lekarz);
    }

    @SuppressLint("SetTextI18n")
    public void zadzwon(View view) {
        PodajSwojeDane dane = new PodajSwojeDane();
        if(dane.numer.equals("a")) {
            TextView textView = findViewById(R.id.textView5);
            textView.setText("Nie dodano żadnego numeru, aby zadzwonić do swojego lekarza należy wcześniej dodać numer!");
        } else {
            dialContactPhone(dane.getNumer());
        }

    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    //PodajSwojeDane dane = new PodajSwojeDane();

  //  String x = dane.getNumer();
}
