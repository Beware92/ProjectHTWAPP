package com.gebaeudeplan;

/**
 * Class to define an object for searchresults, needed for the SearchResultAdapter
 * 
 * @author Thomas Quitter
 *
 */
public class SearchResultElement {
	private String name;
	private String prename;
	private String room;
	private String title;
	private String mail;
	private String tel;
	private int id;
	
	
	public SearchResultElement(String name, String prename, String room, String title, String mail, String tel, int id) {
		this.name = name;
		this.prename = prename;
		this.room = room;
		this.title = title;
		this.mail = mail;
		this.id = id;
		this.tel = tel;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrename() {
		return prename;
	}
	public void setPrename(String prename) {
		this.prename = prename;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String toString() {
		return prename + " " + name + " " + room + " " + mail + " " + tel;
	}
	
}
