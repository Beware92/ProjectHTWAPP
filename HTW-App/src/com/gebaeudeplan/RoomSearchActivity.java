package com.gebaeudeplan;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ToggleButton;

import com.example.htw_app.R;

public class RoomSearchActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener, OnItemClickListener {
	    
    private int rowIdName;
    private int rowIdPreName;
    private int rowIdTel;
    private int rowIdRoom;
    private int rowIdTitle;
    private int rowIdMail;
    private int rowId;
    
    private PopupWindow popUpWindow;
    private ImageButton searchButton;
    private EditText searchText;
    private ListView listView;
    private List<SearchResultElement> resultList;
    
    
    private String regEx = ".*";
    private String vorwahl = "0681 5867 ";

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchroom);

        searchButton = (ImageButton) findViewById(R.id.roomSearchButton);
        searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				search();
				
			}
		});

        searchText = (EditText) findViewById(R.id.searchText);
        listView = (ListView) findViewById(R.id.roomList);      
    }
    

    private void search() {
    	try {
    		
    		
    		
    		resultList = new ArrayList<SearchResultElement>();
    		
			DatabaseHelper dbHelper;
			dbHelper = new DatabaseHelper(this);
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			
			String sql = "SELECT * FROM roomlist";
			Cursor result = db.rawQuery(sql, null);
			
			String[] searchString = searchText.getText().toString().split("\\s+");
			
			
			Log.i("Cursor", ""+result.getCount());

				
			if (result.moveToFirst()) {
				rowIdName = result.getColumnIndex(DatabaseHelper.TABLE_FIELD_NAME);
				rowIdPreName = result.getColumnIndex(DatabaseHelper.TABLE_FIELD_PRENAME);
				rowIdRoom = result.getColumnIndex(DatabaseHelper.TABLE_FIELD_ROOM);
				rowIdMail = result.getColumnIndex(DatabaseHelper.TABLE_FIELD_MAIL);
				rowIdTel = result.getColumnIndex(DatabaseHelper.TABLE_FIELD_TEL);
				rowIdTitle = result.getColumnIndex(DatabaseHelper.TABLE_FIELD_AMTSBEZ);
				rowId = result.getColumnIndex(DatabaseHelper.TABLE_FIELD_ID);
			}
			
			do {
				
				int matchCounter = 0;
				
				String name = result.getString(rowIdName);
				String prename = result.getString(rowIdPreName);
				String room = result.getString(rowIdRoom);
				String title = result.getString(rowIdTitle);
				String mail = result.getString(rowIdMail);
				String tel = result.getString(rowIdTel);
				int itemId = result.getInt(rowId);
				Log.i("**", name);
				
				for (int i = 0; i < searchString.length; i++) {
					Log.i("Search for", searchString[i]);
					if (matchesString(searchString[i], name, prename, room)) {
						matchCounter++;
					}
				}
				
				if (matchCounter == searchString.length) {

					SearchResultElement foundElement = new SearchResultElement(name, prename, room, title, mail, tel, itemId);
					resultList.add(foundElement);
					Log.i("***", foundElement.toString());
				}

			} while (result.moveToNext()); //FIXME DB schliessen
			
			if (resultList.size() == 0) {
				resultList.add(new SearchResultElement("Keine Einträge gefunden","","","","","",0));db.close();
				result.close();
				
				SearchResultAdapter adapter = new SearchResultAdapter(this, R.id.roomList, resultList);
				listView.setAdapter(adapter);
			} else {
				db.close();
				result.close();
				
				SearchResultAdapter adapter = new SearchResultAdapter(this, R.id.roomList, resultList);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(this);
				
				InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				mgr.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
			}
			

			
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private boolean matchesString (String toCompare, String s1, String s2, String s3) {
    	if (s1.matches(regEx + toCompare + regEx) | s2.matches(regEx + toCompare + regEx) | s3.matches(regEx + toCompare + regEx)) {
    		return true;
    	}
    	return false;
    }



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		SearchResultElement selectedElement = (SearchResultElement) listView.getItemAtPosition(position);
		
		String name = selectedElement.getName();
		String prename = selectedElement.getPrename();
		String room = selectedElement.getRoom();
		String tel = selectedElement.getTel();
		String title = selectedElement.getTitle();
		String mail = selectedElement.getMail();
		
		String info = "";
		String headLine = title + " " + prename + " " + name;

		

		
		if (room.length() > 1) {
			info = info + "Raum: " + room + "\n\n";
		}
		if (tel.length() > 2) {
			info = info + "Tel.: " + vorwahl + tel + "\n\n";
			
		}
		if (mail.length() > 5) {
			info = info + "E-Mail:\n" + mail + "\n";
		}
			
	
		Intent detailIntent = new Intent(getApplicationContext(), SearchResultDetails.class);
		Bundle detailBundle = new Bundle();
		
		detailBundle.putString("title", headLine);
		detailBundle.putString("content", info);
		detailBundle.putString("phone", vorwahl + tel);
		detailBundle.putString("mail", mail);
		
		detailIntent.putExtras(detailBundle);
		startActivity(detailIntent);
		

		
		
		//REM vortrag: oeffnungszeiten einfuegen + sonstige infos
		

		
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
	


    
}
