package com.sparkit.sparkit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;


/**
 * Created by I-Gea on 5/1/2016.
 */
public class TransactionActivity extends Activity {
    private EditText ET_card_number, ET_month, ET_year, ET_cvc, ET_address, ET_city, ET_zip;
    public String card_number, month, year, cvc, billing_address, city, state, zip;
    public String email, welcomeMessage, reserve_address, length;
    public Spinner states;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                welcomeMessage = null;
                email = null;
                reserve_address = null;
                length = null;
            } else {
                welcomeMessage = extras.getString("welcomeMessage");
                email = extras.getString("email");
                reserve_address = extras.getString("reserve_address");
                length = extras.getString("length");
            }
        }
        else{
            email = (String) savedInstanceState.getSerializable("email");
            welcomeMessage = (String) savedInstanceState.getSerializable("welcomeMessage");
            reserve_address = (String)savedInstanceState.getSerializable("reserve_address");
            length = (String)savedInstanceState.getSerializable("length");
        }


        states = (Spinner) findViewById(R.id.statesSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                            R.array.states, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        states.setAdapter(adapter);

        ET_card_number = (EditText)findViewById(R.id.cardNumber);
        ET_month = (EditText)findViewById(R.id.month);
        ET_year = (EditText)findViewById(R.id.year);
        ET_cvc = (EditText)findViewById(R.id.CVC);
        ET_address = (EditText)findViewById(R.id.billingAddress);
        ET_city = (EditText)findViewById(R.id.billingCity);
        ET_zip = (EditText)findViewById(R.id.zipCode);

    }

    public void goBack(View view){
        finish();
    }

    public void onPay(View view){

        card_number = ET_card_number.getText().toString();
        month = ET_month.getText().toString();
        year = ET_year.getText().toString();
        cvc = ET_cvc.getText().toString();
        billing_address = ET_address.getText().toString();
        city = ET_city.getText().toString();
        zip = ET_zip.getText().toString();
        state = states.getSelectedItem().toString();

        BackgroundTaskReserve backgroundTaskReserve = new BackgroundTaskReserve(this);
        backgroundTaskReserve.execute(this);

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
