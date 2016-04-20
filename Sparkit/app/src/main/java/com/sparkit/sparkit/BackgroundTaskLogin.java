package com.sparkit.sparkit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * Created by nacho on 3/11/16.
 */
public class BackgroundTaskLogin extends AsyncTask<LoginActivity, Void, String> {

    Context ctx;
    AlertDialog alertDialog;
    LoginActivity loginActivity;
    BackgroundTaskLogin(Context ctx){
        this.ctx = ctx;

    }

    @Override
    protected String doInBackground(LoginActivity... params){

        String login_url = "http://130.184.99.197/login.php";
        loginActivity = params[0];
        String email = loginActivity.email;
        String password = loginActivity.password;

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        return null;
        }


    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Status");
    }

    protected void onPostExecute(String result){
        Toast toast = Toast.makeText(ctx, result, Toast.LENGTH_SHORT);
        toast.show();

        if(!result.equals("Incorrect username and password... Try again")){
            loginActivity.goToMain(result);
        }
        else{
            loginActivity.restartLogin();
        }
    }

}