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

import se.simbio.encryption.Encryption;


/**
 * Created by nacho on 3/11/16.
 */
public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context ctx;
    AlertDialog alertDialog;
    BackgroundTask(Context ctx){
        this.ctx = ctx;

    }

    @Override
    protected String doInBackground(String... params){

        String reg_url = "http://130.184.99.197/register.php";
        String login_url = "http://130.184.99.197/login.php";
        String post_url = "http://130.184.99.197/post.php";
        String method  = params[0];

        if(method.equals("register")){
            String fname = params[1];
            String lname = params[2];
            String email = params[3];
            String password = params[4];

            //Encrypt the password
            Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);
            String pass_encrypt = encryption.encryptOrNull(password);

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
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
        }
        else if(method.equals("login")){
            String email = params[1];
            String password = params[2];

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
        }

        else if(method.equals("post")){
            String title = params[1];
            String email = params[2];
            String stAddress = params[3];
            String city = params[4];
            String state = params[5];
            String zip = params[6];
            String description = params[7];

            try {
                URL url = new URL(post_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("stAddress", "UTF-8") + "=" + URLEncoder.encode(stAddress, "UTF-8") + "&" +
                        URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&" +
                        URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(state, "UTF-8") + "&" +
                        URLEncoder.encode("zip", "UTF-8") + "=" + URLEncoder.encode(zip, "UTF-8") + "&" +
                        URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8");
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
        }

        return null;
    }
    //testout branching

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


    }

}

