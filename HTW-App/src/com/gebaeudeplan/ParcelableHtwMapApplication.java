package com.gebaeudeplan;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableHtwMapApplication implements Parcelable{
	private HtwMapApplication mapObject;
	
	public HtwMapApplication getHtwMapApplication() {
		return mapObject;
	}
	
	public ParcelableHtwMapApplication(HtwMapApplication mapObject) {
		super();
		this.mapObject = mapObject;
	}
	
	public ParcelableHtwMapApplication(Parcel in) {
		mapObject = new HtwMapApplication();
		//mapObject.setMapFloorE(in.readParcelable(loader))
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
}
