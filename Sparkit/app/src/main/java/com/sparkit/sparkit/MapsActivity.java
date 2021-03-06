package com.sparkit.sparkit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnInfoWindowClickListener {

    ArrayList<String> addresses = new ArrayList<String>();
    ArrayList<String> postList = new ArrayList<String>();
    String email, welcomeMessage;

    private GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    protected Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        email = getIntent().getStringExtra("email");
        welcomeMessage = getIntent().getStringExtra("welcomeMessage");

        //retrieve arraylist of addresses
        addresses = getIntent().getStringArrayListExtra("addressList");

        postList = getIntent().getStringArrayListExtra("postList");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //mMap.setMyLocationEnabled(true);

        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"No permissions for fine location services..", Toast.LENGTH_LONG);
            toast.show();
        }*/

        mMap.getUiSettings().setZoomControlsEnabled(true);

        List<Address> coordinates = null;
        for (int i = 0; i < addresses.size(); i++) {
            if (addresses.get(i) != null || !addresses.get(i).equals("")) {
                Geocoder geocoder = new Geocoder(this);
                try {
                    coordinates = geocoder.getFromLocationName(addresses.get(i), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < coordinates.size(); j++) {
                    Address address = coordinates.get(j);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng)
                            .title(addresses.get(i))
                            .position(latLng)
                            .snippet("Click to Reserve"));
                    mMap.setOnInfoWindowClickListener(MapsActivity.this);
                }
            }
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.0626, -94.1574), 10));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this, ReserveActivity.class);
        intent.putExtra("reserve_address", marker.getTitle());
        intent.putExtra("email", email);
        intent.putExtra("welcomeMessage", welcomeMessage);
        intent.putExtra("postList", postList);
        startActivity(intent);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.sparkit.sparkit/http/host/path")

        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    public void onSearch(View view) {
        EditText location_tf = (EditText)findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
            if(location != null || !location.equals("") ) {
                Geocoder geocoder = new Geocoder(this);
                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                //mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            }
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.sparkit.sparkit/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    public void backToMain(View view){
        finish();

        /*Intent intent = new Intent(this, MainPage.class);
        intent.putExtra("welcomeMessage", welcomeMessage);
        intent.putExtra("email",email);
        startActivity(intent);*/
    }



}