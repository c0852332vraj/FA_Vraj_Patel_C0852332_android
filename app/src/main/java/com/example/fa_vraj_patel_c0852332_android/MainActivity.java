package com.example.fa_vraj_patel_c0852332_android;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {


    Database_Handler db;
    GoogleMap mMap;
    LatLng Place = new LatLng(43.642567, -79.387054);
    List<Marker> markerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        db = new Database_Handler(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);

        markerList = new ArrayList<>();
        List<region> regionList = db.getAllRegions();

        for(region p: regionList){
            String myInfo = "ID: " + p.getId() + " Latitude: "+ p.getReglatitude() + "Longitude"
                    + p.getReglongitude() + " Title: " + p.getTitle();
            Log.d("myInfo", myInfo);

            markerList.add(mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(p.getReglatitude())
                            ,Double.parseDouble(p.getReglongitude()))).title(p.getTitle())
                    .zIndex( p.getId() ).snippet("By User")     ));
        }

        markerList.add(mMap.addMarker(new MarkerOptions()
                .position(Place).title("Cn Tower")));

        for(Marker m : markerList){
            // Add a marker in Sydney and move the camera
            LatLng latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng) );
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
        }
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        Intent intent = new Intent(MainActivity.this, insert.class);
        intent.putExtra("latitude" , latLng.latitude);
        intent.putExtra("longitude" , latLng.longitude);
        startActivity(intent);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Intent intent = new Intent(MainActivity.this, display.class);
        intent.putExtra("latitude" , marker.getPosition().latitude);
        intent.putExtra("longitude" , marker.getPosition().longitude);
        intent.putExtra("title" , marker.getTitle());
        intent.putExtra("id" ,  marker.getZIndex()) ;
        startActivity(intent);
        return false;
    }
}