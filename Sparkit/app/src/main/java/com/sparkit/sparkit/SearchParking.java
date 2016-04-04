package com.sparkit.sparkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by nacho on 4/3/16.
 */
public class SearchParking extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_parking);
    }

    public void onLogout(View view){
        startActivity(new Intent(SearchParking.this, LoginActivity.class));
    }

    public void onMain(View view){
        startActivity(new Intent(SearchParking.this, MainPage.class));
    }
}
