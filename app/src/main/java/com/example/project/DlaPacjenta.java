package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DlaPacjenta extends AppCompatActivity {
    private static final String PREFS_NAME = "";
    String liczbaCykli;
    private static String minVal;
    private static String maxVal;
    private static String iloscPowtorzen;
    private String wykonaneCykle;
    String cwiczenie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dla_pacjenta);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        liczbaCykli = prefs.getString("maxCykli","");
        iloscPowtorzen = prefs.getString("iloscPowtorzen","");
        minVal = prefs.getString("minVal","");
        maxVal = prefs.getString("maxVal","");
        cwiczenie = prefs.getString("cwiczenie","Unoszenie nogi siedząc na krześle");
        wykonaneCykle = prefs.getString("wykonaneCykle","");
        EditText textCykle = findViewById(R.id.editText7);
        EditText textMinVal = findViewById(R.id.editText9);
        EditText textMaxVal = findViewById(R.id.editText);
        EditText textPowtorzenia = findViewById(R.id.editText8);
        textCykle.setText(liczbaCykli);
        textMaxVal.setText(maxVal);
        textMinVal.setText(minVal);
        textPowtorzenia.setText(iloscPowtorzen);
        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter();
        //int spinnerPosition = myAdap.getPosition(cwiczenie);
        //spinner.setSelection(spinnerPosition);


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        String myVariable = prefs.getString("maxCykli","");
        EditText editText = (EditText) findViewById(R.id.editText7);
        editText.setText(myVariable);
    }

    //@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("maxCykli", liczbaCykli);
        editor.putString("iloscPowtorzen", iloscPowtorzen);
        editor.putString("minVal", minVal);
        editor.putString("maxVal", maxVal);
        editor.putString("wykonaneCykle",wykonaneCykle);
        editor.putString("cwiczenie",cwiczenie);
        editor.commit();
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("maxCykli", liczbaCykli);
        editor.putString("iloscPowtorzen", iloscPowtorzen);
        editor.putString("minVal", minVal);
        editor.putString("maxVal", maxVal);
        editor.putString("wykonaneCykle",wykonaneCykle);
        editor.putString("cwiczenie",cwiczenie);
        editor.commit();
    }

    protected void onDestroy() {
        super.onDestroy();
        //Bundle outState;
        //outState.putString(TEXT_VIEW_KEY, numer);
        /*
        EditText textCykle = findViewById(R.id.editText7);
        EditText textMinVal = findViewById(R.id.editText9);
        EditText textMaxVal = findViewById(R.id.editText);
        EditText textPowtorzenia = findViewById(R.id.editText8);
        iloscPowtorzen = textPowtorzenia.getText().toString();
        liczbaCykli = textCykle.getText().toString();
        minVal = textMinVal.getText().toString();
        maxVal = textMaxVal.getText().toString();
         */

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("maxCykli", liczbaCykli);
        editor.putString("iloscPowtorzen", iloscPowtorzen);
        editor.putString("minVal", minVal);
        editor.putString("maxVal", maxVal);
        editor.putString("wykonaneCykle",wykonaneCykle);
        editor.putString("cwiczenie",cwiczenie);
        editor.commit();
    }
    public void zapisz(View view) {
        EditText textCykle = findViewById(R.id.editText7);
        EditText textMinVal = findViewById(R.id.editText9);
        EditText textMaxVal = findViewById(R.id.editText);
        EditText textPowtorzenia = findViewById(R.id.editText8);
        Spinner spinner = findViewById(R.id.spinner);
        cwiczenie = spinner.getSelectedItem().toString();
        iloscPowtorzen = textPowtorzenia.getText().toString();
        liczbaCykli = textCykle.getText().toString();
        minVal = textMinVal.getText().toString();
        maxVal = textMaxVal.getText().toString();
        wykonaneCykle = "0";
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
