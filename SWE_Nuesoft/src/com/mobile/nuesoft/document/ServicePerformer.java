package com.mobile.nuesoft.document;

import com.mobile.nuesoft.patient.Address;
import com.mobile.nuesoft.patient.Telephone;

public class ServicePerformer {

	private final String TAG = "ServicePerformer";

	private final String ID;
	private final String CODE;
	private final String CODE_SYSTEM;
	private final String CODE_SYSTEM_NAME;
	private final String PERFORMER_TYPE;
	private final Personnel PERSON;
	private final Address ADDRESS;
	private final Telephone TELEPHONE;

	public ServicePerformer(String ID, String CODE, String CODE_SYSTEM, String CODE_SYSTEM_NAME,
	        final String PERFORMER_TYPE, Personnel PERSON, Address ADDRESS, Telephone TELEPHONE) {
		this.ID = ID;
		this.CODE = CODE;
		this.CODE_SYSTEM = CODE_SYSTEM;
		this.CODE_SYSTEM_NAME = CODE_SYSTEM_NAME;
		this.PERFORMER_TYPE = PERFORMER_TYPE;
		this.PERSON = PERSON;
		this.ADDRESS = ADDRESS;
		this.TELEPHONE = TELEPHONE;
	}

	public Personnel getPERSON() {
		return PERSON;
	}

	public String getID() {
		return ID;
	}

	public String getCODE() {
		return CODE;
	}

	public String getCODE_SYSTEM() {
		return CODE_SYSTEM;
	}

	public String getCODE_SYSTEM_NAME() {
		return CODE_SYSTEM_NAME;
	}

	public String getPERFORMER_TYPE() {
		return PERFORMER_TYPE;
	}

	public Address getADDRESS() {
		return ADDRESS;
	}

	public Telephone getTELEPHONE() {
		return TELEPHONE;
	}

	@Override
	public String toString() {
		String val = "Service performed by:\n";
		val += this.getPERSON().toString();

		return val;
	}
}
