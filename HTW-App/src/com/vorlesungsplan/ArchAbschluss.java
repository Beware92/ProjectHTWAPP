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

/**
 * Klasse zur Verwaltung des Studiengangs "Architektur"
 * @author marc.meese
 *
 */
public class ArchAbschluss extends Activity {

	ProgressDialog mProgressDialog;
	
	int fileIndex;
		
	PlanOpener opener;
	
	Abschluss[] items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vorlesungsplan);
		
		items = new Abschluss[] { new Abschluss(1, getString(R.string.studiengang_bachelor)), 
				new Abschluss(2, getString(R.string.studiengang_master)),
				};
		
		opener = new PlanOpener();		
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<Abschluss> adapter = new ArrayAdapter<Abschluss>(this,
				android.R.layout.simple_list_item_1, items);

		listView.setAdapter(adapter);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					opener.openPDF(Globals.ARCHB,ArchAbschluss.this);
					break;
				case 1:
					opener.openPDF(Globals.ARCHM,ArchAbschluss.this);
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
	
	

}
