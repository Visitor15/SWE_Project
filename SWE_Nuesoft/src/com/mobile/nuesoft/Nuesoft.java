package com.mobile.nuesoft;

import android.app.Application;

public class Nuesoft  extends Application  {

	private static Nuesoft singleton;

	public static final Nuesoft getReference() {
		return Nuesoft.singleton;
	}

	public Nuesoft() {
		super();
	}
	
	@Override
	  public void onCreate() {
	    Nuesoft.singleton = this;
	    super.onCreate();
	}
}
