package com.example.htw_app;

import java.io.File;

import org.mapsforge.android.maps.MapActivity;
import org.mapsforge.android.maps.MapView;

import android.app.Activity;
import android.os.Bundle;

public class GebaeudeplanActivity extends MapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapView mapView = new MapView(this);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMapFile(new File("/sdcard/saarland.map"));	//MUSS VORERST VON HAND ERSTELLT WERDEN
        setContentView(mapView);
    }
}

// @MARC: Der n�chste Schritt w�re, erstmal eine eigene Map aus der OpenStreetMap zu erstellen.
// Mapsforge ben�tigt ein bestimmtes Format. W�re gut, wenn du dich auch informieren w�rdest und mal versuchst
// selbst eine Map zu erstellen und zu konvertieren.