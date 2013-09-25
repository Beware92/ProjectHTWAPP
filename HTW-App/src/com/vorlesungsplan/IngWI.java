package com.vorlesungsplan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.example.htw_app.R;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class IngWI extends Activity {
	
	
	ProgressDialog mProgressDialog;
	final static String INGWIPIKI_URL = "http://www-crypto.htw-saarland.de/weber/stundenplan/2013_ss.html";
	final static String INGWIMB_URL = "http://www.htw-saarland.de/Members/m-sek/vorlesungs-und-prufungsplane/vorlesungsplan/stundenplanss13_14052013.pdf";
	//final static String INGWIET_URL = "http://www.htw-saarland.de/Members/e-sek/vorlesungsplan/bachelor-studiengang-elektrotechnik-sommersemester-2013";
	final static String INGWIBT_URL ="http://www.htw-saarland.de/Members/michael.moeller/stundenplan/stundenplan-sommersemester-2013";
	final static String INGWIMS_URL ="http://www.htw-saarland.de/ingwi/studium/studienbereich-mechatronik-sensortechnik/mst_vorlesungen/vorlesungsplan-ss-2013";
	final static String INGWIEE_URL ="http://www.htw-saarland.de/ingwi/studium/studienbereich-energiesystemtechnik/dokumente/stundenplan-ee-stand-23-04-2013-pdf";
	
	
	
	
	
	
	String Path = "/sdcard/Download/";
	String FileName;
	File PK = new File(Path + "2013_ss.html");
	File MB = new File(Path + "stundenplanss13_14052013.pdf");
	File BT = new File(Path + "stundenplan-sommersemester-2013.pdf");
	File MS = new File(Path + "vorlesungsplan-ss-2013.pdf");
	File EE = new File(Path + "stundenplan-ee-stand-23-04-2013-pdf.pdf");
	

	Studiengang[] items = { new Studiengang(1, "PI"), new Studiengang(2, "KI"),
			new Studiengang(3, "Maschienenbau"),
			new Studiengang(4, "Elektrotechnik"),
			new Studiengang(5, "Biomedizinische Technik"),
			new Studiengang(6, "Mechtronik Sensortechnik"),
			new Studiengang(7, "Erneuerbare Energien/Energiesystemtechnik")};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mProgressDialog = new ProgressDialog(IngWI.this);
		mProgressDialog.setMessage("A message");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		
		
		ListView listView = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<Studiengang> adapter = new ArrayAdapter<Studiengang>(this,
				android.R.layout.simple_list_item_1, items);

		listView.setAdapter(adapter);
		
		
		
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					if (PK.exists()) {
						Intent myIntent = new Intent(IngWI.this, WEBVIEW.class);
						myIntent.putExtra("url", INGWIPIKI_URL);
						startActivity(myIntent);
					}else{
						DownloadFilesTask download = new DownloadFilesTask();
		                download.execute(INGWIPIKI_URL);
						Intent myIntent = new Intent(IngWI.this, WEBVIEW.class);
						myIntent.putExtra("url", INGWIPIKI_URL);
						startActivity(myIntent);	
					}
					break;
				case 1:
					if (PK.exists()) {
						Intent myIntent = new Intent(IngWI.this, WEBVIEW.class);
						myIntent.putExtra("url", INGWIPIKI_URL);
						startActivity(myIntent);
					}else{
						DownloadFilesTask download = new DownloadFilesTask();
		                download.execute(INGWIPIKI_URL);
						Intent myIntent = new Intent(IngWI.this, WEBVIEW.class);
						myIntent.putExtra("url", INGWIPIKI_URL);
						startActivity(myIntent);	
					}
					break;
				case 2:
					if (MB.exists()) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
	                    intent.setDataAndType(Uri.fromFile(MB), "application/pdf");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(intent);
					}else{
						DownloadFilesTask downloadPDF = new DownloadFilesTask();
						downloadPDF.execute(INGWIMB_URL);		
					}
					break;
				case 3:

						Intent intentET = new Intent(IngWI.this, ETAbschluss.class);
	                    startActivity(intentET);
					
					break;
				case 4:
					if (BT.exists()) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
	                    intent.setDataAndType(Uri.fromFile(BT), "application/pdf");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(intent);
					}else{
						Intent intentBT = new Intent(Intent.ACTION_VIEW, Uri.parse(INGWIBT_URL));
						startActivity(intentBT);
					}
					break;
				case 5:
					if (MS.exists()) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
	                    intent.setDataAndType(Uri.fromFile(MS), "application/pdf");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(intent);
					}else{
						Intent intentMS = new Intent(Intent.ACTION_VIEW, Uri.parse(INGWIMS_URL));
						startActivity(intentMS);
					}
					break;
				case 6:
					if (EE.exists()) {
						
					    
					      final Intent intent = new Intent(IngWI.this, PDFMethode.class);
					      intent.putExtra("pdf", EE);
					      startActivity(intent);
					    
					      
					    
					}else{
						Intent intentEE = new Intent(Intent.ACTION_VIEW, Uri.parse(INGWIEE_URL));
						startActivity(intentEE);
					}
					break;
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
