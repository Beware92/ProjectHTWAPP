package com.vorlesungsplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.htw_app.R;

public class ArchBau extends Activity {

	Studiengang[] items = { new Studiengang(1, "bauingenieur"), 
			new Studiengang(2, "architektur"),
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
					Intent intentBau = new Intent(ArchBau.this, BauAbschluss.class);
                    startActivity(intentBau);
					break;
				case 1:
					Intent intentArch = new Intent(ArchBau.this, ArchAbschluss.class);
                    startActivity(intentArch);
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
