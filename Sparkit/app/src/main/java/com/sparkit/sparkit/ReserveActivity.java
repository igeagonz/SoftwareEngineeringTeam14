package com.sparkit.sparkit;

import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by I-Gea on 4/19/2016.
 */
public class ReserveActivity extends Activity{

    private EditText ET_email;
    public String email, welcomeMessage, address, length;
    private Spinner reservation_length;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                welcomeMessage = null;
                email = null;
                address = null;
            } else {
                welcomeMessage = extras.getString("welcomeMessage");
                email = extras.getString("email");
                address = extras.getString("reserve_address");
            }
        }
        else{
            email = (String) savedInstanceState.getSerializable("email");
            welcomeMessage = (String) savedInstanceState.getSerializable("welcomeMessage");
            address = (String)savedInstanceState.getSerializable("reserve_address");
        }

        TextView textView = (TextView)findViewById(R.id.address);
        textView.setText(address);

        ET_email = (EditText) findViewById(R.id.emailReservation);
        ET_email.setText(email);


        reservation_length = (Spinner) findViewById(R.id.spinner1);

        String[] item = new String[]{"Day", "Week", "Month", "Semester"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reservation_length.setAdapter(adapter);
        reservation_length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        // Whatever you want to happen when the first item gets selected
                        length = "Day";
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        length = "Week";
                        break;
                    case 2:
                        // Whatever you want to happen when the third item gets selected
                        length = "Month";
                        break;
                    case 3:
                        // Whatever you want to happen when the fourth item gets selected
                        length = "Semester";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onReserve(View view){

        BackgroundTaskReserve backgroundTaskReserve = new BackgroundTaskReserve(this);
        backgroundTaskReserve.execute(this);

        //finish();

    }

    public void goHome(View view){
        Intent intent = new Intent(this, MainPage.class);
        intent.putExtra("email", email);
        intent.putExtra("welcomeMessage", welcomeMessage);
        startActivity(intent);
    }

    public void updateList() {
        BackgroundTaskListUpdate backgroundTaskListUpdate = new BackgroundTaskListUpdate(this);
        backgroundTaskListUpdate.execute(this);
    }

    public void goToMain(ArrayList<String> result) {
        Intent intent = new Intent(this, MainPage.class);
        intent.putExtra("email", email);
        intent.putExtra("welcomeMessage", welcomeMessage);
        intent.putExtra("addressList",result);
        startActivity(intent);
        finish();
    }
}

