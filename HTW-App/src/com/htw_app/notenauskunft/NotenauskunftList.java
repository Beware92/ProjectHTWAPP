package com.htw_app.notenauskunft;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.htw_app.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Klasse NotenauskunftList zum darstellen von Noten
 * 
 * @author Andreas Görres
 */
public class NotenauskunftList extends Activity implements OnItemClickListener {
	private ListView listView;

	private ProgressDialog pDialog;
	private JSONParser jParser = new JSONParser();
	private JSONArray products = null;
	private JSONArray detail = null;
	public static ArrayList<Kopfdaten> refreshAdapter;
	public static ArrayList<Detaildaten> refreshDetail;
	public CustomListViewAdapter adapter;
	public NotenauskunftList nal;

	private static final String URL_ALL_DATA_NOTEN_KOPFDATEN = "http://htwapp.ohost.de/get_all_data_noten_kopfdaten.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_KOPFDATEN = "data";
	private static final String TAG_ID = "id";
	private static final String TAG_MTKNR = "mtknr";
	private static final String TAG_FNR = "fnr";
	private static final String TAG_ABSCHNITT = "abschnitt";
	private static final String TAG_STG = "stg";
	private static final String TAG_FACH = "fach";
	private static final String TAG_VERSUCH = "versuch";
	private static final String TAG_PFLICHT = "pflicht";
	private static final String TAG_WICHTUNG = "wichtung";
	private static final String TAG_SEMESTER = "semester";
	private static final String TAG_PSTATUS = "pstatus";
	private static final String TAG_CPCREDIT = "cpcredit";
	private static final String TAG_REIHENFOLGE = "reihenfolge";
	private static final String TAG_ABMELDEDATUM = "abmeldedatum";
	private static final String TAG_ART = "art";
	private static final String TAG_MODULNR = "modulnr";

