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

public class Sozial extends Activity {

	final static String SOZIALMEPG_URL="http://www.htw-saarland.de/sowi/Studium/studienangebot/BAME/vp-bame-ss-2013-2";
	
	
	String Path = "/sdcard/Download/";
	String FileName;
	File MEPG = new File(Path + "vp-bame-ss-2013-2");
	
	
	
	
	Studiengang[] items = { new Studiengang(1, "Management und Expertise im Pflege- und Gesundheitswesen"), 
			new Studiengang(2, "Soziale Arbeit und PŠdagogik der Kindheit"),
			new Studiengang(3, "PŠdagogik der Kindheit"),
			new Studiengang(4, "Pflege")
			};
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<Studiengang> adapter = new ArrayAdapter<Studiengang>(this,
				android.R.layout.simple_list_item_1, items);

		listView.setAdapter(adapter);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					if (MEPG.exists()) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
	                    intent.setDataAndType(Uri.fromFile(MEPG), "application/pdf");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(intent);
					}else{
						Intent intentMEPG = new Intent(Intent.ACTION_VIEW, Uri.parse(SOZIALMEPG_URL));
						startActivity(intentMEPG);
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
