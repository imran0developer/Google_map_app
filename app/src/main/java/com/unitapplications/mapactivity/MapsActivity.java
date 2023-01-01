package com.unitapplications.mapactivity;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unitapplications.mapactivity.Adapters.Adapter;
import com.unitapplications.mapactivity.Api_Package.ApiClient;
import com.unitapplications.mapactivity.Api_Package.ApiSet;
import com.unitapplications.mapactivity.Models.ResponseModel;
import com.unitapplications.mapactivity.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE ="longitude";
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    String latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent getIntent = getIntent();
        latitude = getIntent.getStringExtra(LATITUDE);
        longitude = getIntent.getStringExtra(LONGITUDE);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in location and move the camera
        double lat = Double.parseDouble(latitude);
        double longi = Double.parseDouble(longitude);


        LatLng new_location = new LatLng(lat,longi);
        //LatLng sydney = new LatLng(17.323492863753206, 78.47343183512093);

        mMap.addMarker(new MarkerOptions().position(new_location).title("Marker is Here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new_location));
    }

}