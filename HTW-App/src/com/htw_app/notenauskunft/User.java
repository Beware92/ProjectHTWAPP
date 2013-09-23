package com.htw_app.notenauskunft;

public class User {
	private int id;
	private String mtknr, passwort;

	public User(int id, String mtknr, String passwort) {
		this.id = id;
		this.mtknr = mtknr;
		this.passwort = passwort;
	}

	public int getId() {
		return id;
	}

	public String getMtknr() {
		return mtknr;
	}

	public String getPasswort() {
		return passwort;
	}
}