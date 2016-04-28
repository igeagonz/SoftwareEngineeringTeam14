package com.sparkit.sparkit;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by nacho on 3/11/16.
 */

public class SignupActivity extends Activity{

    EditText ET_fname, ET_lname, ET_email, ET_password, ET_password2;
    String fname, lname, email, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ET_fname = (EditText)findViewById(R.id.fname);
        ET_lname = (EditText)findViewById(R.id.lname);
        ET_email = (EditText)findViewById(R.id.new_email);
        ET_password = (EditText)findViewById(R.id.new_password);
        ET_password2 = (EditText)findViewById(R.id.new_password2);
    }

    public void userReg(View view){
        fname = ET_fname.getText().toString();
        lname = ET_lname.getText().toString();
        email = ET_email.getText().toString();
        password = ET_password.getText().toString();
        password2  =ET_password2.getText().toString();
        String method = "register";

        if (!validateEmail(email)){
            ET_email.setError("Invalid Email");
            ET_email.requestFocus();
        }
        else if(!validatePassword(password)){
            ET_password.setError("Password must be greater than 6 characters.");
            ET_password.requestFocus();
        }
        else if(!validatePassMatch(password, password2)){
            ET_password2.setError("Passwords do not match.");
            ET_password2.requestFocus();
        }
        else{
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, fname, lname, email, password);
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

    protected boolean validatePassMatch(String pass1, String pass2) {
        if (pass1.equals(pass2))
            return true;
        else
            return false;
    }

    protected boolean validatePassword(String password) {
        if(password!=null && password.length()>6) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean validateEmail(String email){
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
