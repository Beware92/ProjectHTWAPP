package com.example.htw_app;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;


public class RSSHandler extends DefaultHandler {

	/**
	 * Konstante für das Entry - Element innerhalb der XML
	 */
	final private String ENTRY = new String("item");
	/**
	 * Konstante für das Title - Element innerhalb der XML
	 */
	final private String TITLE = new String("title");
	/**
	 * Konstante für das Link - Element innerhalb der XML
	 */
	final private String LINK = new String("link");
	/**
	 * Konstante für das Attribute des Link - Elemente innerhalb der XML
	 * 
	 */
	final private String LINK_ATTR_HREF = new String("href");

	/**
	 * Wahrheitswert der aussagt ob das Entry-Element gerade geparst wird
	 */
	private boolean entryBoolean;
	/**
	 * Wahrheitswert der aussagt ob das Title-Element gerade geparst wird
	 */
	private boolean titleBoolean;
	/**
	 * Text zwischen dem Element Titel
	 */
	private String contentTitle;
	/**
	 * Speichert den Inhalt des RSS Feeds
	 */
	private RSSContent myContent;
	
	private boolean addeLink = false;

	/**
	 * Konstruktor
	 * 
	 * @param content
	 *            - Speicher für den Inhalt des RSS Feeds
	 */
	public RSSHandler(RSSContent content) {
		myContent = content;
	}

	// wird aufgerufen wenn der Parser den Anfag des Dokumentes erreicht
	@Override
	public void startDocument() throws SAXException {

		entryBoolean = false;
		titleBoolean = false;
		contentTitle = "";

	}

	// wird aufgerufen wenn der Parser das Ende des Dokumentes erreicht
	@Override
	public void endDocument() throws SAXException {

	}

	// wird aufgerufen wenn der Parser das startTag eines Elementes erreicht
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		System.out.println("Element start= " + qName);
		
		if (localName.equalsIgnoreCase(this.ENTRY)) {
			this.entryBoolean = true;
		} else if (localName.equalsIgnoreCase(this.TITLE)) {
			this.titleBoolean = true;
		} else if (localName.equalsIgnoreCase(this.LINK)) {

			if (entryBoolean) {
				// nur Links innerhalb des entry Elements werden gespeichert
				addeLink = true;
			}
		}
	}

	// wird aufgerufen wenn das endTag eines Elementes erreicht
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		System.out.println("Element ende= " + qName);

		if (localName.equalsIgnoreCase(this.ENTRY)) {
			this.entryBoolean = false;
		} else if (localName.equalsIgnoreCase(this.TITLE)) {
			this.titleBoolean = false;
			if (this.entryBoolean) {

				Log.i(RSSHandler.class.getSimpleName(),this.contentTitle);
				// Speichern des Titels
				myContent.setTitel(this.contentTitle);
				// Zwischenspeicher wird "gelöscht"
				this.contentTitle = "";
			}
		}

	}

	// wird aufgerufen für den Content zwischen startTag und endTag
	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {

		String textBetween = new String(ch, start, length);
		
		if(addeLink){
			myContent.setUrl(textBetween);
			addeLink = false;
		}

		if (this.entryBoolean) {
			// nur Title innerhalb des Entry Element werden ausgelesen
			if (this.titleBoolean) {				
				this.contentTitle = this.contentTitle + textBetween;
				System.out.println("Content " + this.contentTitle);
			}

		}
	}

}