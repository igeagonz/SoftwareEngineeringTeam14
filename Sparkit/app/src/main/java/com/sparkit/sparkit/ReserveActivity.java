package com.sparkit.sparkit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by I-Gea on 4/19/2016.
 */
public class ReserveActivity extends Activity{

    public String email, welcomeMessage, address, length;
    private Spinner reservation_length;
    private EditText date_select;
    Calendar myCalendar = Calendar.getInstance();
    ArrayList<String> postList = new ArrayList<String>();

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

        postList = getIntent().getStringArrayListExtra("postList");

        TextView textView = (TextView)findViewById(R.id.address);
        textView.setText(address);

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


        date_select = (EditText)findViewById(R.id.date_select);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date_select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ReserveActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date_select.setText(sdf.format(myCalendar.getTime()));
    }


    public void onReserve(View view){

        String reserve_date = date_select.toString();

        Intent intent = new Intent(this,TransactionActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("welcomeMessage", welcomeMessage);
        intent.putExtra("length", length);
        intent.putExtra("reserve_address",address);
        intent.putExtra("postList",postList);
        intent.putExtra("reserve_date",reserve_date);

        startActivity(intent);


        /*BackgroundTaskReserve backgroundTaskReserve = new BackgroundTaskReserve(this);
        backgroundTaskReserve.execute(this);

        //finish();*/

    }

    public void goBack(View view){
        finish();
    }

    /*public void updateList() {
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
    */
}

