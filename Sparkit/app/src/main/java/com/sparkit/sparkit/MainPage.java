package com.sparkit.sparkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by nacho on 3/17/16.
 */
public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
    }

    public void onLogout(View view){
        startActivity(new Intent(MainPage.this, LoginActivity.class));
    }

    public void onPost(View view){
        startActivity(new Intent(MainPage.this, CreatePosting.class));
    }

}
