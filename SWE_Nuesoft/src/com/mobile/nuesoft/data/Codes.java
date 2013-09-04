package com.mobile.nuesoft.data;

import java.util.HashMap;

public class Codes {
	
	private static Codes mInstance;
	
	public static final String ALLERGY = "allergy";

	public static HashMap<String, String> codeMap = new HashMap<String, String>();
	
	private Codes() {
		init();
	}
	
	public static Codes getInstance() {
		if(mInstance == null) {
			mInstance = new Codes();
		}
		
		return mInstance;
	}
	
	private void init() {
		codeMap.put(ALLERGY, "48765-2");
	}
}
