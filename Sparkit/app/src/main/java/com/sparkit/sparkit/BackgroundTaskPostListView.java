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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BackgroundTaskPostListView extends AsyncTask<LoginActivity, Void, ArrayList<String>> {

    Context ctx;
    AlertDialog alertDialog;
    LoginActivity loginActivity;

    BackgroundTaskPostListView(Context ctx) {
        this.ctx = ctx;

    }

    @Override
    protected ArrayList<String> doInBackground(LoginActivity... params) {

        loginActivity = params[0];
        String email = loginActivity.email;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.147:3306/sparkit", "root", "password");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select title from Posts where email = " + "'" + email + "'");

            ArrayList<String> result = new ArrayList<>();

            while(rs.next()) {
                result.add(rs.getString(1));
            }

            con.close();

            return result;

        } catch(Exception e) {
            e.printStackTrace();
        }

        /*String address_url = "http://130.184.99.197/GetPosts.php";
        loginActivity = params[0];
        String email = loginActivity.email;


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
        }*/

        return null;
    }


    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    protected void onPreExecute() {
    }

    protected void onPostExecute(ArrayList<String> result) {
        loginActivity.goToMain(result);


    }
}
