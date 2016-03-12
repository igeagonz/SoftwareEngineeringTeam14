package com.sparkit.sparkit;

import android.app.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

/**
 * Created by nacho on 3/11/16.
 */

public class SignupActivity extends Activity{

    EditText ET_fname, ET_lname, ET_email, ET_password;
    String fname, lname, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ET_fname = (EditText)findViewById(R.id.fname);
        ET_lname = (EditText)findViewById(R.id.lname);
        ET_email = (EditText)findViewById(R.id.new_email);
        ET_password = (EditText)findViewById(R.id.new_password);
    }

    public void userReg(View view){
        fname = ET_fname.getText().toString();
        lname = ET_lname.getText().toString();
        email = ET_email.getText().toString();
        password = ET_password.getText().toString();
        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, fname, lname, email, password);
    }


}
