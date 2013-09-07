package com.mobile.nuesoft.document;

import com.mobile.nuesoft.patient.Address;
import com.mobile.nuesoft.patient.Telephone;

public class Participant {

	private final String TYPE;
	private final String ROLE;
	private final Address ADDRESS;
	private final Telephone TELEPHONE;

	public Participant(final String TYPE, final String ROLE, final Address ADDRESS, final Telephone TELEPHONE) {
		this.TYPE = TYPE;
		this.ROLE = ROLE;
		this.ADDRESS = ADDRESS;
		this.TELEPHONE = TELEPHONE;
	}

	public String getTYPE() {
		return TYPE;
	}

	public String getROLE() {
		return ROLE;
	}

	public Address getADDRESS() {
		return ADDRESS;
	}

	public Telephone getTELEPHONE() {
		return TELEPHONE;
	}
}
