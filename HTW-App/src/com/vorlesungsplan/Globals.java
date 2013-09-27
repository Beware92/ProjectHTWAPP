package com.vorlesungsplan;

import java.io.File;

import android.os.Environment;

public class Globals {
	
	final static String vl_Path = Environment.getExternalStorageDirectory().toString()+ "/htw_app/vl_plaene/";
	

	final static String INGWIPIKI_URL = "http://www-crypto.htw-saarland.de/weber/stundenplan/2013_ss.html";
	final static String PKFilename = INGWIPIKI_URL.substring(INGWIPIKI_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String INGWIMB_URL = "http://www.htw-saarland.de/Members/m-sek/vorlesungs-und-prufungsplane/vorlesungsplan/stundenplan_ws1314_07092013.pdf";
	final static String MBFilename = INGWIMB_URL.substring(INGWIMB_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String INGWIBT_URL ="http://www.htw-saarland.de/Members/michael.moeller/stundenplan/stundenplan-sommersemester-2013";
	final static String BTFilename = INGWIBT_URL.substring(INGWIBT_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String INGWIMS_URL ="http://www.htw-saarland.de/ingwi/studium/studienbereich-mechatronik-sensortechnik/mst_vorlesungen/vorlesungsplan-ss-2013";
	final static String MSFilename = INGWIMS_URL.substring(INGWIMS_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String INGWIEE_URL ="http://www.htw-saarland.de/ingwi/studium/studienbereich-energiesystemtechnik/dokumente/stundenplan-ee-stand-23-04-2013-pdf";
	final static String EEFilename = INGWIEE_URL.substring(INGWIEE_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String INGWIETB_URL = "http://www.htw-saarland.de/Members/e-sek/vorlesungsplan/bachelor-studiengang-elektrotechnik-sommersemester-2013";
	final static String ETBFilename = INGWIETB_URL.substring(INGWIETB_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String INGWIETM_URL = "http://www.htw-saarland.de/Members/e-sek/vorlesungsplan/master-studiengang-elektrotechnik-sommersemester-2013";
	final static String ETMFilename = INGWIETM_URL.substring(INGWIETM_URL.lastIndexOf("/")).replaceFirst("/", "");
		
	final static String ARCHB_URL = "http://www.htw-saarland.de/aub/Studium/schule-fuer-architektur-saar/aktuell/ba_ss2013_kompakt2.pdf";
	final static String ARCHBFilename = ARCHB_URL.substring(ARCHB_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String ARCHM_URL = "http://www.htw-saarland.de/aub/Studium/schule-fuer-architektur-saar/aktuell/ma_ss2013kompakt_130415.pdf";
	final static String ARCHMFilename = ARCHM_URL.substring(ARCHM_URL.lastIndexOf("/")).replaceFirst("/", "");
		
	final static String BAUB_URL = "http://www.htw-saarland.de/aub/Studium/bi/bib/pdf-dateien/aktueller-stundenplan-ba-ss13";
	final static String BAUBFilename = BAUB_URL.substring(BAUB_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String BAUM_URL = "http://www.htw-saarland.de/aub/Studium/bi/bib/pdf-dateien/master-stuplan-ss13";
	final static String BAUMFilename = BAUM_URL.substring(BAUM_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	final static String SOZIALMEPG_URL="http://www.htw-saarland.de/sowi/Studium/studienangebot/BAME/vp-bame-ss-2013-2";
	final static String SOZIALMEPGFilename = SOZIALMEPG_URL.substring(SOZIALMEPG_URL.lastIndexOf("/")).replaceFirst("/", "");
	
	public static File SOZIALMEPG = new File(vl_Path + SOZIALMEPGFilename);
	public static File BAUB = new File(vl_Path + BAUBFilename);
	public static File BAUM = new File(vl_Path + BAUMFilename);
	public static File ARCHB = new File(vl_Path + ARCHBFilename);
	public static File ARCHM = new File(vl_Path + ARCHMFilename);
	public static File PK = new File(vl_Path + PKFilename);
	public static File MB = new File(vl_Path + MBFilename);
	public static File BT = new File(vl_Path + BTFilename);
	public static File MS = new File(vl_Path + MSFilename);
	public static File EE = new File(vl_Path + EEFilename);	
	public static File ETB = new File(vl_Path + ETBFilename);
	public static File ETM = new File(vl_Path + ETMFilename);
	
}
