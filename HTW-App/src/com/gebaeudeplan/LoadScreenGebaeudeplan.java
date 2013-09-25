package com.gebaeudeplan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.htw_app.R;
import com.larvalabs.svgandroid.SVGParser;

public class LoadScreenGebaeudeplan extends Activity {

	  //private LoadMapsTask loadMapsTask;
	  //private ImageView logo;
	  private static int SPLASH_TIME_OUT = 3000;
	  //private InitializeMapTask initializeMapTask;
	  //private HtwMapApplication map;
	  //private static PictureDrawable floorE;
	  
	  protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  Log.i("**", "1");
		  
		  setContentView(R.layout.activity_loadgebaeudeplan);
		  
		  
		  //initialize();
		  Intent localIntent = new Intent(LoadScreenGebaeudeplan.this, GebaeudeplanActivity.class);
		  localIntent.putEx
		  LoadScreenGebaeudeplan.this.startActivity(localIntent);
		  LoadScreenGebaeudeplan.this.finish();
	      //this.initializeMapTask = new InitializeMapTask();
	      //this.initializeMapTask.execute(map);
	  }
	    	
	  
	  

	  
	  private void initialize() {
		  //HtwMapApplication map = (HtwMapApplication)this.getApplication();
		  
		  //Erdgeschoss - 0
		  Log.i("**", "a");
		  PictureDrawable floorE = SVGParser.getSVGFromResource(LoadScreenGebaeudeplan.this.getResources(), R.raw.e).createPictureDrawable();
		  Log.i("**", "ab");
		  HtwMapApplication.getInstance().setMapFloorE(floorE);
		  Log.i("**", "b");

		  //map.setMapFloorE(floorE);
		  //Log.i("**", a.getMapFloorE().toString());
		  
		  /*
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
		  */
		  
	  }
	  
	  /*
	  private class InitializeMapTask extends AsyncTask<HtwMapApplication, Integer, Void> {

		  private InitializeMapTask() {
		  }
		  
		  //HtwMapApplication htwMap = new HtwMapApplication(); 
		  @Override
		  protected Void doInBackground(HtwMapApplication map) {
			   
			  //Erdgeschoss - 0
			  
			  try {
				  
				  //HtwMapApplication map = (HtwMapApplication)this.
				PictureDrawable floorE = SVGParser.getSVGFromResource(LoadScreenGebaeudeplan.this.getResources(), R.raw.e).createPictureDrawable();
				map.setMapFloorE(floorE);
				  //HtwMapApplication.getInstance().setMapFloorE(floorE);
				  //PictureDrawable floorENoText = SVGParser.getSVGFromResource(getResources(), R.raw.eno).createPictureDrawable();
				  //HtwMapApplication.getInstance().setMapFloorEnoDesc(floorENoText);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  /*
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
		  
		    protected void onCancelled() {
		      super.onCancelled();
		      LoadScreenGebaeudeplan.this.finish();
		    }		  
		  
	      protected void onPostExecute(Void paramVoid) {
			  super.onPostExecute(paramVoid);
			  Log.i("**", "onpostExecute");
			  //Log.i("*htwMap*", htwMap.getMapFloor1().toString());
			  Intent localIntent = new Intent(LoadScreenGebaeudeplan.this, GebaeudeplanActivity.class);
			  //LoadScreenGebaeudeplan.this.startActivity(localIntent);
			  //localIntent.setData(htwMap);
			  
			  LoadScreenGebaeudeplan.this.startActivity(localIntent);
			  LoadScreenGebaeudeplan.this.finish();
			  
		  }

		@Override
		protected Void doInBackground(HtwMapApplication... params) {
			// TODO Auto-generated method stub
			return null;
		}
		  
	  }	  
	  */
}

