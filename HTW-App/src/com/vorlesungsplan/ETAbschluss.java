package com.vorlesungsplan;

import java.io.File;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import com.example.htw_app.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ETAbschluss extends Activity {

	PlanOpener opener;

	Abschluss[] items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vorlesungsplan);
		
		items =  new Abschluss[] {
				new Abschluss(1, getString(R.string.studiengang_bachelor)),
				new Abschluss(2, getString(R.string.studiengang_master)), };
		
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
					opener.openPDF(Globals.ETB, ETAbschluss.this);
					break;
				case 1:
					opener.openPDF(Globals.ETM, ETAbschluss.this);
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
