package com.vorlesungsplan;

import com.example.htw_app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StundenPlanActivity extends Activity {

	Fakultaet[] items = { new Fakultaet(1, "IngWI"),
			new Fakultaet(2, "Architektur & Bauingenieur"),
			new Fakultaet(3, "Sozialwissenschaften"),

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView1 = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<Fakultaet> adapter = new ArrayAdapter<Fakultaet>(this,
				android.R.layout.simple_list_item_1, items);

		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					Intent myIntent = new Intent(StundenPlanActivity.this, IngWI.class);
					startActivity(myIntent);
					break;
				case 1:
					Intent myIntent1 = new Intent(StundenPlanActivity.this,
							ArchBau.class);
					startActivity(myIntent1);
					break;
				case 2:
					Intent myIntent2 = new Intent(StundenPlanActivity.this,
							Sozial.class);
					startActivity(myIntent2);
					break;
				
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
