package com.RSS;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Verwaltung von RSS Inhalten
 * @author marc.meese
 *
 */
public class RSSContent {

	/**
	 * Titel der Nachricht
	 */
	private List<String> titel;
	/**
	 * URL zum Inhalt der Nachricht
	 */
	private List<String> url;

	/**
	 * Konstruktor
	 */
	public RSSContent() {

		titel = new ArrayList<String>();
		url = new ArrayList<String>();

	}

	/**
	 * Alle Nachrichten aus dem RSS Feed
	 * 
	 * @return Liste mit Nachrichten
	 */
	public List<String> getTitel() {
		return this.titel;
	}

	/**
	 * fuegt einen Nachricht aus dem RSS Feed der Klasse hinzu
	 * 
	 * @param newsTitel
	 *            - Nachricht aus RSS Feed
	 */
	public void setTitel(String newsTitel) {
		System.out.println("Title geaddet: " + newsTitel);
		this.titel.add(newsTitel);
	}

	/**
	 * Url zum Inhalt der Nachricht
	 * 
	 * @param pos
	 *            - Position der Nachricht in der Liste
	 * @return - url
	 */
	public String getUrl(int pos) {

		if (pos >= 0) {
			return this.url.get(pos);
		} else {
			return "";
		}
	}

	/**
	 * Neue Url aus dem RSS Feed hinzufügen
	 * 
	 * @param newsUrl
	 *            - neue Url
	 */
	public void setUrl(String newsUrl) {
		System.out.println("URL geaddet: " + newsUrl);
		this.url.add(newsUrl);
	}

}