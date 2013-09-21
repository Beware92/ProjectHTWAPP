package com.gebaeudeplan;

import android.app.Application;
import android.graphics.drawable.Drawable;

public class HtwMapApplication extends Application
{
	private static HtwMapApplication instance;
	private Drawable mapFloorE;
	private Drawable mapFloorEnoDesc;
	private Drawable mapFloor1;
	private Drawable mapFloor1noDesc;
	private Drawable mapFloor2;
	private Drawable mapFloor2noDesc;
	private Drawable mapFloor3;
	private Drawable mapFloor3noDesc;
	  
	
	
	public static HtwMapApplication getInstance() {
		return instance;
	}
	
	
	public void onCreate() {
		super.onCreate();
		instance = this;
	}


	public Drawable getMapFloorE() {
		return mapFloorE;
	}


	public void setMapFloorE(Drawable mapFloorE) {
		this.mapFloorE = mapFloorE;
	}


	public Drawable getMapFloorEnoDesc() {
		return mapFloorEnoDesc;
	}


	public void setMapFloorEnoDesc(Drawable mapFloorEnoDesc) {
		this.mapFloorEnoDesc = mapFloorEnoDesc;
	}


	public Drawable getMapFloor1() {
		return mapFloor1;
	}


	public void setMapFloor1(Drawable mapFloor1) {
		this.mapFloor1 = mapFloor1;
	}


	public Drawable getMapFloor1noDesc() {
		return mapFloor1noDesc;
	}


	public void setMapFloor1noDesc(Drawable mapFloor1noDesc) {
		this.mapFloor1noDesc = mapFloor1noDesc;
	}


	public Drawable getMapFloor2() {
		return mapFloor2;
	}


	public void setMapFloor2(Drawable mapFloor2) {
		this.mapFloor2 = mapFloor2;
	}


	public Drawable getMapFloor2noDesc() {
		return mapFloor2noDesc;
	}


	public void setMapFloor2noDesc(Drawable mapFloor2noDesc) {
		this.mapFloor2noDesc = mapFloor2noDesc;
	}


	public Drawable getMapFloor3() {
		return mapFloor3;
	}


	public void setMapFloor3(Drawable mapFloor3) {
		this.mapFloor3 = mapFloor3;
	}


	public Drawable getMapFloor3noDesc() {
		return mapFloor3noDesc;
	}


	public void setMapFloor3noDesc(Drawable mapFloor3noDesc) {
		this.mapFloor3noDesc = mapFloor3noDesc;
	}

}