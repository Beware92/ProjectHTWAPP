package com.vorlesungsplan;

import com.example.htw_app.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * Klasse zur Verwaltung von Wirtschaftsfakultaeten
 * @author marc.meese
 *
 */
public class Wirtschaft extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vorlesungsplan);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
