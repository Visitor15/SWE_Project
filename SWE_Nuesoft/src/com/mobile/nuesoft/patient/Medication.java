package com.mobile.nuesoft.patient;

public class Medication {
	
	private final String TITLE;
	private final String DESCRIPTION;
	private final String INSTRUCTIONS;
	private final int AMOUNT;
	private final String AMOUNT_TYPE;
	
	public Medication(final String TITLE, final String DESCRIPTION, final String INSTRUCTIONS, final int AMOUNT, final String AMOUNT_TYPE) {
		this.TITLE = TITLE;
		this.DESCRIPTION = DESCRIPTION;
		this.AMOUNT = AMOUNT;
		this.AMOUNT_TYPE = AMOUNT_TYPE;
		this.INSTRUCTIONS = INSTRUCTIONS;
	}

	public String getTITLE() {
		return TITLE;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	
	public String getINSTRUCTIONS() {
		return INSTRUCTIONS;
	}

	public int getAMOUNT() {
		return AMOUNT;
	}

	public String getAMOUNT_TYPE() {
		return AMOUNT_TYPE;
	}
	
	@Override
	public String toString() {
		return this.getTITLE();
	}
}
