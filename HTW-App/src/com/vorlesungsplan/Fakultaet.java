package com.vorlesungsplan;

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
        return this.id + ". " + this.name;
    }
}