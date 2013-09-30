package com.vorlesungsplan;

import com.example.htw_app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Klasse zum Abruf von Vorlesungsplaenen
 * @author marc.meese
 *
 */
public class StundenPlanActivity extends Activity {

	Fakultaet[] items = { new Fakultaet(1, "IngWI"),
			new Fakultaet(2, "Architektur & Bauingenieur"),
			new Fakultaet(3, "Sozialwissenschaften"),

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stundenplan);

		ImageButton buttonAuB = (ImageButton) this.
				findViewById(R.id.ibtnAuB);
		ImageButton buttonIngwi = (ImageButton) this
				.findViewById(R.id.ibtnIngwi);
		ImageButton buttonSowi = (ImageButton) this.
				findViewById(R.id.ibtnSowi);

		// Button-Listener
				buttonAuB.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {
						// AboutActivity öffnen
						Intent myIntent = new Intent(StundenPlanActivity.this, ArchBau.class);
						startActivity(myIntent);
					}
				});

				// Button-Listener
				buttonIngwi.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {
						// AboutActivity öffnen
						Intent myIntent = new Intent(StundenPlanActivity.this, IngWI.class);
						startActivity(myIntent);
					}
				});

				// Button-Listener
				buttonSowi.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {
						// AboutActivity öffnen
						Intent myIntent = new Intent(StundenPlanActivity.this, Sozial.class);
						startActivity(myIntent);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
