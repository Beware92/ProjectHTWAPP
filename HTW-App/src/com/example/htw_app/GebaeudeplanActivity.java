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

// @MARC: Der nächste Schritt wäre, erstmal eine eigene Map aus der OpenStreetMap zu erstellen.
// Mapsforge benötigt ein bestimmtes Format. Wäre gut, wenn du dich auch informieren würdest und mal versuchst
// selbst eine Map zu erstellen und zu konvertieren.