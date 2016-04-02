package com.sparkit.sparkit;

import android.content.Intent;
import android.widget.EditText;

/**
 * Created by grantjohns on 4/1/16.
 */


//
public class CreatePosting
{
    public Post onCreatePost()
    {
        EditText titleText = (EditText) findViewById(R.id.postTitle);
        String title = titleText.getText().toString();
        EditText emailText = (EditText) findViewById(R.id.postEmail);
        String email = emailText.getText().toString();
        EditText stAddressText = (EditText) findViewById(R.id.postStAddress);
        String stAddress = stAddressText.getText().toString();
        EditText cityText = (EditText) findViewById(R.id.postCity);
        String city = cityText.getText().toString();
        EditText stateText = (EditText) findViewById(R.id.postState);
        String state = stateText.getText().toString();
        EditText zipText = (EditText) findViewById(R.id.postZip);
        int zip = Integer.parseInt(zipText.getText().toString());
        EditText descriptionText = (EditText) findViewById(R.id.postDescription);
        String description = descriptionText.getText().toString();

        Post postNew = new Post(title,email,stAddress,city,state,zip,description);

        startActivity(new Intent(CreatePosting.this, MainPage.class));
    }

}
