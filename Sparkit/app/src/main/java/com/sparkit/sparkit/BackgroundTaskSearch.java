package com.sparkit.sparkit;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BackgroundTaskSearch extends AsyncTask<MainPage, Void, ArrayList<String>> {

    Context ctx;
    AlertDialog alertDialog;
    MainPage mainPage;

    BackgroundTaskSearch(Context ctx) {
        this.ctx = ctx;

    }

    @Override
    protected ArrayList<String> doInBackground(MainPage... params) {

        String address_url = "http://130.184.99.197/GetAddresses.php";
        mainPage = params[0];

        try {
            URL url = new URL(address_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
            ArrayList<String> result = new ArrayList<String>();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
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
    }

    protected void onPostExecute(ArrayList<String> result) {
            /*Toast toast = Toast.makeText(ctx,result.get(1), Toast.LENGTH_SHORT);
            toast.show();*/
            mainPage.goToSearch(result);


    }
}
