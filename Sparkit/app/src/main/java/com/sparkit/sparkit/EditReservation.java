package com.sparkit.sparkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by I-Gea on 4/27/2016.
 */
public class EditReservation extends Activity {
    String address, email, welcomeMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editreservation);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                welcomeMessage = null;
                email = null;
                address = null;
            } else {
                welcomeMessage = extras.getString("welcomeMessage");
                email = extras.getString("email");
                address = extras.getString("address");
            }
        }
        else{
            welcomeMessage = (String)savedInstanceState.getSerializable("welcomeMessage");
            email = (String)savedInstanceState.getSerializable("email");
            address = (String)savedInstanceState.getSerializable("address");
        }

        TextView textView = (TextView)findViewById(R.id.address);
        textView.setText(address);

    }

    public void onCancel(View view){
        BackgroundTaskCancel backgroundTaskCancel = new BackgroundTaskCancel(this);
        backgroundTaskCancel.execute(this);


    }

    public void retrieveList(){
        BackgroundTaskListCancel backgroundTaskListCancel = new BackgroundTaskListCancel(this);
        backgroundTaskListCancel.execute(this);
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
