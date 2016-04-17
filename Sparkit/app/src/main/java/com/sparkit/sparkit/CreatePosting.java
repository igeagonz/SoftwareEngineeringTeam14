package com.sparkit.sparkit;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

/**
 * Created by grantjohns on 4/1/16.
 */

public class CreatePosting extends Activity
{
    EditText ET_title, ET_email, ET_stAddress, ET_city, ET_state, ET_zip, ET_description;
    String title, email,stAddress, city, state, zip, description;
    MapsActivity mapsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_posting);

        ET_title = (EditText)findViewById(R.id.postTitle);
        ET_email = (EditText)findViewById(R.id.postEmail);
        ET_stAddress = (EditText)findViewById(R.id.postStAddress);
        ET_city = (EditText)findViewById(R.id.postCity);
        ET_state = (EditText)findViewById(R.id.postState);
        ET_zip = (EditText)findViewById(R.id.postZip);
        ET_description = (EditText)findViewById(R.id.postDescription);

    }
    public void onCreatePost(View view)
    {
        title = ET_title.getText().toString();
        email = ET_email.getText().toString();
        stAddress = ET_stAddress.getText().toString();
        city = ET_city.getText().toString();
        state = ET_state.getText().toString();
        zip = ET_state.getText().toString();
        description = ET_description.getText().toString();

        String method = "post";

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, title, email, stAddress, city, state, zip, description);

        finish();

    }

}
