package com.htw_app.notenauskunft;

/**
 * Klasse Detaildaten zum erstellen von Detaildaten-Objekten
 * 
 * @author Andreas G�rres
 */
public class Detaildaten {
	private String id2, mtknr2, fnr2, status, lfdversuch, punkte, pnote,
			pdatum, grund, semester2, bemerkung, besprechung, decnote;

	/**
	 * Standartkonstruktor
	 */
	public Detaildaten(String id2, String mtknr2, String fnr2, String status,
			String lfdversuch, String punkte, String pnote, String pdatum,
			String grund, String semester2, String bemerkung,
			String besprechung, String decnote) {

		this.id2 = id2;
		this.mtknr2 = mtknr2;
		this.fnr2 = fnr2;
		this.status = status;
		this.lfdversuch = lfdversuch;
		this.punkte = punkte;
		this.pnote = pnote;
		this.pdatum = pdatum;
		this.grund = grund;
		this.semester2 = semester2;
		this.bemerkung = bemerkung;
		this.besprechung = besprechung;
		this.decnote = decnote;
	}

	public String getId2() {
		return id2;
	}

	public String getMtknr2() {
		return mtknr2;
	}

	public String getFnr2() {
		return fnr2;
	}

	public String getStatus() {
		return status;
	}

	public String getLfdversuch() {
		return lfdversuch;
	}

	public String getPunkte() {
		return punkte;
	}

	public String getPnote() {
		return pnote;
	}

	public String getPdatum() {
		return pdatum;
	}

	public String getGrund() {
		return grund;
	}

	public String getSemester2() {
		return semester2;
	}

	public String getBemerkung() {
		return bemerkung;
	}

	public String getBesprechung() {
		return besprechung;
	}

	public String getDecnote() {
		return decnote;
	}

	public String toString() {
		return "Status: " + status + "\n" + "Versuch: " + lfdversuch + "\n"
				+ "Punkte: " + punkte + "\n" + "Note: " + pnote + "\n"
				+ "Optionale Note: " + decnote + "\n" + "Semester: "
				+ semester2 + "\n" + "Datum: " + pdatum + "\n" + "Bemerkung: "
				+ bemerkung + "\n" + "Besprechung: " + besprechung;
	}
}