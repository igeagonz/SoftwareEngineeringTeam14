package com.sparkit.sparkit;

import android.app.AlertDialog;
import android.content.Context;
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
 * Created by I-Gea on 4/27/2016.
 */
public class BackgroundTaskCancel extends AsyncTask<EditReservation, Void, String> {

    Context ctx;
    AlertDialog alertDialog;
    EditReservation editReservation;

    BackgroundTaskCancel(Context ctx) {
        this.ctx = ctx;

    }

    @Override
    protected String doInBackground(EditReservation... params) {

        String delete_url = "http://130.184.99.197/DeleteReservation.php";
        editReservation = params[0];
        String email = editReservation.email;
        String address = editReservation.address;

        try {
            URL url = new URL(delete_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                    URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();

            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            IS.close();
            httpURLConnection.disconnect();

            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Status");
    }

    protected void onPostExecute(String result) {
        Toast toast = Toast.makeText(ctx, result, Toast.LENGTH_SHORT);
        toast.show();
        editReservation.retrieveList();
    }
}


