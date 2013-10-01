package com.htw_app.mensaplan;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "menue.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_MENUE = "menue";
	public static final String MENUE_FIELD_ID = "id";
	public static final String MENUE_TYPE_ID = "INTEGER";
	public static final String MENUE_FIELD_TAG = "tag";
	public static final String MENUE_TYPE_TAG = "TEXT";
	public static final String MENUE_FIELD_MENUE1 = "menue1";
	public static final String MENUE_TYPE_MENUE1 = "TEXT";
	public static final String MENUE_FIELD_MENUE2 = "menue2";
	public static final String MENUE_TYPE_MENUE2 = "TEXT";
	
	public static final String TABLE_MENUE_CRB = "menue_crb";
	public static final String MENUE_CRB_FIELD_ID = "id";
	public static final String MENUE_CRB_TYPE_ID = "INTEGER";
	public static final String MENUE_CRB_FIELD_TAG = "tag";
	public static final String MENUE_CRB_TYPE_TAG = "TEXT";
	public static final String MENUE_CRB_FIELD_MENUE1 = "menue1";
	public static final String MENUE_CRB_TYPE_MENUE1 = "TEXT";
	public static final String MENUE_CRB_FIELD_MENUE2 = "menue2";
	public static final String MENUE_CRB_TYPE_MENUE2 = "TEXT";
	
	public static final String TABLE_MENUE_CRP = "menue_crp";
	public static final String MENUE_CRP_FIELD_ID = "id";
	public static final String MENUE_CRP_TYPE_ID = "INTEGER";
	public static final String MENUE_CRP_FIELD_TAG = "tag";
	public static final String MENUE_CRP_TYPE_TAG = "TEXT";
	public static final String MENUE_CRP_FIELD_MENUE1 = "menue1";
	public static final String MENUE_CRP_TYPE_MENUE1 = "TEXT";
	public static final String MENUE_CRP_FIELD_MENUE2 = "menue2";
	public static final String MENUE_CRP_TYPE_MENUE2 = "TEXT";

	public static final String TABLE_INFORMATIONEN = "informationen";
	public static final String INFORMATIONEN_FIELD_ID = "id";
	public static final String INFORMATIONEN_TYPE_ID = "INTEGER";
	public static final String INFORMATIONEN_FIELD_CAMPUS = "campus";
	public static final String INFORMATIONEN_TYPE_CAMPUS = "TEXT";
	public static final String INFORMATIONEN_FIELD_WOCHE = "woche";
	public static final String INFORMATIONEN_TYPE_WOCHE = "TEXT";
	public static final String INFORMATIONEN_FIELD_BEILAGEN = "beilagen";
	public static final String INFORMATIONEN_TYPE_BEILAGEN = "TEXT";
	public static final String INFORMATIONEN_FIELD_ESSENSAUSGABE = "essensausgabe";
	public static final String INFORMATIONEN_TYPE_ESSENSAUSGABE = "TEXT";

	private static final String CREATE_TABLE_MENUE = "CREATE TABLE "
			+ TABLE_MENUE + "(" + MENUE_FIELD_ID + " " + MENUE_TYPE_ID
			+ " PRIMARY KEY AUTOINCREMENT" + ", " + MENUE_FIELD_TAG + " "
			+ MENUE_TYPE_TAG + ", " + MENUE_FIELD_MENUE1 + " "
			+ MENUE_TYPE_MENUE1 + ", " + MENUE_FIELD_MENUE2 + " "
			+ MENUE_TYPE_MENUE2 + ")";
	
	private static final String CREATE_TABLE_MENUE_CRB = "CREATE TABLE "
			+ TABLE_MENUE_CRB + "(" + MENUE_CRB_FIELD_ID + " " + MENUE_CRB_TYPE_ID
			+ " PRIMARY KEY AUTOINCREMENT" + ", " + MENUE_CRB_FIELD_TAG + " "
			+ MENUE_CRB_TYPE_TAG + ", " + MENUE_CRB_FIELD_MENUE1 + " "
			+ MENUE_CRB_TYPE_MENUE1 + ", " + MENUE_CRB_FIELD_MENUE2 + " "
			+ MENUE_CRB_TYPE_MENUE2 + ")";
	
	private static final String CREATE_TABLE_MENUE_CRP = "CREATE TABLE "
			+ TABLE_MENUE_CRP + "(" + MENUE_CRP_FIELD_ID + " " + MENUE_CRP_TYPE_ID
			+ " PRIMARY KEY AUTOINCREMENT" + ", " + MENUE_CRP_FIELD_TAG + " "
			+ MENUE_CRP_TYPE_TAG + ", " + MENUE_CRP_FIELD_MENUE1 + " "
			+ MENUE_CRP_TYPE_MENUE1 + ", " + MENUE_CRP_FIELD_MENUE2 + " "
			+ MENUE_CRP_TYPE_MENUE2 + ")";

	private static final String CREATE_TABLE_INFORMATIONEN = "CREATE TABLE "
			+ TABLE_INFORMATIONEN + "(" + INFORMATIONEN_FIELD_ID + " "
			+ INFORMATIONEN_TYPE_ID + " PRIMARY KEY AUTOINCREMENT" + ", "
			+ INFORMATIONEN_FIELD_CAMPUS + " " + INFORMATIONEN_TYPE_CAMPUS
			+ ", " + INFORMATIONEN_FIELD_WOCHE + " " + INFORMATIONEN_TYPE_WOCHE
			+ ", " + INFORMATIONEN_FIELD_BEILAGEN + " "
			+ INFORMATIONEN_TYPE_BEILAGEN + ", "
			+ INFORMATIONEN_FIELD_ESSENSAUSGABE + " "
			+ INFORMATIONEN_TYPE_ESSENSAUSGABE + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_TABLE_MENUE);
			db.execSQL("INSERT INTO menue (tag, menue1, menue2) VALUES ('Mo:', 'Gegrillte Hähnchenkeule (4)', 'Eiersalat mit Schnittlauch (2,4,8)')");
			db.execSQL("INSERT INTO menue (tag, menue1, menue2) VALUES ('Di:', 'Schnitzel „Wiener Art“ (Pute oder Schwein) (4)', 'Vegetarischgefüllte Paprikaschote (2,4,8)')");
			db.execSQL("INSERT INTO menue (tag, menue1, menue2) VALUES ('Mi:', 'Pastete gefüllt mit Hühnerfrikassee (2,4,8)', 'Tortellini gefüllt mit Gemüse (2,4,8)')");
			db.execSQL("INSERT INTO menue (tag, menue1, menue2) VALUES ('Do:', 'Putenspieß mit Curry-Ananassoße (2,4,8)', 'Rahmspinat mit Rührei (2,4,8)')");
			db.execSQL("INSERT INTO menue (tag, menue1, menue2) VALUES ('Fr:', 'Pangasiusfilet in Salbeibutter (4)', 'Broccoli-Kartoffel-Gratin (2,4,8)')");
			
			db.execSQL(CREATE_TABLE_MENUE_CRB);
			db.execSQL("INSERT INTO menue_crb (tag, menue1, menue2) VALUES ('Mo:', 'Spaghetti „Bolognese“ (Rind) (1,4,2)', 'Penne mit Spinat und Schafskäsesoße (8,2)')");
			db.execSQL("INSERT INTO menue_crb (tag, menue1, menue2) VALUES ('Di:', 'Cordonbleu (Pute) (8)', 'Vollkornnudeln mit Gemüsesoße (8)')");
			db.execSQL("INSERT INTO menue_crb (tag, menue1, menue2) VALUES ('Mi:', 'Chili con Carne (Rind) (1,4,2)', 'Kartoffel-Karotten-Auflauf (8,2)')");
			db.execSQL("INSERT INTO menue_crb (tag, menue1, menue2) VALUES ('Do:', 'Putenschnitzel „Hawaii“', 'Salatteller mit Kartoffeltasche gefüllt mit Tomaten Mozzarella (8)')");
			db.execSQL("INSERT INTO menue_crb (tag, menue1, menue2) VALUES ('Fr:', 'Salamipizza (Schwein) (8)', 'Gefüllte Zucchini')");
			
			db.execSQL(CREATE_TABLE_MENUE_CRP);
			db.execSQL("INSERT INTO menue_crp (tag, menue1, menue2) VALUES ('Mo:', 'Zigeunerschnitzel mit Pommes Fritte', 'Blumenkohlkäse-Medaillons mit Püree')");
			db.execSQL("INSERT INTO menue_crp (tag, menue1, menue2) VALUES ('Di:', 'Schweinegeschnetzeltes „Indisches Curry“ mit Reis', 'Käse Spätzle')");
			db.execSQL("INSERT INTO menue_crp (tag, menue1, menue2) VALUES ('Mi:', 'Schwenkbraten mit Nudelsalat', 'Broccolinuggets mit Pommes Fritte')");
			db.execSQL("INSERT INTO menue_crp (tag, menue1, menue2) VALUES ('Do:', 'Wirsingrouladen mit Püree', 'Tomaten-Mozzarella-Gratin')");
			db.execSQL("INSERT INTO menue_crp (tag, menue1, menue2) VALUES ('Fr:', 'Bandnudeln mit Lachssahnesoße', 'Suppe Germknödel mit Vanillesoße')");

			db.execSQL(CREATE_TABLE_INFORMATIONEN);
			db.execSQL("INSERT INTO informationen (campus, woche, beilagen, essensausgabe) VALUES ('Mensa der HTW Saarbrücken Campus Alt-Saarbrücken', 'Speiseplan vom 24.06.2013 - 28.06.2013', 'Wir reichen zu jedem Hauptgericht die dazu passenden Beilagen.', 'Essensausgabe von 11:30 bis 14:00 Uhr.')");
			db.execSQL("INSERT INTO informationen (campus, woche, beilagen, essensausgabe) VALUES ('Mensa der HTW Saarbrücken Campus Rotenbühl', 'Speiseplan vom 24.06.2013 - 28.06.2013', 'Wir reichen zu jedem Hauptgericht die dazu passenden Beilagen.', 'Essensausgabe von 11:30 bis 14:00 Uhr.')");
			db.execSQL("INSERT INTO informationen (campus, woche, beilagen, essensausgabe) VALUES ('Mensa der HTW Saarbrücken Campus Rastpfuhl', 'Speiseplan vom 24.06.2013 - 28.06.2013', 'Wir reichen zu jedem Hauptgericht die dazu passenden Beilagen.', 'Essensausgabe von 11:30 bis 13:45 Uhr.')");
		} catch (SQLException ex) {
			System.out.println("DatabaseHelper (error creating tables): " + ex);
		}
	}

	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
	}

	public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) {
	}
}