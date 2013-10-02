package com.vorlesungsplan;

/**
 * Klasse zur Verwaltung von Studiengaengen
 * @author marc.meese
 *
 */
public class Studiengang{
    private int id;
    private String name;
 
    
    public Studiengang(){
        super();
    }
    
    public Studiengang(int id, String name) {
        super();
        this.id = id;
        this.name = name;
        
    }

  
	@Override
    public String toString() {
        return this.name;
    }
}