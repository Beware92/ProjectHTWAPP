package com.vorlesungsplan;

import java.io.File;

import com.example.htw_app.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ETAbschluss extends Activity {


	
	final static String INGWIETB_URL = "http://www.htw-saarland.de/Members/e-sek/vorlesungsplan/bachelor-studiengang-elektrotechnik-sommersemester-2013";
	final static String INGWIETM_URL = "http://www.htw-saarland.de/Members/e-sek/vorlesungsplan/master-studiengang-elektrotechnik-sommersemester-2013";
	
	String Path = "/sdcard/Download/";
	
	
	File ETB = new File(Path + "bachelor-studiengang-elektrotechnik-sommersemester-2013.pdf");
	File ETM = new File(Path + "master-studiengang-elektrotechnik-sommersemester-2013.pdf");
	
	Abschluss[] items = { new Abschluss(1, "Bachelor"), 
			new Abschluss(2, " Master"),
			};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<Abschluss> adapter = new ArrayAdapter<Abschluss>(this,
				android.R.layout.simple_list_item_1, items);

		listView.setAdapter(adapter);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					if (ETB.exists()) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
	                    intent.setDataAndType(Uri.fromFile(ETB), "application/pdf");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(intent);
					}else{
						Intent intentMS = new Intent(Intent.ACTION_VIEW, Uri.parse(INGWIETB_URL));
						startActivity(intentMS);
					}
					break;
				case 1:
					if (ETM.exists()) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
	                    intent.setDataAndType(Uri.fromFile(ETM), "application/pdf");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(intent);
					}else{
						Intent intentMS = new Intent(Intent.ACTION_VIEW, Uri.parse(INGWIETM_URL));
						startActivity(intentMS);
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

}
