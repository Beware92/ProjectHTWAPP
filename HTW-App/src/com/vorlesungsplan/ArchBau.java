package com.vorlesungsplan;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.htw_app.R;

public class ArchBau extends Activity {

	ProgressDialog mProgressDialog;

	boolean filesLoaded = false;

	String FileName;
	int fileIndex;

	Studiengang[] items = { new Studiengang(1, "bauingenieur"),
			new Studiengang(2, "architektur"), };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vorlesungsplan);

		fileIndex = 0;

		mProgressDialog = new ProgressDialog(ArchBau.this);
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
					Intent intentBau = new Intent(ArchBau.this,
							BauAbschluss.class);
					startActivity(intentBau);
					break;
				case 1:
					Intent intentArch = new Intent(ArchBau.this,
							ArchAbschluss.class);
					startActivity(intentArch);
					break;

				}
			}
		});

	}

	private void downloadFiles() {
		Log.d("INFO", "downloadFiles()");
		if (filesLoaded == false) {
			DownloadFilesTask download = new DownloadFilesTask();

			switch (fileIndex) {

			case 0: {
				if (!(Globals.ARCHB.exists())) {
					// Architektur Bachelor
					download.execute(Globals.ARCHB_URL);
					Log.d("DOWNLOAD", Globals.ARCHB_URL);
				} else {
					fileIndex++;
					downloadFiles();
				}
				break;
			}
			case 1: {
				if (!(Globals.ARCHM.exists())) {
					// Architektur Master
					download.execute(Globals.ARCHM_URL);
					Log.d("DOWNLOAD", Globals.ARCHM_URL);
				} else {
					fileIndex++;
					downloadFiles();
				}
				break;
			}
			case 2: {
				if (!(Globals.BAUB.exists())) {
					// Bauingenieur Bachelor
					download.execute(Globals.BAUB_URL);
					Log.d("DOWNLOAD", Globals.BAUB_URL);
				} else {
					fileIndex++;
					downloadFiles();
				}
				break;
			}
			case 3: {

				if (!(Globals.BAUM.exists())) {
					// Bauingenieur Master
					download.execute(Globals.BAUM_URL);
					Log.d("DOWNLOAD", Globals.BAUM_URL);
				} else {
					fileIndex++;
					downloadFiles();
				}
				break;
			}
			case 5: {
				filesLoaded = true;
			}
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

}
