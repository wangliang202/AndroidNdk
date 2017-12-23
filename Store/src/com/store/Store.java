package com.store;

public class Store {
	
	static {
//		System.loadLibrary("store");
	}
	
	public native String getString(String pKey);
	public native void setString(String pKey, String pValue);
	public native Integer getInteger(String pKey);
	public native void setInteger(String pKey, Integer pValue);
}
