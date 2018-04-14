package com.sparkit.sparkit;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class CreatePosting extends Activity
{
    EditText ET_title, ET_email, ET_stAddress, ET_city, ET_state, ET_zip, ET_description;
    String title, email,stAddress, city, state, zip, description, welcomeMessage;
    ArrayList<String> addressList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_posting);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                email = null;
                welcomeMessage = null;
            } else {
                email = extras.getString("email");
                welcomeMessage = extras.getString("welcomeMessage");
            }
        }
        else{
            email = (String)savedInstanceState.getSerializable("email");
            welcomeMessage = (String)savedInstanceState.getSerializable("welcomeMessage");
        }

        ET_title = (EditText)findViewById(R.id.postTitle);
        //ET_email = (EditText)findViewById(R.id.postEmail);
        //ET_email.setText(email);
        ET_stAddress = (EditText)findViewById(R.id.postStAddress);
        ET_city = (EditText)findViewById(R.id.postCity);
        ET_state = (EditText)findViewById(R.id.postState);
        ET_zip = (EditText)findViewById(R.id.postZip);
        ET_description = (EditText)findViewById(R.id.postDescription);



    }
    public void onCreatePost(View view)
    {
        title = ET_title.getText().toString();
        //email = ET_email.getText().toString();
        stAddress = ET_stAddress.getText().toString();
        city = ET_city.getText().toString();
        state = ET_state.getText().toString();
        zip = ET_state.getText().toString();
        description = ET_description.getText().toString();

        String method = "post";

        BackgroundTaskPost backgroundTaskPost = new BackgroundTaskPost(this);
        backgroundTaskPost.execute(this);

        //finish();
    }
    public void goHome(View view){
        finish();
    }

    public void goToMain(ArrayList<String> result) {

        Intent intent = new Intent(this, MainPage.class);
        intent.putExtra("email", email);
        intent.putExtra("welcomeMessage", welcomeMessage);
        intent.putExtra("postList",result);
        intent.putExtra("addressList",addressList);
        startActivity(intent);
        finish();
    }

    public void retrieveList() {
        BackgroundTaskListView3 backgroundTaskListView3 = new BackgroundTaskListView3(this);
        backgroundTaskListView3.execute(this);
    }

    public void retrieveList2(ArrayList<String> result) {
        addressList = result;
        BackgroundTaskPostListView3 backgroundTaskPostListView3 = new BackgroundTaskPostListView3(this);
        backgroundTaskPostListView3.execute(this);

    }
}
