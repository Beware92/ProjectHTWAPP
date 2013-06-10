package com.example.htw_app;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class RSSNewsAnzeigen extends Activity {

private final String TAG = RSSNewsAnzeigen.class.getSimpleName();

/**
* Konstante für die URL des RSS Links
*/
private final String URL_RSS = "http://www.htw-saarland.de/news/RSS";

/**
* Liste in der die vorhanden Feeds angezeigt werden
*/
private ListView liste;

/**
* enthält die Daten des RSS Feeds die angezeigt werden sollen
*/
private static RSSContent myContent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssmain);
        
        //verbindet die ListView in der XML mit dem Java Code
        liste =(ListView)findViewById(R.id.rssFeedListe);
        
        myContent = new RSSContent();
        
      //Start des Hintergrundprozess um den RSS Feed zu parsen
        new ReadRssFeedTask(this).execute();

//wird ausgeführt wenn auf ein Item der Liste geklickt wird
liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {

public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

//Aufruf der Activity Anzeigen
Intent intent = new Intent(RSSNewsAnzeigen.this,RSSFeedDetails.class);
//Adresse des Inhalts des ausgewählten Feeds
intent.putExtra("URL", myContent.getUrl(position));
startActivity(intent);
}
});
    }
    
    /**
* Parst im Hintergrund den Inhalt des RSS Feeds
*/
    private class ReadRssFeedTask extends AsyncTask<Void,Void,Void> {
    
     /**
* Anzeige das der RSS Feed vom Server geladen wird
*/
     private ProgressDialog dialog;
    
     /**
* der HttpClient dient dazu die Anfrage an den Server zu stellen
*/
     private HttpClient client;
    
     /**
* Art der Anfrage
*/
     private HttpGet httpGet;
    
     /**
* Antwort des Severs
*/
private HttpResponse response;



     //Konstruktor
     public ReadRssFeedTask(Activity activity) {
    
    
            client = new DefaultHttpClient();
        
            
     httpGet = new HttpGet(URL_RSS);
    
     response = null;
         
     dialog = new ProgressDialog(activity);
    
    
     }
    
     //wird vor onInBackground ausgeführt
     protected void onPreExecute() {
    
     //einrichten des Dialogs
     this.dialog.setTitle("Bitte warten!");
     this.dialog.setMessage("Der RSS Feed wird vom Server geladen.");
            this.dialog.show();
         }
    
     //Hintergrundthread
     @Override
     protected Void doInBackground(Void... tmp){
    
    
     try {
    
     //ausführen der Anfrage
     response = client.execute(httpGet);
    
     } catch (ClientProtocolException e) {
     Log.e(TAG,"Falsches Protokol " + e.getMessage());
     } catch (IOException e) {
     Log.e(TAG,"URL ist falsch, URL: "+URL_RSS + " " + e.getMessage());
     }
    
     //Kontrolle ob bei ausführen der Anfrage ein Fehler aufgetreten ist
     if(response != null){
    
     //Status der Abfrage
     StatusLine statusLine = response.getStatusLine();
    
    
     //Statuscode 200 => Die Anfrage wurde erfolgreich bearbeitet und das Ergebnis der Anfrage wird in der Antwort übertragen
     if ( statusLine.getStatusCode() == 200) {
    
     //Ergebnis der Anfrage
     HttpEntity entity = response.getEntity();
    
     try {
    
    
     SAXParserFactory spf = SAXParserFactory.newInstance();
    
     SAXParser sp = null;
    
     try {
     //neue Instanz des Parser
     sp = spf.newSAXParser();
    
     } catch (ParserConfigurationException e) {
     Log.e(TAG, "Fehler: " + e.getMessage());
     } catch (SAXException e) {
     Log.e(TAG, "Fehler im Handler: " + e.getMessage());
     }
    
     //entscheidet welche Daten aus dem RSS Feed gespeichert werden
     RSSHandler myHandler = new RSSHandler(myContent);
    
     try {
     //Parser bekommt den RSS Feed und den Handler
     sp.parse(entity.getContent(), myHandler);
    
    
     } catch (SAXException e) {
     Log.e(TAG,"Fehler beim Parsen. Fehler: " + e.getMessage());
    
     } catch (IOException e) {
    
    
     Log.e(TAG,e.getMessage());
     }
    
    
    
     } catch (IllegalStateException e) {
     e.printStackTrace();
     }
     }else{
     Log.i(TAG,"Der Server antwortet mit anderen Statuscode als 200. Statuscode: "+statusLine.getStatusCode());
    
     }
     }else{
     Log.i(TAG,"Keine Internetverbindung.");
    
     }
    
    
    
     return null;
           
        }
    
     @Override
         protected void onPostExecute(Void result){
    
     //falls der Dialog angezeigt wird, wird er beendet
     if (dialog.isShowing()) {
                 dialog.dismiss();
             }
    
     //liste mit Daten füllen
     liste.setAdapter(new ArrayAdapter<String>(RSSNewsAnzeigen.this, android.R.layout.simple_list_item_1,myContent.getTitel()));
    
     }
    }
}