package com.example.htw_app.mensaplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.htw_app.R;
import com.example.htw_app.SettingsActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SpeiseplanCAS extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	public static SpeiseplanCAS ma;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	// String
	public static ArrayList<Zeile> zeilenAdapter;
	// ArrayList<HashMap<String, String>> productsList;

	// url to get all products list
	private static final String URL_ALL_DATA_CAS = "http://htwapp.ohost.de/get_all_data_cas.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "data";
	private static final String TAG_PID = "id";
	private static final String TAG_NAME = "tag";
	private static final String TAG_1 = "menue1";
	private static final String TAG_2 = "menue2";

	// products JSONArray
	JSONArray products = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_mensa);

		// Hashmap for ListView
		zeilenAdapter = new ArrayList<Zeile>();

		// Loading products in Background Thread
		new LoadAllProducts().execute();

		final TextView textView3 = (TextView) findViewById(R.id.textView3);
		final TextView textView4 = (TextView) findViewById(R.id.textView4);
		final TextView textView5 = (TextView) findViewById(R.id.textView5);

		final TextView textView6 = (TextView) findViewById(R.id.textView6);
		final TextView textView7 = (TextView) findViewById(R.id.textView7);
		final TextView textView8 = (TextView) findViewById(R.id.textView8);

		final TextView textView9 = (TextView) findViewById(R.id.textView9);
		final TextView textView10 = (TextView) findViewById(R.id.textView10);
		final TextView textView11 = (TextView) findViewById(R.id.textView11);

		final TextView textView12 = (TextView) findViewById(R.id.textView12);
		final TextView textView13 = (TextView) findViewById(R.id.textView13);
		final TextView textView14 = (TextView) findViewById(R.id.textView14);

		final TextView textView15 = (TextView) findViewById(R.id.textView15);
		final TextView textView16 = (TextView) findViewById(R.id.textView16);
		final TextView textView17 = (TextView) findViewById(R.id.textView17);

		final Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				for (int i = 0; i < zeilenAdapter.size(); i++) {
					Zeile zeile = zeilenAdapter.get(i);
					if (i == 0) {
						textView3.setText(zeile.getTag());
						textView4.setText(zeile.getMenue1());
						textView5.setText(zeile.getMenue2());
					} else if (i == 1) {
						textView6.setText(zeile.getTag());
						textView7.setText(zeile.getMenue1());
						textView8.setText(zeile.getMenue2());
					} else if (i == 2) {
						textView9.setText(zeile.getTag());
						textView10.setText(zeile.getMenue1());
						textView11.setText(zeile.getMenue2());
					} else if (i == 3) {
						textView12.setText(zeile.getTag());
						textView13.setText(zeile.getMenue1());
						textView14.setText(zeile.getMenue2());
					} else if (i == 4) {
						textView15.setText(zeile.getTag());
						textView16.setText(zeile.getMenue1());
						textView17.setText(zeile.getMenue2());
					}
				}
			}
		});

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SpeiseplanCAS.this);
			pDialog.setMessage("Loading data. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(URL_ALL_DATA_CAS, "GET",
					params);

			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						int id = c.getInt(TAG_PID);
						String name = c.getString(TAG_NAME);
						String menue1 = c.getString(TAG_1);
						String menue2 = c.getString(TAG_2);

						Zeile zeile = new Zeile(id, name, menue1, menue2);

						// adding HashList to ArrayList
						zeilenAdapter.add(zeile);
					}
				} else {
					// no products found
					// Launch Add New product Activity
					// Intent i = new Intent(getApplicationContext(),
					// NewProductActivity.class);
					// Closing all previous activities
					// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */

					/*
					 * ListAdapter adapter = new SimpleAdapter(
					 * AllProductsActivity.this, productsList,
					 * R.layout.list_item, new String[] { TAG_PID, TAG_NAME },
					 * new int[] { R.id.pid, R.id.name }); // updating //
					 * listview setListAdapter(adapter);
					 */

				}
			});

		}

	}
}
