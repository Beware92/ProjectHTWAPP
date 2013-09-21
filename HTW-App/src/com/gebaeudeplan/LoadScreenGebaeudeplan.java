package com.gebaeudeplan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.htw_app.R;
import com.larvalabs.svgandroid.SVGParser;

public class LoadScreenGebaeudeplan extends Activity {

	  private LoadMapsTask loadMapsTask;
	  //private ImageView logo;
	  
	  
	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    Log.i("**", "1");
	    setContentView(R.layout.activity_loadgebaeudeplan);
	    
	    //this.loadMapsTask = new LoadMapsTask(null);
	    //this.loadMapsTask.execute(new Void[0]);
	    Log.i("**", "2");

		Log.i("**", "3");
		  Intent localIntent = new Intent(LoadScreenGebaeudeplan.this, GebaeudeplanActivity.class);
		  Log.i("**", "4");
		  LoadScreenGebaeudeplan.this.startActivity(localIntent);
		  Log.i("**", "5");
		  //LoadScreenGebaeudeplan.this.finish();
		  Log.i("**", "6");
		
	  }
	  
	  protected void onDestroy() {
		  //this.loadMapsTask.cancel(true);
		  super.onDestroy();
	  }
	  
	  
	  private class LoadMapsTask extends AsyncTask<Void, Integer, Void> {
		  
		  private LoadMapsTask(String a) {
		  }

		  protected Void doInBackground(Void[] paramArrayOfVoid)
		  {
			  
			if (!isCancelled()) {
				  PictureDrawable floorE = SVGParser.getSVGFromResource(LoadScreenGebaeudeplan.this.getResources(), R.raw.e).createPictureDrawable();
				  HtwMapApplication.getInstance().setMapFloorE(floorE);
			        Integer[] arrayOfInteger2 = new Integer[1];
			        arrayOfInteger2[0] = Integer.valueOf(50);
			        publishProgress(arrayOfInteger2);
			}
			if (!isCancelled()) {
			  PictureDrawable floorEnoText = SVGParser.getSVGFromResource(LoadScreenGebaeudeplan.this.getResources(), R.raw.e).createPictureDrawable();
			  HtwMapApplication.getInstance().setMapFloorEnoDesc(floorEnoText);
		        Integer[] arrayOfInteger1 = new Integer[1];
		        arrayOfInteger1[0] = Integer.valueOf(10);
		        publishProgress(arrayOfInteger1);

			}	
			
			  
			  //Hier weitere Stockwerke adden
			  return null;
		  }

		  protected void onCancelled() {
			  super.onCancelled();
			  LoadScreenGebaeudeplan.this.finish();
		  }

		  protected void onPostExecute(Void paramVoid) {
			  super.onPostExecute(paramVoid);
			  Intent localIntent = new Intent(LoadScreenGebaeudeplan.this, GebaeudeplanActivity.class);
			  LoadScreenGebaeudeplan.this.startActivity(localIntent);
			  LoadScreenGebaeudeplan.this.finish();
		  }
	  }
	  
}

