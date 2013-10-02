package com.vorlesungsplan;

/**
 * Klasse zur Verwaltung von Abschluessen
 * @author marc.meese
 *
 */
public class Abschluss {
    private int id;
    private String name;
 
 
    public Abschluss(){
        super();
    }
    

    public Abschluss(int id, String name) {
        super();
        this.id = id;
        this.name = name;
        
    }


	@Override
    public String toString() {
        return this.name;
    }
}