	private static final String URL_ALL_DATA_NOTEN_DETAILDATEN = "http://htwapp.ohost.de/get_all_data_noten_detaildaten.php";
	private static final String TAG_SUCCESS2 = "success";
	private static final String TAG_DETAILDATEN = "data";
	private static final String TAG_ID2 = "id";
	private static final String TAG_MTKNR2 = "mtknr";
	private static final String TAG_FNR2 = "fnr";
	private static final String TAG_STATUS = "status";
	private static final String TAG_LFDVERSUCH = "lfdversuch";
	private static final String TAG_PUNKTE = "punkte";
	private static final String TAG_PNOTE = "pnote";
	private static final String TAG_PDATUM = "pdatum";
	private static final String TAG_GRUND = "grund";
	private static final String TAG_SEMESTER2 = "semester";
	private static final String TAG_BEMERKUNG = "bemerkung";
	private static final String TAG_BESPRECHUNG = "besprechung";
	private static final String TAG_DECNOTE = "decnote";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_notenauskunftlist);

		refreshAdapter = new ArrayList<Kopfdaten>();
		refreshAdapter.clear();
		refreshDetail = new ArrayList<Detaildaten>();
		refreshDetail.clear();
		listView = (ListView) findViewById(R.id.list);

		if (isOnline()) {
			new LoadAllProducts().execute();
		} else {
			Toast.makeText(NotenauskunftList.this,
					"Internetverbindung notwendig!", Toast.LENGTH_SHORT).show();
		}
	}
	
	/** Ueberprueft ob eine Internetverbindung besteht */
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) {
			return true;
		}
		return false;
	}

	/** Stellt alle Items aus dem Adapter dar */
	public boolean setAdapter() {
		adapter = new CustomListViewAdapter(this, R.layout.list_item,
				refreshAdapter);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		return true;
	}
	
	/** Zeigt Toast (mit weiteren Informationen) an wenn man auf ein Element in der Liste klickt */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		String suchString = refreshAdapter.get(position).getFnr();
		Log.d("HTW-App", "test:" + suchString);

		Detaildaten output = sucheDetail(suchString);

		if (output == null) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Daten konnten nicht gefunden werden!", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		} else {
			LayoutInflater inflater = getLayoutInflater();
	        View layout = inflater.inflate(R.layout.toast_layout_notenauskunft,
	                (ViewGroup) findViewById(R.id.toast_layout_root));
	        Toast toast = new Toast(getApplicationContext());
	        
	        TextView textViewDetailStatus = (TextView) layout.findViewById(R.id.textViewDetailStatus);
	        TextView textViewDetailVersuch = (TextView) layout.findViewById(R.id.textViewDetailVersuch);
	        TextView textViewDetailPunkte = (TextView) layout.findViewById(R.id.textViewDetailPunkte);
	        TextView textViewDetailNote = (TextView) layout.findViewById(R.id.textViewDetailNote);
	        TextView textViewDetailOptionaleNote = (TextView) layout.findViewById(R.id.textViewDetailOptionaleNote);
	        TextView textViewDetailSemester = (TextView) layout.findViewById(R.id.textViewDetailSemester);
	        TextView textViewDetailDatum = (TextView) layout.findViewById(R.id.textViewDetailDatum);
	        TextView textViewDetailBemerkung = (TextView) layout.findViewById(R.id.textViewDetailBemerkung);
	        TextView textViewDetailBesprechung = (TextView) layout.findViewById(R.id.textViewDetailBesprechung);
	        
	        textViewDetailStatus.setText("Status: " + output.getStatus());
	        textViewDetailVersuch.setText("Versuch: " + output.getLfdversuch());
	        textViewDetailPunkte.setText("Punkte: " + output.getPunkte());
	        textViewDetailNote.setText("Note: " + output.getPnote());
	        textViewDetailOptionaleNote.setText("Optionale Note: " + output.getDecnote());
	        textViewDetailSemester.setText("Semester: " + output.getSemester2());
	        textViewDetailDatum.setText("Datum: " + output.getPdatum());
	        textViewDetailBemerkung.setText("Bemerkung: " + output.getBemerkung());
	        textViewDetailBesprechung.setText("Besprechung: " + output.getBesprechung());
	        
	        toast.setGravity(Gravity.CENTER, 0, 0);
	        toast.setDuration(Toast.LENGTH_LONG);
	        toast.setView(layout);
	        toast.show();
		}
	}

	/** Sucht in der refreshDetail liste nach einem Element und gibt es mit der toString-Methode zurueck  */
	public Detaildaten sucheDetail(String suchString) {
		for (int i = 0; i < refreshDetail.size(); i++) {
			if (suchString.equals(refreshDetail.get(i).getFnr2())) {
				Log.d("HTW-App", "test2:" + refreshDetail.get(i).getFnr2());
				return refreshDetail.get(i);
			}
		}
		return null;
	}

	/** Laet daten aus der externen Datenbank */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NotenauskunftList.this);
			pDialog.setMessage("Loading data. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			String mtknrDB = "";

			try {
				SQLiteDatabase db = Notenauskunft.mDbHelper
						.getReadableDatabase();
				String sql = "SELECT * FROM " + DatabaseHelper.TABLE_BENUTZER
						+ " ORDER BY " + DatabaseHelper.BENUTZER_FIELD_ID;
				Cursor result = db.rawQuery(sql, null);

				if (result.moveToFirst()) {
					int campusIdx = result
							.getColumnIndex(DatabaseHelper.BENUTZER_FIELD_MTKNR);

					// do {
					mtknrDB = result.getString(campusIdx);
					// } while (result.moveToNext());

				}
				result.close();
				db.close();

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				JSONObject json = jParser.makeHttpRequest(
						URL_ALL_DATA_NOTEN_KOPFDATEN, "GET", params);

				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					products = json.getJSONArray(TAG_KOPFDATEN);

					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						String id = c.getString(TAG_ID);
						String mtknr = c.getString(TAG_MTKNR);
						String fnr = c.getString(TAG_FNR);
						String abschnitt = c.getString(TAG_ABSCHNITT);
						String stg = c.getString(TAG_STG);
						String fach = c.getString(TAG_FACH);
						String versuch = c.getString(TAG_VERSUCH);
						String pflicht = c.getString(TAG_PFLICHT);
						String wichtung = c.getString(TAG_WICHTUNG);
						String semester = c.getString(TAG_SEMESTER);
						String pstatus = c.getString(TAG_PSTATUS);
						String cpcredit = c.getString(TAG_CPCREDIT);
						String reihenfolge = c.getString(TAG_REIHENFOLGE);
						String abmeldedatum = c.getString(TAG_ABMELDEDATUM);
						String art = c.getString(TAG_ART);
						String modulnr = c.getString(TAG_MODULNR);

						if (mtknrDB.equals(mtknr)) {
							Kopfdaten rowItem = new Kopfdaten(id, mtknr, fnr,
									abschnitt, stg, fach, versuch, pflicht,
									wichtung, semester, pstatus, cpcredit,
									reihenfolge, abmeldedatum, art, modulnr);
							refreshAdapter.add(rowItem);
						}

					}
				}

				List<NameValuePair> params2 = new ArrayList<NameValuePair>();
				JSONObject json2 = jParser.makeHttpRequest(
						URL_ALL_DATA_NOTEN_DETAILDATEN, "GET", params2);

				int success2 = json2.getInt(TAG_SUCCESS2);

				if (success2 == 1) {
					detail = json2.getJSONArray(TAG_DETAILDATEN);

					for (int i = 0; i < detail.length(); i++) {
						JSONObject c = detail.getJSONObject(i);

						String id2 = c.getString(TAG_ID2);
						String mtknr2 = c.getString(TAG_MTKNR2);
						String fnr2 = c.getString(TAG_FNR2);
						String status = c.getString(TAG_STATUS);
						String lfdversuch = c.getString(TAG_LFDVERSUCH);
						String punkte = c.getString(TAG_PUNKTE);
						String pnote = c.getString(TAG_PNOTE);
						String pdatum = c.getString(TAG_PDATUM);
						String grund = c.getString(TAG_GRUND);
						String semester2 = c.getString(TAG_SEMESTER2);
						String bemerkung = c.getString(TAG_BEMERKUNG);
						String besprechung = c.getString(TAG_BESPRECHUNG);
						String decnote = c.getString(TAG_DECNOTE);

						if (mtknrDB.equals(mtknr2)) {
							Detaildaten rowItem2 = new Detaildaten(id2, mtknr2,
									fnr2, status, lfdversuch, punkte, pnote,
									pdatum, grund, semester2, bemerkung,
									besprechung, decnote);
							refreshDetail.add(rowItem2);
						}

					}
				}

			} catch (Exception e) {
				//Log.e("HTW-App", "test:", e);
			}

			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();

			setAdapter();
		}
	}
}