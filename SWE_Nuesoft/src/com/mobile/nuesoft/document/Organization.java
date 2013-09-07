package com.mobile.nuesoft.document;

import com.mobile.nuesoft.patient.Address;
import com.mobile.nuesoft.patient.Telephone;

public class Organization {

	private final String DISPLAY_NAME;
	private final Address ADDRESS;
	private final Telephone TELEPHONE;
	
	public String ID = "";
	
	public Organization(final String DISPLAY_NAME, final Address ADDRESS, final Telephone TELEPHONE) {
		this.DISPLAY_NAME = DISPLAY_NAME;
		this.ADDRESS = ADDRESS;
		this.TELEPHONE = TELEPHONE;
	}

	public String getDISPLAY_NAME() {
		return DISPLAY_NAME;
	}

	public Address getADDRESS() {
		return ADDRESS;
	}

	public Telephone getTELEPHONE() {
		return TELEPHONE;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
