package com.htw_app.notenauskunft;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Klasse zur implementierung einer Datenbank
 * 
 * @author Andreas Görres
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "notenauskunft.db";
	private static final int DATABASE_VERSION = 1;

	/*
	public static final String TABLE_NOTEN_KOPFDATEN = "noten_kopfdaten";
	public static final String NOTEN_KOPFDATEN_FIELD_ID = "id";
	public static final String NOTEN_KOPFDATEN_TYPE_ID = "INTEGER";
	public static final String NOTEN_KOPFDATEN_FIELD_MTKNR = "mtknr";
	public static final String NOTEN_KOPFDATEN_TYPE_MTKNR = "INTEGER";
	public static final String NOTEN_KOPFDATEN_FIELD_FNR = "fnr";
	public static final String NOTEN_KOPFDATEN_TYPE_FNR = "INTEGER";
	public static final String NOTEN_KOPFDATEN_FIELD_ABSCHNITT = "abschnitt";
	public static final String NOTEN_KOPFDATEN_TYPE_ABSCHNITT = "TEXT";
	public static final String NOTEN_KOPFDATEN_FIELD_STG = "stg";
	public static final String NOTEN_KOPFDATEN_TYPE_STG = "TEXT";
	public static final String NOTEN_KOPFDATEN_FIELD_FACH = "fach";
	public static final String NOTEN_KOPFDATEN_TYPE_FACH = "TEXT";
	public static final String NOTEN_KOPFDATEN_FIELD_VERSUCH = "versuch";
	public static final String NOTEN_KOPFDATEN_TYPE_VERSUCH = "INTEGER";
	public static final String NOTEN_KOPFDATEN_FIELD_PFLICHT = "pflicht";
	public static final String NOTEN_KOPFDATEN_TYPE_PFLICHT = "TEXT";
	public static final String NOTEN_KOPFDATEN_FIELD_WICHTUNG = "wichtung";
	public static final String NOTEN_KOPFDATEN_TYPE_WICHTUNG = "INTEGER";
	public static final String NOTEN_KOPFDATEN_FIELD_SEMESTER = "semester";
	public static final String NOTEN_KOPFDATEN_TYPE_SEMESTER = "INTEGER";
	public static final String NOTEN_KOPFDATEN_FIELD_PSTATUS = "pstatus";
	public static final String NOTEN_KOPFDATEN_TYPE_PSTATUS = "TEXT";
	public static final String NOTEN_KOPFDATEN_FIELD_CPCREDIT = "cpcredit";
	public static final String NOTEN_KOPFDATEN_TYPE_CPCREDIT = "INTEGER";
	public static final String NOTEN_KOPFDATEN_FIELD_REIHENFOLGE = "reihenfolge";
	public static final String NOTEN_KOPFDATEN_TYPE_REIHENFOLGE = "INTEGER";
	public static final String NOTEN_KOPFDATEN_FIELD_ABMELDEDATUM = "abmeldedatum";
	public static final String NOTEN_KOPFDATEN_TYPE_ABMELDEDATUM = "TEXT";
	public static final String NOTEN_KOPFDATEN_FIELD_ART = "art";
	public static final String NOTEN_KOPFDATEN_TYPE_ART = "TEXT";
	public static final String NOTEN_KOPFDATEN_FIELD_MODULNR = "modulnr";
	public static final String NOTEN_KOPFDATEN_TYPE_MODULNR = "TEXT";
	
	public static final String TABLE_NOTEN_DETAILDATEN = "noten_detaildaten";
	public static final String NOTEN_DETAILDATEN_FIELD_ID = "id";
	public static final String NOTEN_DETAILDATEN_TYPE_ID = "INTEGER";
	public static final String NOTEN_DETAILDATEN_FIELD_MTKNR = "mtknr";
	public static final String NOTEN_DETAILDATEN_TYPE_MTKNR = "INTEGER";
	public static final String NOTEN_DETAILDATEN_FIELD_FNR = "fnr";
	public static final String NOTEN_DETAILDATEN_TYPE_FNR = "INTEGER";
	public static final String NOTEN_DETAILDATEN_FIELD_STATUS = "status";
	public static final String NOTEN_DETAILDATEN_TYPE_STATUS = "TEXT";
	public static final String NOTEN_DETAILDATEN_FIELD_LFDVERSUCH = "lfdversuch";
	public static final String NOTEN_DETAILDATEN_TYPE_LFDVERSUCH = "INTEGER";
	public static final String NOTEN_DETAILDATEN_FIELD_PUNKTE = "punkte";
	public static final String NOTEN_DETAILDATEN_TYPE_PUNKTE = "REAL";
	public static final String NOTEN_DETAILDATEN_FIELD_PNOTE = "pnote";
	public static final String NOTEN_DETAILDATEN_TYPE_PNOTE = "REAL";
	public static final String NOTEN_DETAILDATEN_FIELD_PDATUM = "pdatum";
	public static final String NOTEN_DETAILDATEN_TYPE_PDATUM = "TEXT";
	public static final String NOTEN_DETAILDATEN_FIELD_GRUND = "grund";
	public static final String NOTEN_DETAILDATEN_TYPE_GRUND = "TEXT";
	public static final String NOTEN_DETAILDATEN_FIELD_SEMESTER = "semester";
	public static final String NOTEN_DETAILDATEN_TYPE_SEMESTER = "INTEGER";
	public static final String NOTEN_DETAILDATEN_FIELD_BEMERKUNG = "bemerkung";
	public static final String NOTEN_DETAILDATEN_TYPE_BEMERKUNG = "TEXT";
	public static final String NOTEN_DETAILDATEN_FIELD_BESPRECHUNG = "besprechung";
	public static final String NOTEN_DETAILDATEN_TYPE_BESPRECHUNG = "TEXT";
	public static final String NOTEN_DETAILDATEN_FIELD_DECNOTE = "decnote";
	public static final String NOTEN_DETAILDATEN_TYPE_DECNOTE = "REAL";
	*/
	
	public static final String TABLE_BENUTZER = "user";
	public static final String BENUTZER_FIELD_ID = "id";
	public static final String BENUTZER_TYPE_ID = "INTEGER";
	public static final String BENUTZER_FIELD_MTKNR = "mtknr";
	public static final String BENUTZER_TYPE_MTKNR = "INTEGER";
	public static final String BENUTZER_FIELD_PASSWORT = "passwort";
	public static final String BENUTZER_TYPE_PASSWORT = "TEXT";
	public static final String BENUTZER_FIELD_STATUS = "status";
	public static final String BENUTZER_TYPE_STATUS = "TEXT";
	
	/*
	private static final String CREATE_TABLE_NOTEN_KOPFDATEN = "CREATE TABLE " + TABLE_NOTEN_KOPFDATEN
	+ "("
		+ NOTEN_KOPFDATEN_FIELD_ID              + " " + NOTEN_KOPFDATEN_TYPE_ID                + " PRIMARY KEY AUTOINCREMENT" + ", "
		+ NOTEN_KOPFDATEN_FIELD_MTKNR           + " " + NOTEN_KOPFDATEN_TYPE_MTKNR                                            + ", "
		+ NOTEN_KOPFDATEN_FIELD_FNR             + " " + NOTEN_KOPFDATEN_TYPE_FNR                                              + ", "
		+ NOTEN_KOPFDATEN_FIELD_ABSCHNITT       + " " + NOTEN_KOPFDATEN_TYPE_ABSCHNITT                                        + ", "
		+ NOTEN_KOPFDATEN_FIELD_STG             + " " + NOTEN_KOPFDATEN_TYPE_STG                                              + ", "
		+ NOTEN_KOPFDATEN_FIELD_FACH            + " " + NOTEN_KOPFDATEN_TYPE_FACH                                             + ", "
		+ NOTEN_KOPFDATEN_FIELD_VERSUCH         + " " + NOTEN_KOPFDATEN_TYPE_VERSUCH                                          + ", "
		+ NOTEN_KOPFDATEN_FIELD_PFLICHT         + " " + NOTEN_KOPFDATEN_TYPE_PFLICHT                                          + ", "
		+ NOTEN_KOPFDATEN_FIELD_WICHTUNG        + " " + NOTEN_KOPFDATEN_TYPE_WICHTUNG                                         + ", "
		+ NOTEN_KOPFDATEN_FIELD_SEMESTER        + " " + NOTEN_KOPFDATEN_TYPE_SEMESTER                                         + ", "
		+ NOTEN_KOPFDATEN_FIELD_PSTATUS         + " " + NOTEN_KOPFDATEN_TYPE_PSTATUS                                          + ", "
		+ NOTEN_KOPFDATEN_FIELD_CPCREDIT        + " " + NOTEN_KOPFDATEN_TYPE_CPCREDIT                                         + ", "
		+ NOTEN_KOPFDATEN_FIELD_REIHENFOLGE     + " " + NOTEN_KOPFDATEN_TYPE_REIHENFOLGE                                      + ", "
		+ NOTEN_KOPFDATEN_FIELD_ABMELDEDATUM    + " " + NOTEN_KOPFDATEN_TYPE_ABMELDEDATUM                                     + ", "
		+ NOTEN_KOPFDATEN_FIELD_ART             + " " + NOTEN_KOPFDATEN_TYPE_ART                                              + ", "
		+ NOTEN_KOPFDATEN_FIELD_MODULNR         + " " + NOTEN_KOPFDATEN_TYPE_MODULNR
	+ ")";
	
	private static final String CREATE_TABLE_NOTEN_DETAILDATEN = "CREATE TABLE " + TABLE_NOTEN_DETAILDATEN
	+ "("
		+ NOTEN_DETAILDATEN_FIELD_ID           + " " + NOTEN_DETAILDATEN_TYPE_ID         + " PRIMARY KEY AUTOINCREMENT" + ", "
		+ NOTEN_DETAILDATEN_FIELD_MTKNR        + " " + NOTEN_DETAILDATEN_TYPE_MTKNR                                     + ", "
		+ NOTEN_DETAILDATEN_FIELD_FNR          + " " + NOTEN_DETAILDATEN_TYPE_FNR                                       + ", "
		+ NOTEN_DETAILDATEN_FIELD_STATUS       + " " + NOTEN_DETAILDATEN_TYPE_STATUS                                    + ", "
		+ NOTEN_DETAILDATEN_FIELD_LFDVERSUCH   + " " + NOTEN_DETAILDATEN_TYPE_LFDVERSUCH                                + ", "
		+ NOTEN_DETAILDATEN_FIELD_PUNKTE       + " " + NOTEN_DETAILDATEN_TYPE_PUNKTE                                    + ", "
		+ NOTEN_DETAILDATEN_FIELD_PNOTE        + " " + NOTEN_DETAILDATEN_TYPE_PNOTE                                     + ", "
		+ NOTEN_DETAILDATEN_FIELD_PDATUM       + " " + NOTEN_DETAILDATEN_TYPE_PDATUM                                    + ", "
		+ NOTEN_DETAILDATEN_FIELD_GRUND        + " " + NOTEN_DETAILDATEN_TYPE_GRUND                                     + ", "
		+ NOTEN_DETAILDATEN_FIELD_SEMESTER     + " " + NOTEN_DETAILDATEN_TYPE_SEMESTER                                  + ", "
		+ NOTEN_DETAILDATEN_FIELD_BEMERKUNG    + " " + NOTEN_DETAILDATEN_TYPE_BEMERKUNG                                 + ", "
		+ NOTEN_DETAILDATEN_FIELD_BESPRECHUNG  + " " + NOTEN_DETAILDATEN_TYPE_BESPRECHUNG                               + ", "
		+ NOTEN_DETAILDATEN_FIELD_DECNOTE      + " " + NOTEN_DETAILDATEN_TYPE_DECNOTE
	+ ")";
	*/
	
	private static final String CREATE_TABLE_BENUTZER = "CREATE TABLE " + TABLE_BENUTZER
	+ "("
		+ BENUTZER_FIELD_ID         + " " + BENUTZER_TYPE_ID           + ", "
		+ BENUTZER_FIELD_MTKNR      + " " + BENUTZER_TYPE_MTKNR        + ", "
		+ BENUTZER_FIELD_STATUS     + " " + BENUTZER_TYPE_STATUS       + ", "
		+ BENUTZER_FIELD_PASSWORT   + " " + BENUTZER_TYPE_PASSWORT
	+ ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		try {
			/*
			db.execSQL(CREATE_TABLE_NOTEN_KOPFDATEN);
			db.execSQL(CREATE_TABLE_NOTEN_DETAILDATEN);
			*/
			db.execSQL(CREATE_TABLE_BENUTZER);
			
			db.execSQL("INSERT INTO user (id, mtknr, passwort, status) VALUES ('1', '3584063', 'takeover', 'logout')");
		} catch (SQLException ex) {
			Log.d("HTW-App", "DatabaseHelper: " + ex);
		}
	}

	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {}

	public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) {}
}