package com.vorlesungsplan;

import com.example.htw_app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Klasse zur Anzeige von HTML Dateien
 * @author marc.meese
 *
 */
public class HTMLVIEW extends Activity {

	
	String fileName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		
		
		
		WebView myWebView = (WebView) findViewById(R.id.webview);
       
        Intent urlIntent = getIntent();
        fileName = urlIntent.getCharSequenceExtra("url").toString();
        
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("file://"+fileName);                   
  

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.webview, menu);
		return true;
	}
	
}
