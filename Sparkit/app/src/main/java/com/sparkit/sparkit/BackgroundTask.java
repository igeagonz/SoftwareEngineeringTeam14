package com.sparkit.sparkit;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by nacho on 3/11/16.
 */
public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context ctx;
    BackgroundTask(Context ctx){
        this.ctx = ctx;

    }

    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params){
        String reg_url = "http://10.0.2.2/SParkit/register.php";
        String login_url = "http://10.0.2.2/SParkit/login.php";
        String method  = params[0];

        if(method.equals("register")){
            String fname = params[1];
            String lname = params[2];
            String email = params[3];
            String password = params[4];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                HttpURLConnection.setRequestMethod("POST");
                HttpURLConnection.setDoOutput(true);
                OutputStream OS = HttpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(fname, "UTF-8") + "&" +
                        URLEncoder.encode("lname", "UTF-8") + "=" + URLEncoder.encode(lname, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                return "Registration Success...";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }


        }

        return Null;
    }

    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(String result){
        Toast.makeTest(ctx,result, Toast.LENGTH_LONG).show();
    }

}

