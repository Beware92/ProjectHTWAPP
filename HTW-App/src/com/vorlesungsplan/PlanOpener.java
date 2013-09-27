package com.vorlesungsplan;

import java.io.File;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PlanOpener {	

	final public static String FILE_NOT_FOUND ="Datei offline";

	public void openHTML(String html, Context cont){
		Intent myIntent = new Intent(cont, HTMLVIEW.class);
		myIntent.putExtra("url", html);
		cont.startActivity(myIntent);
	}

	public void openPDF(File pdf, Context cont){
		if(pdf.exists()){
			Intent myIntent = new Intent(cont, PDFMethode.class);
			myIntent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, pdf.getAbsolutePath());						
			cont.startActivity(myIntent);
		}
		else{
			Toast toast = Toast.makeText(cont, FILE_NOT_FOUND, Toast.LENGTH_SHORT);
			toast.show();
		}
	
	}
}
