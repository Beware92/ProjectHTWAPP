package com.example.htw_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class RSSFeedDetails extends Activity {

	/**
	 * Anzeige der Webseite
	 */
	private WebView webView;
	/**
	 * Wartedialog der den Benutzer darüber informiert das die Webseite geladen
	 * wird
	 */
	private ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_anzeigen);

		// Zuweisung WebView
		webView = (WebView) findViewById(R.id.rssWebView);

		dialog = new ProgressDialog(this);
		dialog.setTitle("Bitte warten Sie!");
		dialog.setMessage("Die Website wird geladen.");

		// die Adresse der Nachricht aus der Main Activity
		String url = getIntent().getExtras().getString("URL");

		webView.setWebViewClient(new WebViewClient() {

			// wird ausgeführt wenn die Seite angefangen wird zuladen
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {

				// Anzeige des Wartedialog
				dialog.show();
			}

			// wird ausgeführt wenn die Seite fertig geladen ist
			@Override
			public void onPageFinished(WebView view, String url) {

				// falls der Wartedialog angezeigt wird soll er beendet werden
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
			}

			// wird ausgeführt wenn der User auf exterene Links auf der Webseite
			// klickt oder bei einer Weiterleitungen der Webseite
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				// aufgerufene Url wird innerhalb unserer Webview angezeigt
				view.loadUrl(url);
				return true;
			}
		});

		// laden der Url
		webView.loadUrl(url);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// wurde der Back-Button gedrückt und gibt es eine History von besuchten
		// Webseiten
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}

		// sollte die obere If-Abfrage false ergeben, wird das Event ausgeführt
		// das hinter dem gedrückten Button liegt
		return super.onKeyDown(keyCode, event);
	}
}
