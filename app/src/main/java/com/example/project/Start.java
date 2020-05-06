package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Start extends AppCompatActivity implements SensorEventListener {

    private static final String PREFS_NAME = "";
    private SensorManager sensorManager;
    //private Sensor mLight;
    private int counter =0;
    private boolean minFlag = false;
    private boolean maxFlag = false;
    private String liczbaCykli;
    private String iloscPowtorzen;
    private String minVal;
    private String maxVal;
    private boolean flag = false;
    Sensor accelerometer;
    Sensor magnetometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ProgressBar bar = findViewById(R.id.progressBar);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
                //mLight = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
                //mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
       liczbaCykli = prefs.getString("maxCykli","");
        iloscPowtorzen = prefs.getString("iloscPowtorzen","");
        minVal = prefs.getString("minVal","");
       maxVal = prefs.getString("maxVal","");
        bar.setMax(Integer.parseInt(iloscPowtorzen));
       // DlaPacjenta.minVal = "20";
       // DlaPacjenta.maxVal = "80";
    }

    @Override
    protected void onResume() {
        super.onResume();
        //sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        //sensorManager.registerListener( this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        liczbaCykli = prefs.getString("maxCykli","");
       iloscPowtorzen = prefs.getString("iloscPowtorzen","");
        minVal = prefs.getString("minVal","");
        maxVal = prefs.getString("maxVal","");
    }

    @Override
    protected void onPause() {
        super.onPause();
       // sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
       // mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.unregisterListener(this);
        // if you unregister the last listener, the hardware will stop detecting step events
//        sensorManager.unregisterListener(this);
    }
    float[] mGravity;
    float[] mGeomagnetic;

    ///@SuppressLint("SetTextI18n")
    @Override
    public final void onSensorChanged(SensorEvent event) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        double azimut = 0, pitch = 0, roll = 0;
        ProgressBar bar = findViewById(R.id.progressBar);
        TextView editTextC = findViewById(R.id.textView16);
        TextView editTextA = findViewById(R.id.textView14);
        TextView editTextT = findViewById(R.id.textView15);
        TextView editTextX = findViewById(R.id.textView11);
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values.clone();
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values.clone();
        if (mGravity != null && mGeomagnetic != null) {
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimut = orientation[0] * 180 / Math.PI; // orientation contains: azimut, pitch and roll
                pitch = orientation[1] * 180 / Math.PI;
                roll = orientation[2] * 180 / Math.PI;
            }
            editTextA.setText(String.valueOf(azimut));
            editTextT.setText(String.valueOf(pitch));
            editTextX.setText(String.valueOf(roll));
        }
        double inclination = -pitch;
        if(inclination < Integer.parseInt(minVal) && flag) {
            minFlag = true;
            //r.play();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                 v.vibrate(100);
            }
            if(minFlag && maxFlag) {
                counter++;
                maxFlag=false;
                minFlag=false;
                bar.setProgress(counter);
                editTextC.setText(String.valueOf(counter) + "/" + iloscPowtorzen);
            }
        }

        if(inclination > Integer.parseInt(maxVal) ) {
            maxFlag = true;
            flag = true;
            //r.play();
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                 v.vibrate(100);
            }
            //Toast.makeText(this, "powtórzenie zaliczone", Toast.LENGTH_LONG).show();
        }

        if(counter >= Integer.parseInt(iloscPowtorzen)) {
            r.play();
            Intent intent = new Intent(this, KoniecCwiczenia.class);
            startActivity(intent);
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
/*
    private double[] convertFloatsToDoubles(float[] input)
    {
        if (input == null)
            return null;

        double[] output = new double[input.length];

        for (int i = 0; i < input.length; i++)
            output[i] = input[i];

        return output;
    }

 */
 /*
        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            ProgressBar bar = findViewById(R.id.progressBar);
            TextView editTextC = findViewById(R.id.textView16);
            TextView editTextA = findViewById(R.id.textView14);
            TextView editTextT = findViewById(R.id.textView15);
            TextView editTextX = findViewById(R.id.textView11);
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            //float[] g = new float[3];
            double[] g = convertFloatsToDoubles(event.values.clone());
            //g = event.values.clone();

            //float norm_Of_g = (float) Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);
            //double azimuth = g[0] * 180 / Math.PI;
            //double pitch = g[1] * 180 / Math.PI;
           // double roll = g[2] * 180 / Math.PI;
            double azimuth = g[0];
            double pitch = g[1];
            double roll = g[2] ;
            //editTextT.setText(String.valueOf(g[0]));
            //editTextR.setText(String.valueOf(g[1]));
            //editTextC.setText(String.valueOf(g[2]));
            //editTextT.setText(String.valueOf(azimuth));
            editTextA.setText(String.valueOf(azimuth)); //oś z // w emulatorze oś x czyli nachylenie do podłoża zakres -180 do 180
            editTextT.setText(String.valueOf(pitch)); // oś x // w emulatorze oś y czyli obrót w jakby w okół własnej osi zakres -90 do 90
            editTextX.setText(String.valueOf(roll)); // oś y // w emulatorze oś z czyli telefon leży płasko na stole i obracając nim zmieniamy kąt (jak kompas) zakres od 180 do - 180 jeżeli wskzuje 0 to telefon jest skierowany w strone północy
            double inclination = azimuth;
            if(inclination < Integer.parseInt(minVal) && flag) {
                minFlag = true;
                //r.play();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                   // v.vibrate(150);
                }
                if(minFlag && maxFlag) {
                    counter++;
                    maxFlag=false;
                    minFlag=false;
                    bar.setProgress(counter);
                    editTextC.setText(String.valueOf(counter) + "/" + iloscPowtorzen);
                }
            }

            if(inclination > Integer.parseInt(maxVal) ) {
                maxFlag = true;
                flag = true;
                //r.play();
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                   // v.vibrate(150);
                }
                //Toast.makeText(this, "powtórzenie zaliczone", Toast.LENGTH_LONG).show();
            }

            if(counter == Integer.parseInt(iloscPowtorzen)) {
                r.play();
                Intent intent = new Intent(this, KoniecCwiczenia.class);
                startActivity(intent);
            }
        }

         */
// Normalize the accelerometer vector
    //g[0] = g[0] / norm_Of_g;
    // g[1] = g[1] / norm_Of_g;
    //  g[2] = g[2] / norm_Of_g;
        /*
        int inclination = (int) Math.round(Math.toDegrees(Math.acos(g[2])));
        int rotation = (int) Math.round(Math.toDegrees(Math.atan2(g[0], g[1])));


        editTextR.setText(String.valueOf(rotation));
        editTextT.setText(String.valueOf(inclination));

        if(inclination < Integer.parseInt(minVal) && flag) {
            minFlag = true;
            //r.play();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(150);
            }
            if(minFlag && maxFlag) {
                counter++;
                maxFlag=false;
                minFlag=false;
                bar.setProgress(counter);
                editTextC.setText(String.valueOf(counter) + "/" + iloscPowtorzen);
            }
        }

        if(inclination > Integer.parseInt(maxVal) ) {
            maxFlag = true;
            flag = true;
            //r.play();
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(150);
            }
            //Toast.makeText(this, "powtórzenie zaliczone", Toast.LENGTH_LONG).show();
        }

        if(counter == Integer.parseInt(iloscPowtorzen)) {
            r.play();
            Intent intent = new Intent(this, KoniecCwiczenia.class);
            startActivity(intent);
        }
*/

/*

        double norm = Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2] + g[3] * g[3]);
        g[0] /= norm;
        g[1] /= norm;
        g[2] /= norm;
        g[3] /= norm;

        //Set values to commonly known quaternion letter representatives
        double x = g[0];
        double y = g[1];
        double z = g[2];
        double w = g[3];

        //Calculate Pitch in degrees (-180 to 180)
        double sinP = 2.0 * (w * x + y * z);
        double cosP = 1.0 - 2.0 * (x * x + y * y);
        pitch = Math.atan2(sinP, cosP) * (180 / Math.PI);

        //Calculate Tilt in degrees (-90 to 90)
        double sinT = 2.0 * (w * y - z * x);
        if (Math.abs(sinT) >= 1)
            tilt = Math.copySign(Math.PI / 2, sinT) * (180 / Math.PI);
        else
            tilt = Math.asin(sinT) * (180 / Math.PI);

        //Calculate Azimuth in degrees (0 to 360; 0 = North, 90 = East, 180 = South, 270 = West)
        double sinA = 2.0 * (w * z + x * y);
        double cosA = 1.0 - 2.0 * (y * y + z * z);
        azimuth = Math.atan2(sinA, cosA) * (180 / Math.PI);
        editTextT.setText(String.valueOf(tilt));
        editTextR.setText(String.valueOf(pitch));
        editTextC.setText(String.valueOf(azimuth));

 */

}
