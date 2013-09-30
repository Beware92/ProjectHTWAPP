package com.vorlesungsplan;

import com.example.htw_app.R;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;
import android.os.Bundle;

/**
 * Klasse zur Verwaltung von PDF Dateien
 * @author marc.meese
 *
 */
public class PDFMethode extends PdfViewerActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	}

	public int getPreviousPageImageResource() {
	    return R.drawable.left_arrow;
	}

	public int getNextPageImageResource() {
	    return R.drawable.right_arrow;
	}

	public int getZoomInImageResource() {
	    return R.drawable.zoom_in;
	}

	public int getZoomOutImageResource() {
	    return R.drawable.zoom_out;
	}

	public int getPdfPasswordLayoutResource() {
	    return R.layout.pdf_file_password;
	}

	public int getPdfPageNumberResource() {
	    return R.layout.dialog_pagenumber;
	}

	public int getPdfPasswordEditField() {
	    return R.id.etPassword;
	}

	public int getPdfPasswordOkButton() {
	    return R.id.btOK;
	}

	public int getPdfPasswordExitButton() {
	    return R.id.btExit;
	}

	public int getPdfPageNumberEditField() {
	    return R.id.pagenum_edit;
	}
}
