package com.vorlesungsplan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import com.example.htw_app.R;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class IngWI extends Activity {
	
	boolean filesLoaded= false;
	PlanOpener opener;
	
	ProgressDialog mProgressDialog;
	
	
	int fileIndex;
	
	
	
	
	String FileName;
	
	

	Studiengang[] items = { new Studiengang(1, "PI"), new Studiengang(2, "KI"),
			new Studiengang(3, "Maschienenbau"),
			new Studiengang(4, "Elektrotechnik"),
			new Studiengang(5, "Biomedizinische Technik"),
			new Studiengang(6, "Mechtronik Sensortechnik"),
			new Studiengang(7, "Erneuerbare Energien/Energiesystemtechnik")};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vorlesungsplan);

		opener = new PlanOpener();
		
		//Zähler für downloadTask
		fileIndex = 0;
		
		//Ordner erstellen
		File folder = new File(Globals.vl_Path);
		if(!(folder.exists())){
			folder.mkdirs();
		}	
		Log.d("INFO", "Ordner "+Globals.vl_Path + " erstellt");
		
		

		mProgressDialog = new ProgressDialog(IngWI.this);
		mProgressDialog.setMessage("Download...");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		

		//Plaene downloaden
		downloadFiles();
		
		ListView listView = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<Studiengang> adapter = new ArrayAdapter<Studiengang>(this,
				android.R.layout.simple_list_item_1, items);

		listView.setAdapter(adapter);
		
		
		
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					Log.d("Filepath", Globals.PK.getAbsolutePath());
						opener.openHTML(Globals.PK.getAbsolutePath(),IngWI.this);
					break;
				case 1:
						opener.openHTML(Globals.PK.getAbsolutePath(),IngWI.this);
					break;
				case 2:
						opener.openPDF(Globals.MB,IngWI.this);						
					break;
				case 3:
						Intent intentET = new Intent(IngWI.this, ETAbschluss.class);
	                    startActivity(intentET);
					
					break;
				case 4:
					
						opener.openPDF(Globals.BT,IngWI.this);
					break;
				case 5:
						opener.openPDF(Globals.MS,IngWI.this);
					break;
				case 6:
						opener.openPDF(Globals.EE,IngWI.this);
					break;
				}
			}
		});
	}
	
	private void downloadFiles(){
		Log.d("INFO", "downloadFiles()");
		if(filesLoaded == false){
			
			if(isOnline()){
				try{
					DownloadFilesTask download = new DownloadFilesTask();
					
					switch (fileIndex){

						case 0:{
							if(!(Globals.MB.exists())){
								//Maschinenbau
								download.execute(Globals.INGWIMB_URL);	
								Log.d("DOWNLOAD", Globals.INGWIMB_URL);						
							}				
							else{
								fileIndex++;
								downloadFiles();
							}
							break;
						}
						case 1:{
							if(!(Globals.PK.exists())){
								//PI/KI
						        download.execute(Globals.INGWIPIKI_URL);
						        Log.d("DOWNLOAD", Globals.INGWIPIKI_URL);
							}
							else{
								fileIndex++;
								downloadFiles();
							}
							break;
						}
						case 2:{
							if(!(Globals.BT.exists())){
								 //Biomediz. Technik
						        download.execute(Globals.INGWIBT_URL);
								Log.d("DOWNLOAD", Globals.INGWIBT_URL);
							}
							else{
								fileIndex++;
								downloadFiles();
							}
							break;
						}
						case 3:{
							
							if(!(Globals.MS.exists())){
								// Mechatronik Sensortechnik
								download.execute(Globals.INGWIMS_URL);
								Log.d("DOWNLOAD", Globals.INGWIMS_URL);
							}
							else{
								fileIndex++;
								downloadFiles();
							}
							break;
						}
						case 4:{
							if(!(Globals.EE.exists())){
								//Erneuerbare Energien
								download.execute(Globals.INGWIEE_URL);
								Log.d("DOWNLOAD", Globals.INGWIEE_URL);
							}
							else{
								fileIndex++;
								downloadFiles();
							}
							break;
						}
						case 5:{
							if(!(Globals.ETB.exists())){
								//Elektrotechnik Bachelor
								download.execute(Globals.INGWIETB_URL);
								Log.d("DOWNLOAD", Globals.INGWIETB_URL);
							}
							else{
								fileIndex++;
								downloadFiles();
							}
							break;
						}
						case 6:{
							if(!(Globals.ETM.exists())){
								//Elektrotechnik Master
								download.execute(Globals.INGWIETM_URL);
								Log.d("DOWNLOAD", Globals.INGWIETM_URL);
							}
							else{
								fileIndex++;
								downloadFiles();
							}
							break;
						}
						case 7:{
							filesLoaded = true;
						}
					}       
				}
				catch (Exception e){
					Toast toast = Toast.makeText(this, Globals.NETWORK_ABORT, Toast.LENGTH_SHORT);
					toast.show();
				}
				
			}
			else{
				Toast toast = Toast.makeText(this, Globals.NETWORK_OFFLINE, Toast.LENGTH_SHORT);
				toast.show();
			}
			
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	private class DownloadFilesTask extends AsyncTask<String, Integer, String> {

		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        mProgressDialog.show();
	    }

	   
	
		protected String doInBackground(String... sUrl) {
			try {

				URL url = new URL(sUrl[0]);
				FileName = url.toString().substring(
						url.toString().lastIndexOf("/"));

				URLConnection conn = url.openConnection();
				conn.connect();
				int fileLength = conn.getContentLength();

				InputStream bis = new BufferedInputStream(url.openStream());
				OutputStream fos = new FileOutputStream(Globals.vl_Path + FileName);
				/*
				 * Read bytes to the Buffer until there is nothing more to
				 * read(-1).
				 */
	            byte data[] = new byte[1024];
	            long total = 0;
	            int count;
	            while ((count = bis.read(data)) != -1) {
	                total += count;
	                // publishing the progress....
	                publishProgress((int) (total * 100 / fileLength));
	                fos.write(data, 0, count);
	            }
	            fos.flush();
				fos.close();
				bis.close();


				
			} catch (Exception e) {
			}
			return null;
		}
		 @Override
		    protected void onProgressUpdate(Integer... progress) {
		        super.onProgressUpdate(progress);
		        mProgressDialog.setProgress(progress[0]);
		        
		    }
		 @Override
			protected void onPostExecute(String unused) {
			 mProgressDialog.dismiss();			
			 fileIndex++;
			 downloadFiles();
			}
		 
	}
	
	/**
	 * Methode um Online-Verfuegbarkeit zu testen
	 * @return Online Offline
	 */
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}	
	
}
