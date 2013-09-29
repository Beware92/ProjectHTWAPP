package com.gebaeudeplan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.htw_app.R;
import com.larvalabs.svgandroid.SVGParser;



public class GebaeudeplanActivity extends Activity {

	private TouchImageView touchImageView;
	private boolean showRoomDesc;
	private HtwMapApplication htwMap;
	private ToggleButton toggleButtonShowRoomDesc;
	private ImageButton buttonFloorUp;
	private ImageButton buttonFloorDown;
	private int currentFloor;
	private TextView tvCurrentFloor;
	private ImageButton buttonSearch;
	
	private ProgressDialog pDialog;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
    	new LoadAllProducts().execute();
    	

    	setContentView(R.layout.activity_gebaeudeplan);
    	
    	showRoomDesc = false;
    	currentFloor = 0;
    	

    	touchImageView = ((TouchImageView)findViewById(R.id.touchImageViewOut)); 
    	tvCurrentFloor = ((TextView)findViewById(R.id.textViewCurrentFloor));

    	
    	
    	toggleButtonShowRoomDesc = (ToggleButton) findViewById(R.id.toggleButtonRoomDesc);
    	toggleButtonShowRoomDesc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleButtonRoomDescClick();
				
			}
		});
    	
    	buttonFloorDown = (ImageButton) findViewById(R.id.ImageButtonFloorDown);
    	buttonFloorDown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("**", "Down");
				floorDown();
			}
		});
    
    	buttonFloorUp = (ImageButton) findViewById(R.id.ImageButtonFloorUp);
    	buttonFloorUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("**", "Up");
				floorUp();
			}
		});
    	
    	buttonSearch = (ImageButton) findViewById(R.id.buttonSearch);
    	buttonSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("**", "Search");
				startSearch();
			}
		});
	}
	
	private void setMapToE() {
		this.touchImageView.setImageDrawable(htwMap.getMapFloorEnoDesc());
		displayCurrentFloorNumber();
		
	}

	private void updateMapDrawable() {
		Log.i("**", "update " + showRoomDesc + " " + currentFloor);
		if (showRoomDesc) {
			switch (currentFloor) {
			
				case 0: displayMap(htwMap.getMapFloorE());
						break;
				case 1: displayMap(htwMap.getMapFloor1());
						break;
				case 2: displayMap(htwMap.getMapFloor2());
						break;
				case 3: displayMap(htwMap.getMapFloor3());
						break;
			}	
		} else {
			switch (currentFloor) {
			
				case 0: displayMap(htwMap.getMapFloorEnoDesc());
						break;
				case 1: displayMap(htwMap.getMapFloor1noDesc());
						break;
				case 2: displayMap(htwMap.getMapFloor2noDesc());
						break;
				case 3: displayMap(htwMap.getMapFloor3noDesc());
						break;
			}
		}
		
	}

	
	private void displayMap(Drawable mapToDisplay) {
		this.touchImageView.setImageDrawable(mapToDisplay);
	}
	
	private void displayCurrentFloorNumber() {
		switch (currentFloor) {
			case 0: tvCurrentFloor.setText("E");
				break;
			case 1: tvCurrentFloor.setText("1");
				break;
			case 2: tvCurrentFloor.setText("2");
				break;
			case 3: tvCurrentFloor.setText("3");
				break;
		}
	}
	
	public void toggleButtonRoomDescClick() {
		showRoomDesc = !showRoomDesc;
		updateMapDrawable();
	}
	
	private void floorUp() {

		if (currentFloor < 3) {
			currentFloor++;
			Log.i("**", "Etage" + currentFloor);
			updateMapDrawable();
			displayCurrentFloorNumber();
		}
	}
	
	private void floorDown() {

		if (currentFloor > 0) {
			currentFloor--;
			Log.i("**", "Etage" + getCurrentFloor());
			updateMapDrawable();
			displayCurrentFloorNumber();
		}
	}
	
	public void startSearch() {
		  
		  Intent searchIntent = new Intent(GebaeudeplanActivity.this, RoomSearchActivity.class);
		  GebaeudeplanActivity.this.startActivity(searchIntent);
		 
	}

	public boolean isShowRoomDesc() {
		return showRoomDesc;
	}

	public void setShowRoomDesc(boolean showRoomDesc) {
		this.showRoomDesc = showRoomDesc;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	
	
	class LoadAllProducts extends AsyncTask<String, String, String> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GebaeudeplanActivity.this);
			pDialog.setMessage("Loading data. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			htwMap = new HtwMapApplication();
			//Erdgeschoss - 0
		    PictureDrawable floorE = SVGParser.getSVGFromResource(getResources(), R.raw.e).createPictureDrawable();
		    htwMap.setMapFloorE(floorE);
			PictureDrawable floorENoText = SVGParser.getSVGFromResource(getResources(), R.raw.eno).createPictureDrawable();
			htwMap.setMapFloorEnoDesc(floorENoText);
			//Etage - 1
			PictureDrawable floorOne = SVGParser.getSVGFromResource(getResources(), R.raw.one).createPictureDrawable();
			htwMap.setMapFloor1(floorOne);
			PictureDrawable floorOneNoText = SVGParser.getSVGFromResource(getResources(), R.raw.oneno).createPictureDrawable();
			htwMap.setMapFloor1noDesc(floorOneNoText);
			//Etage - 2
			PictureDrawable floorTwo = SVGParser.getSVGFromResource(getResources(), R.raw.two).createPictureDrawable();
			htwMap.setMapFloor2(floorTwo);
			PictureDrawable floorTwoNoText = SVGParser.getSVGFromResource(getResources(), R.raw.twono).createPictureDrawable();
			htwMap.setMapFloor2noDesc(floorTwoNoText);
			//Etage - 3
			PictureDrawable floorTree = SVGParser.getSVGFromResource(getResources(), R.raw.tree).createPictureDrawable();
			htwMap.setMapFloor3(floorTree);
			PictureDrawable floorTreeNoText = SVGParser.getSVGFromResource(getResources(), R.raw.treeno).createPictureDrawable();
			htwMap.setMapFloor3noDesc(floorTreeNoText);
			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			setMapToE();
		}
	}
	
	

	
}
