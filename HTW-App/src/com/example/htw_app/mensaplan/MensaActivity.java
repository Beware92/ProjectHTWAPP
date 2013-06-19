package com.example.htw_app.mensaplan;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MensaActivity extends TabActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//setContentView(R.layout.activity_stundenplan);

		TabHost tabHost = getTabHost();

		// Tab for CAS
		TabSpec casSpec = tabHost.newTabSpec("Mensa CAS");
		casSpec.setIndicator("Mensa CAS");
		Intent casIntent = new Intent(this, SpeiseplanCAS.class);
		casSpec.setContent(casIntent);

		// Tab for CRB
		TabSpec crbSpec = tabHost.newTabSpec("Mensa CRB");
		crbSpec.setIndicator("Mensa CRB");
		Intent crbIntent = new Intent(this, SpeiseplanCRB.class);
		crbSpec.setContent(crbIntent);

		// Tab for CRP
		TabSpec crpSpec = tabHost.newTabSpec("Mensa CRP");
		crpSpec.setIndicator("Mensa CRP");
		Intent crpIntent = new Intent(this, SpeiseplanCRP.class);
		crpSpec.setContent(crpIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(casSpec);
		tabHost.addTab(crbSpec);
		tabHost.addTab(crpSpec);
	}
}