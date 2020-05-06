package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RozpocznijCw extends AppCompatActivity {

    private static final String PREFS_NAME = "";
    String cwiczenie;
    //public static final String ACTIVITY_RECOGNITION;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozpocznij_cw);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        cwiczenie = prefs.getString("cwiczenie","");
        TextView textView13 = findViewById(R.id.textView13);
        TextView textView12 = findViewById(R.id.textView12);
        ImageView img= (ImageView) findViewById(R.id.imageView);

        textView13.setText("Teraz wykonujesz ćwiczenie " + cwiczenie.toLowerCase());
        //SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
       // String myVariable = prefs.getString("key_name","");
        //sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(cwiczenie.equals("Unoszenie nogi siedząc na krześle")) {
            img.setImageResource(R.drawable.unoszenie);
            textView12.setText("Aby wykonać to ćwiczenie usiądź na krześle oraz zamocuj telefon do goleni. " +
                    "Naciśnij przycisk \"Start\" na ekranie telefonu " +
                    "i zacznij wykonywać ćwiczenie tak jak jest to przedstawione na obrazku powyżej. " +
                    "Wibracje telefonu oznaczają osiągnięcie górnego i dolnego zakresu wykonywanego ćwiczenia. " +
                    "Ćwiczenie należy zakończyć po usłyszeniu sygnału dźwiękowego (w tym celu należy włączyć dźwięk w telefonie).");
            /*
            textView12.setText("Aby wykonać to ćwiczenie zamocuj telefon do uda tak aby jego ekran był skierowany do góry. " +
                    "Połóż się na plecach oraz wyprostuj nogi. Naciśnij przycisk \"Start\" na ekranie telefonu " +
                    "i zacznij wykonywać ćwiczenie tak jak jest to przedstawione na obrazku powyżej. " +
                    "Wibracje telefonu oznaczają osiągnięcie górnego i dolnego zakresu wykonywanego ćwiczenia. " +
                    "Ćwiczenie należy zakończyć po usłyszeniu sygnału dźwiękowego (w tym celu należy włączyć dźwięk w telefonie).");
                    */

        }
        if(cwiczenie.equals("Podciąganie stopy do pośladków w pozycji siedzącej")) {
            img.setImageResource(R.drawable.podciaganie);
            textView12.setText("Aby wykonać to ćwiczenie usiądź na ziemi z nogami wyprostowanymi przed siebie oraz zamocuj telefon do goleni. " +
                    "Naciśnij przycisk \"Start\" na ekranie telefonu " +
                    "i zacznij wykonywać ćwiczenie tak jak jest to przedstawione na obrazku powyżej. " +
                    "Wibracje telefonu oznaczają osiągnięcie górnego i dolnego zakresu wykonywanego ćwiczenia. " +
                    "Ćwiczenie należy zakończyć po usłyszeniu sygnału dźwiękowego (w tym celu należy włączyć dźwięk w telefonie).");
        }

    }

    public void start(View view) {
        //Intent intent = new Intent(this, Start.class);
        //startActivity(intent);
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }


/*
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener( this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        // if you unregister the last listener, the hardware will stop detecting step events
//        sensorManager.unregisterListener(this);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public final void onSensorChanged(SensorEvent event) {
        TextView editText =  findViewById(R.id.textView11);
        TextView editTextR =  findViewById(R.id.textView13);
        TextView editTextT = findViewById(R.id.textView12);

        float[] g = new float[3];
        g = event.values.clone();

        float norm_Of_g = (float) Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);

// Normalize the accelerometer vector
        g[0] = g[0] / norm_Of_g;
        g[1] = g[1] / norm_Of_g;
        g[2] = g[2] / norm_Of_g;
        int inclination = (int) Math.round(Math.toDegrees(Math.acos(g[2])));
        int rotation = (int) Math.round(Math.toDegrees(Math.atan2(g[0], g[1])));
        editText.setText(String.valueOf( (g[0])) + " " +  (g[1]) + " " + g[2]);
        editTextR.setText(String.valueOf(rotation));
        editTextT.setText(String.valueOf(inclination));

            //Toast.makeText(this, "powtórzenie zaliczone", Toast.LENGTH_LONG).show();

        }
       // float x = event.values[0];
        //editText.setText("fds");
        //editText.setText(String.valueOf(x));


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
*/
}
