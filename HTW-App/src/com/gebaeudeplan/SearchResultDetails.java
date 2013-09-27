package com.gebaeudeplan;

import com.example.htw_app.MainActivity;
import com.example.htw_app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class SearchResultDetails extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchroomdetails);
        
        Bundle detailBundle = getIntent().getExtras();
        TextView title = (TextView) findViewById(R.id.textViewPopUpTitle);
        TextView content = (TextView) findViewById(R.id.textViewPopUpContent);
        
        title.setText(detailBundle.getCharSequence("title"));
        content.setText(detailBundle.getCharSequence("content"));
        boolean phoneExisting = detailBundle.getBoolean("phoneExisting");
        boolean mailExisting = detailBundle.getBoolean("mailExisting");
        
        ImageButton buttonPhone = (ImageButton) this.findViewById(R.id.imageButtonPopUpPhone);
        ImageButton buttonMail = (ImageButton) this.findViewById(R.id.imageButtonPopUpMail);
    	buttonPhone.setEnabled(false);
    	buttonMail.setEnabled(false);
        
        if (phoneExisting) {
        	buttonPhone.setEnabled(true);
        }
        if (mailExisting) {
        	buttonMail.setEnabled(true);
        }
        
        buttonPhone.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// GebaeudeplanActivity öffnen
				//Intent intentGebaeudeplan = new Intent(MainActivity.this, LoadScreenGebaeudeplan.class);
				//startActivity(intentGebaeudeplan);
			}
		});
        
        buttonMail.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// GebaeudeplanActivity öffnen
				//Intent intentGebaeudeplan = new Intent(MainActivity.this, LoadScreenGebaeudeplan.class);
				//startActivity(intentGebaeudeplan);
			}
		});
        
        
        
    }
}
