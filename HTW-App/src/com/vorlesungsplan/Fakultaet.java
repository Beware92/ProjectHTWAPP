package com.vorlesungsplan;

/**
 * Klasse zur Verwaltung von Fakultaeten
 * @author marc.meese
 *
 */
public class Fakultaet{
    private int id;
    private String name;
 
    
    public Fakultaet(){
        super();
    }
    
    public Fakultaet(int id, String name) {
        super();
        this.id = id;
        this.name = name;
        
    }

  
	@Override
    public String toString() {
        return this.name;
    }
}