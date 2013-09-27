package com.vorlesungsplan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.example.htw_app.R;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ArchAbschluss extends Activity {

	ProgressDialog mProgressDialog;
	
	final static String ARCHB_URL = "http://www.htw-saarland.de/aub/Studium/schule-fuer-architektur-saar/aktuell/ba_ss2013_kompakt2.pdf";
	final static String ARCHM_URL = "http://www.htw-saarland.de/aub/Studium/schule-fuer-architektur-saar/aktuell/ma_ss2013kompakt_130415.pdf";
	
	String FileName;
	String Path = "/sdcard/Download/";
	
	
	File B = new File(Path + "ba_ss2013_kompakt2.pdf");
	File M = new File(Path + "ma_ss2013kompakt_130415.pdf");
	
	Abschluss[] items = { new Abschluss(1, "Bachelor"), 
			new Abschluss(2, " Master"),
			};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vorlesungsplan);
		
		
		
		
		mProgressDialog = new ProgressDialog(ArchAbschluss.this);
		mProgressDialog.setMessage("A message");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		
		
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<Abschluss> adapter = new ArrayAdapter<Abschluss>(this,
				android.R.layout.simple_list_item_1, items);

		listView.setAdapter(adapter);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					if (B.exists()) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
	                    intent.setDataAndType(Uri.fromFile(B), "application/pdf");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(intent);
					}else{
						DownloadFilesTask downloadPDF = new DownloadFilesTask();
						downloadPDF.execute(ARCHB_URL);		
					}
						
					break;
				case 1:
					if (M.exists()) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
	                    intent.setDataAndType(Uri.fromFile(M), "application/pdf");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(intent);
					}else{
						DownloadFilesTask downloadPDF = new DownloadFilesTask();
						downloadPDF.execute(ARCHM_URL);		
					}
				}
			}
			
		});
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
				OutputStream fos = new FileOutputStream(Path + FileName);
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
			}
		 
	}

}
