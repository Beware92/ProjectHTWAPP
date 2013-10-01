package com.htw_app.notenauskunft;

/**
 * Klasse Kopfdaten zum erstellen von Kopfdaten-Objekten
 * 
 * @author Andreas Görres
 */
public class Kopfdaten {
	private String id, mtknr, fnr, abschnitt, stg, fach, versuch, pflicht,
			wichtung, semester, pstatus, cpcredit, reihenfolge, abmeldedatum,
			art, modulnr;

	/**
	 * Standartkonstruktor
	 */
	public Kopfdaten(String id, String mtknr, String fnr, String abschnitt,
			String stg, String fach, String versuch, String pflicht,
			String wichtung, String semester, String pstatus, String cpcredit,
			String reihenfolge, String abmeldedatum, String art, String modulnr) {

		this.id = id;
		this.mtknr = mtknr;
		this.fnr = fnr;
		this.abschnitt = abschnitt;
		this.stg = stg;
		this.fach = fach;
		this.versuch = versuch;
		this.pflicht = pflicht;
		this.wichtung = wichtung;
		this.semester = semester;
		this.pstatus = pstatus;
		this.cpcredit = cpcredit;
		this.reihenfolge = reihenfolge;
		this.abmeldedatum = abmeldedatum;
		this.art = art;
		this.modulnr = modulnr;
	}

	public String getId() {
		return id;
	}

	public String getMtknr() {
		return mtknr;
	}

	public String getFnr() {
		return fnr;
	}

	public String getAbschnitt() {
		return abschnitt;
	}

	public String getStg() {
		return stg;
	}

	public String getFach() {
		return fach;
	}

	public String getVersuch() {
		return versuch;
	}

	public String getPflicht() {
		return pflicht;
	}

	public String getWichtung() {
		return wichtung;
	}

	public String getSemester() {
		return semester;
	}

	public String getPstatus() {
		return pstatus;
	}

	public String getCpcredit() {
		return cpcredit;
	}

	public String getReihenfolge() {
		return reihenfolge;
	}

	public String getAbmeldedatum() {
		return abmeldedatum;
	}

	public String getArt() {
		return art;
	}

	public String getModulnr() {
		return modulnr;
	}
}