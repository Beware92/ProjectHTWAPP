package com.htw_app.notenauskunft;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.htw_app.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Notenauskunft extends Activity {

	public static DatabaseHelper mDbHelper;
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
	JSONArray products = null;
	public static ArrayList<User> refreshAdapter;
	public boolean login = false;

	private static final String URL_ALL_DATA_USER = "http://htwapp.ohost.de/get_all_data_user.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_USER = "data";
	private static final String TAG_ID = "id";
	private static final String TAG_MTKNR = "mtknr";
	private static final String TAG_PASSWORT = "passwort";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_notenauskunft);

		mDbHelper = new DatabaseHelper(this);
		refreshAdapter = new ArrayList<User>();

		final Button buttonLogin = (Button) this.findViewById(R.id.buttonLogin);
		final Button buttonShowList = (Button) this
				.findViewById(R.id.buttonShowList);

		buttonLogin.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (isOnline()) {
					new LoadAllProducts().execute();
				} else {
					Toast.makeText(Notenauskunft.this,
							"Internetverbindung notwendig!", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		buttonShowList.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (login == true) {

					Intent intentNotenauskunftList = new Intent(
							Notenauskunft.this, NotenauskunftList.class);
					startActivity(intentNotenauskunftList);
				} else {
					Toast.makeText(Notenauskunft.this, "Nicht angemeldet!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) {
			return true;
		}
		return false;
	}

	class LoadAllProducts extends AsyncTask<String, String, String> {
		final EditText editTextMatrikelnummer = (EditText) findViewById(R.id.editTextMatrikelnummer);
		final EditText editTextPasswort = (EditText) findViewById(R.id.editTextPasswort);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Notenauskunft.this);
			pDialog.setMessage("Loading data. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			refreshAdapter.clear();

			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				JSONObject json = jParser.makeHttpRequest(URL_ALL_DATA_USER,
						"GET", params);

				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					products = json.getJSONArray(TAG_USER);

					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						int id = c.getInt(TAG_ID);
						String mtknr = c.getString(TAG_MTKNR);
						String passwort = c.getString(TAG_PASSWORT);

						User user = new User(id, mtknr, passwort);
						refreshAdapter.add(user);
					}
				}

				for (int i = 0; i < refreshAdapter.size(); i++) {
					String nr = editTextMatrikelnummer.getText().toString();
					String pw = editTextPasswort.getText().toString();

					if (refreshAdapter.get(i).getMtknr().equals(nr)) {
						if (refreshAdapter.get(i).getPasswort().equals(pw)) {
							login = true;
							Log.d("HTW-App", "DatabaseHelper: " + login);
						}
						Log.d("HTW-App", "DatabaseHelper: test");
					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}

}
