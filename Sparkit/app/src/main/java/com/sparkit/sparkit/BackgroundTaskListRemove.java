package com.sparkit.sparkit;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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
 * Created by I-Gea on 4/27/2016.
 */
public class BackgroundTaskListRemove extends AsyncTask<EditPost, Void, ArrayList<String>> {

    Context ctx;
    AlertDialog alertDialog;
    EditPost editPost;

    BackgroundTaskListRemove(Context ctx) {
        this.ctx = ctx;

    }

    @Override
    protected ArrayList<String> doInBackground(EditPost... params) {

        String address_url = "http://130.184.99.197/GetPosts.php";
        editPost = params[0];
        String email = editPost.email;


        try {
            URL url = new URL(address_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

            String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();

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
        editPost.goToMain(result);
    }
}


