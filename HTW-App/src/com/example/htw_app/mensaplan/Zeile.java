package com.example.htw_app.mensaplan;

/* Klasse Todo um Todo Objekte anzulegen */
public class Zeile {
	// Attribute des Todo Objekts
	private int id;
	private String tag, menue1, menue2;

	// Konstruktor zum anlegen der Todo Objekte
	public Zeile(int id, String tag, String menue1, String menue2) {
		this.id = id;
		this.tag = tag;
		this.menue1 = menue1;
		this.menue2 = menue2;
	}

	/* Getter Methoden der Attribute */
	public int getId() {
		return id;
	}
	
	public String getTag() {
		return tag;
	}

	public String getMenue1() {
		return menue1;
	}
	
	public String getMenue2() {
		return menue2;
	}

	/* Setter Methoden der Attribute */	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setMenue1(String menue1) {
		this.menue1 = menue1;
	}

	public void setMenue2(String menue2) {
		this.menue2 = menue2;
	}
}

