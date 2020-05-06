package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;


public class PodajSwojeDane extends AppCompatActivity {
    public static String numer = "a";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podaj_swoje_dane);
    }

    public void stworzKontakt(View view) {
        EditText editText = (EditText) findViewById(R.id.editText3);
        String nazwa = editText.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.editText2);
        numer = editText2.getText().toString();

        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        // Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        intent.putExtra(ContactsContract.Intents.Insert.NAME, nazwa)

                .putExtra(ContactsContract.Intents.Insert.PHONE, numer);

        startActivity(intent);
    }

    public String getNumer() {
        return numer;
    }
}
