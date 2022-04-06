package com.example.hp8440p.myapplication.Map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.example.hp8440p.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("SCHOOL"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng fptPoly = new LatLng(16.075737, 108.169893);
        LatLng fptPolyTayNguyen = new LatLng(12.687285, 108.054364);
        LatLng fptPolyCanTho = new LatLng(10.027133, 105.757310);
        LatLng fptHCM = new LatLng(10.812026, 106.679903);
        LatLng fptHN = new LatLng(21.042981, 105.790156);
        mMap.addMarker(new MarkerOptions().position(fptPoly).title("FPT POLYTECHNIC DN"));
        mMap.addMarker(new MarkerOptions().position(fptPolyTayNguyen).title("FPT POLYTECHNIC TN"));
        mMap.addMarker(new MarkerOptions().position(fptPolyCanTho).title("FPT POLYTECHNIC CT"));
        mMap.addMarker(new MarkerOptions().position(fptHCM).title("FPT POLYTECHNIC HCM"));
        mMap.addMarker(new MarkerOptions().position(fptHN).title("FPT POLYTECHNIC HN"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fptPoly));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}