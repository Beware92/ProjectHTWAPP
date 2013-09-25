package com.vorlesungsplan;

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
        return this.id + ". " + this.name;
    }
}
