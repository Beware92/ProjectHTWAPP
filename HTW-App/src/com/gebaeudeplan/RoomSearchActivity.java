package com.gebaeudeplan;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RoomSearchActivity extends ListActivity {
	private ArrayList<String> results = new ArrayList<String>();
    private String tableName = DatabaseHelper.DATABASE_NAME;
    private SQLiteDatabase newDB;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAndQueryDatabase();
         
        displayResultList();
         
         
    }
    private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("This data is retrieved from the database and only 4 " +
                "of the results are displayed");
        getListView().addHeaderView(tView);
         
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item, results));
        getListView().setTextFilterEnabled(true);
         
    }
    private void openAndQueryDatabase() {
        try {
            DatabaseHelper dbHelper = new DatabaseHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT * FROM " + tableName , null);
 
            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                    	
                        String firstName = c.getString(c.getColumnIndex("vorname"));
                        String lastName = c.getString(c.getColumnIndex("nachname"));
                        //int age = c.getInt(c.getColumnIndex("Age"));
                        results.add("Name: " + firstName + ",Nachname: " + lastName);
                    } while (c.moveToNext());
                }
            }          
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
                newDB.close();
        }
 
    }
}
