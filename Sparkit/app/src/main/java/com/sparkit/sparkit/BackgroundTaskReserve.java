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


/**
 * Created by nacho on 3/11/16.
 */
public class BackgroundTaskReserve extends AsyncTask<ReserveActivity, Void, String> {

    Context ctx;
    AlertDialog alertDialog;
    ReserveActivity reserveActivity;
    BackgroundTaskReserve(Context ctx){
        this.ctx = ctx;

    }

    @Override
    protected String doInBackground(ReserveActivity... params){

        String reserve_url = "http://130.184.99.197/reserve.php";
        reserveActivity = params[0];
        String email = reserveActivity.email;
        String length = reserveActivity.length;
        String address = reserveActivity.address;


            try {
                URL url = new URL(reserve_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("length", "UTF-8") + "=" + URLEncoder.encode(length, "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
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

    }

    protected void onPostExecute(String result){
        Toast toast = Toast.makeText(ctx, result, Toast.LENGTH_SHORT);
        toast.show();


    }

}


