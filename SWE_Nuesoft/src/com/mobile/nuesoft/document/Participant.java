package com.mobile.nuesoft.document;


public class Participant {

	private final String TYPE;
	private final String ROLE;
	private final Personnel PERSON;

	public Participant(final String TYPE, final String ROLE, final Personnel PERSON) {
		this.TYPE = TYPE;
		this.ROLE = ROLE;
		this.PERSON = PERSON;
	}

	public String getTYPE() {
		return TYPE;
	}

	public String getROLE() {
		return ROLE;
	}

	public Personnel getPERSON() {
		return PERSON;
	}
}
