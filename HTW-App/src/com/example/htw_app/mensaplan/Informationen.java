package com.example.htw_app.mensaplan;

public class Informationen {
	private int id;
	private String campus, woche, beilagen, essensausgabe;

	public Informationen(int id, String campus, String woche, String beilagen,
			String essensausgabe) {
		this.id = id;
		this.campus = campus;
		this.woche = woche;
		this.beilagen = beilagen;
		this.essensausgabe = essensausgabe;
	}

	public int getId() {
		return id;
	}

	public String getCampus() {
		return campus;
	}

	public String getWoche() {
		return woche;
	}

	public String getBeilagen() {
		return beilagen;
	}

	public String getEssensausgabe() {
		return essensausgabe;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public void setWoche(String woche) {
		this.woche = woche;
	}

	public void setBeilagen(String beilagen) {
		this.beilagen = beilagen;
	}

	public void setEssensausgabe(String essensausgabe) {
		this.essensausgabe = essensausgabe;
	}
}