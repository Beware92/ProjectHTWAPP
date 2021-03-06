package com.example.htw_app;

import com.htw_app.mensaplan.*;
import com.gebaeudeplan.GebaeudeplanActivity;
import com.htw_app.mensaplan.MensaActivity;
import com.htw_app.notenauskunft.Notenauskunft;
import com.vorlesungsplan.StundenPlanActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.app.Activity;
import android.content.Intent;

/* MainActivity, zeigt Buttons
 * an um auf andere
 * Activitys zu switchen 
 */
public class MainActivity extends Activity {

	// onCreate, aktionen beim Starten der Activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Buttons einbinden
		ImageButton buttonAbout = (ImageButton) this
				.findViewById(R.id.buttonAbout);
		ImageButton buttonGebaeudeplan = (ImageButton) this
				.findViewById(R.id.buttonGebaeudeplan);
		ImageButton buttonMensa = (ImageButton) this
				.findViewById(R.id.buttonMensa);
		ImageButton buttonNews = (ImageButton) this
				.findViewById(R.id.buttonNews);
		ImageButton buttonNotenauskunft = (ImageButton) this
				.findViewById(R.id.buttonNotenauskunft);
		ImageButton buttonStundenplan = (ImageButton) this
				.findViewById(R.id.buttonStundenplan);

		// Button-Listener
		buttonAbout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {	
				// AboutActivity �ffnen
				Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(intentAbout);
			}
		});

		buttonGebaeudeplan.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// GebaeudeplanActivity �ffnen
				Intent intentGebaeudeplan = new Intent(MainActivity.this, GebaeudeplanActivity.class);
				startActivity(intentGebaeudeplan);
			}
		});

		buttonMensa.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// MensaActivity �ffnen
				Intent intentMensa = new Intent(MainActivity.this, MensaActivity.class);
				startActivity(intentMensa);
			}
		});

		buttonNews.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// NewsActivity �ffnen
				Intent intentNews = new Intent(MainActivity.this, RSSNewsAnzeigen.class);
				startActivity(intentNews);
			}
		});

		buttonNotenauskunft.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// NotenauskunftActivity �ffnen
				Intent intentNotenauskunft = new Intent(MainActivity.this, Notenauskunft.class);
				startActivity(intentNotenauskunft);
			}
		});

		buttonStundenplan.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// StundenplanActivity �ffnen
				Intent intentStundenplan = new Intent(MainActivity.this, StundenPlanActivity.class);
				startActivity(intentStundenplan);
			}
		});
	}


}

