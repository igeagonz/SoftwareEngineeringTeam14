package com.sparkit.sparkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nacho on 3/17/16.
 */
public class MainPage extends Activity {

    String email, welcomeMessage;
    ArrayList<String> addressList = new ArrayList<String>();
    ArrayList<String> postList = new ArrayList<String>();
    ListView listView;
    ListView postView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_page);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                email = null;
                welcomeMessage = null;
                addressList = null;
                postList = null;
            } else {
                email = extras.getString("email");
                welcomeMessage = extras.getString("welcomeMessage");
                addressList = getIntent().getStringArrayListExtra("addressList");
                postList = getIntent().getStringArrayListExtra("postList");
            }
        }
        else{
           // Bundle extras = getIntent().getExtras();
            //email = extras.getString("email");
            email = (String)savedInstanceState.getSerializable("email");
            //welcomeMessage = extras.getString("welcomeMessage");
            welcomeMessage = (String)savedInstanceState.getSerializable("welcomeMessage");
            addressList = getIntent().getStringArrayListExtra("addressList");
            postList = getIntent().getStringArrayListExtra("postList");
        }

        TextView textView = (TextView) findViewById(R.id.welcomeMessage);
        textView.setText(welcomeMessage);

        //Handle the listview
        listView = (ListView) findViewById(R.id.listView);
        postView = (ListView) findViewById(R.id.postView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                                            android.R.layout.simple_list_item_1, addressList );

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String address = addressList.get(position);

                if (!address.equals("No reservations at this time...")){
                    Intent intent = new Intent(MainPage.this, EditReservation.class);
                    intent.putExtra("address", address);
                    intent.putExtra("welcomeMessage", welcomeMessage);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    //finish();
                }

            }
        });

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, postList );

        postView.setAdapter(arrayAdapter2);

        postView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String address = postList.get(position);

                if (!address.equals("No postings at this time...")){
                    Intent intent = new Intent(MainPage.this, EditPost.class);
                    intent.putExtra("address", address);
                    intent.putExtra("welcomeMessage", welcomeMessage);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }

            }
        });

    }

    public void onLogout(View view){
        startActivity(new Intent(MainPage.this, LoginActivity.class));
        finish();
    }

    public void onPost(View view){
        Intent intent = new Intent(this, CreatePosting.class);
        intent.putExtra("email", email);
        intent.putExtra("welcomeMessage", welcomeMessage);
        startActivity(intent);
    }

    public void onSearch(View view){
        //Call BackgroundTaskSearch in order to fetch array from database and pre-populate the google maps fragment

        BackgroundTaskSearch backgroundTaskSearch = new BackgroundTaskSearch(this);
        backgroundTaskSearch.execute(this);

        //End BackgroundTaskSearch activity




        //startActivity(new Intent(MainPage.this, MapsActivity.class));
    }

    public void goToSearch(ArrayList<String> result){
        Intent intent = new Intent(MainPage.this, MapsActivity.class);
        intent.putExtra("addressList", result);
        intent.putExtra("postList",postList);
        intent.putExtra("email", email);
        intent.putExtra("welcomeMessage", welcomeMessage);
        startActivity(intent);
    }

}
