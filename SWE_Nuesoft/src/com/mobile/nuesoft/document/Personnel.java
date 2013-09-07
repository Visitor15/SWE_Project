package com.mobile.nuesoft.document;

import com.mobile.nuesoft.patient.Address;

public class Personnel {

	public static final String TAG = "Personnel";

	private final String GIVEN_NAME;
	private final String FAMILY_NAME;
	private final String SUFFIX;

	private String PERSONNEL_ID = "";
	private String PERSONNEL_TYPE = "";
	private Address PERSONNEL_ADDRESS = null;

	public Personnel(final String firstName, final String lastName, final String suffix) {
		this.GIVEN_NAME = firstName;
		this.FAMILY_NAME = lastName;
		this.SUFFIX = suffix;
	}

	public String getGIVEN_NAME() {
		return GIVEN_NAME;
	}

	public String getFAMILY_NAME() {
		return FAMILY_NAME;
	}

	public String getSUFFIX() {
		return SUFFIX;
	}

	public String getPERSONNEL_TYPE() {
		return PERSONNEL_TYPE;
	}

	public void setPERSONNEL_TYPE(String PERSONNEL_TYPE) {
		this.PERSONNEL_TYPE = PERSONNEL_TYPE;
	}

	public Address getPERSONNEL_ADDRESS() {
		return PERSONNEL_ADDRESS;
	}

	public void setPERSONNEL_ADDRESS(Address pERSONNEL_ADDRESS) {
		PERSONNEL_ADDRESS = pERSONNEL_ADDRESS;
	}
	
	public void setPERSONNEL_ID(final String ID) {
		this.PERSONNEL_ID = ID;
	}
	
	public String getPERSONNEL_ID() {
		return this.PERSONNEL_ID;
	}

	@Override
	public String toString() {
		String val = "";
		
		if(this.getPERSONNEL_TYPE().trim().length() > 0) {
			val += this.getPERSONNEL_TYPE() + ",\n";
		}
		
		val += this.getFAMILY_NAME() + ", " + this.getGIVEN_NAME() + " " + this.getSUFFIX();
		
		if(this.getPERSONNEL_ADDRESS() != null) {
			val += "\n" + this.getPERSONNEL_ADDRESS().toString();
		}
		
		return val;
	}
}
