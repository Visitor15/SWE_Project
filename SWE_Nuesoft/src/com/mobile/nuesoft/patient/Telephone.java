package com.mobile.nuesoft.patient;

public class Telephone {
	private String AREA_CODE;
	private String FIRST_PART;
	private String SECOND_PART;

	public Telephone() {
		AREA_CODE = "000";
		FIRST_PART = "000";
		SECOND_PART = "0000";
	}

	public Telephone(final String areaCode, final String firstPart,
	        final String secondPart) {
		this.AREA_CODE = areaCode;
		this.FIRST_PART = firstPart;
		this.SECOND_PART = secondPart;
	}

	@Override
	public String toString() {
		return "(" + AREA_CODE + ")" + "-" + FIRST_PART + "-" + SECOND_PART;
	}
}