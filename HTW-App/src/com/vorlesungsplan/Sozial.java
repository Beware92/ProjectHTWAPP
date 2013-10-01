package com.vorlesungsplan;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.htw_app.R;

/**
 * Klasse zur Verwaltung des Studiengangs "Sozial"
 * @author marc.meese
 *
 */
public class Sozial extends Activity {

	boolean filesLoaded = false;
	PlanOpener opener;
	int fileIndex;
	ProgressDialog mProgressDialog;

	String FileName;

	Studiengang[] items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vorlesungsplan);
		
		items  = new Studiengang[]{
				new Studiengang(1,
						getString(R.string.studiengang_mueipugs)) };

		fileIndex = 0;
		opener = new PlanOpener();

		mProgressDialog = new ProgressDialog(Sozial.this);
		mProgressDialog.setMessage("Download...");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(100);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

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
					opener.openPDF(Globals.SOZIALMEPG, Sozial.this);
					break;

				}
			}
		});

	}

	/**
	 * Methode um Vorlesungsplan herunterzuladen
	 * @author marc.meese
	 *
	 */
	private void downloadFiles() {
		Log.d("INFO", "downloadFiles()");
		if (filesLoaded == false) {
			if (isOnline()) {
				try {
					DownloadFilesTask download = new DownloadFilesTask();

					switch (fileIndex) {

					case 0: {
						if (!(Globals.SOZIALMEPG.exists())) {
							// Sozialwissenschaften
							download.execute(Globals.SOZIALMEPG_URL);
							Log.d("DOWNLOAD", Globals.SOZIALMEPG_URL);
						} else {
							fileIndex++;
							downloadFiles();
						}
						break;
					}
					case 1: {

						filesLoaded = true;
						break;
					}
					}
				} catch (Exception e) {
					Toast toast = Toast.makeText(this, Globals.NETWORK_ABORT,
							Toast.LENGTH_SHORT);
					toast.show();
				}

			} else {
				Toast toast = Toast.makeText(this, Globals.NETWORK_OFFLINE,
						Toast.LENGTH_SHORT);
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

	/**
	 * Klasse zur Verwaltung des Plandownloads
	 * @author marc.meese
	 *
	 */
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
				OutputStream fos = new FileOutputStream(Globals.vl_Path
						+ FileName);
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
	 * 
	 * @return Online Offline
	 */
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

}
