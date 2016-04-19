package com.sparkit.sparkit;

import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by I-Gea on 4/19/2016.
 */
public class ReserveActivity extends Activity{

    private EditText ET_email;
    private String email;
    private Spinner reservation_length;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        ET_email = (EditText) findViewById(R.id.emailReservation);

        //check for correct email format input
        if(!validateEmail(email)){
            ET_email.setError("Invalid Email");
            ET_email.requestFocus();
        }

        reservation_length = (Spinner) findViewById(R.id.spinner1);

        String[] item = new String[]{"Day, Week, Month, Semester"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reservation_length.setAdapter(adapter);
        reservation_length.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

    }

    public void onReserve(View view){

        email = ET_email.getText().toString();

    }

    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

}

