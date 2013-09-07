package com.mobile.nuesoft.document;

public class DocRecipient {

	private final String REASON_FOR_REFERRAL;
	private final String FACILITY_TYPE_CODE;
	private final String FACILITY_NAME;
	private final Personnel RECIPIENT;

	public DocRecipient(final Personnel RECIPIENT, final String FACILITY_NAME, final String FACILITY_TYPE_CODE,
	        final String REASON_FOR_REFERRAL) {
		this.RECIPIENT = RECIPIENT;
		this.FACILITY_NAME = FACILITY_NAME;
		this.FACILITY_TYPE_CODE = FACILITY_TYPE_CODE;
		this.REASON_FOR_REFERRAL = REASON_FOR_REFERRAL;
	}

	public String getREASON_FOR_REFERRAL() {
		return REASON_FOR_REFERRAL;
	}

	public String getFACILITY_TYPE_CODE() {
		return FACILITY_TYPE_CODE;
	}

	public String getFACILITY_NAME() {
		return FACILITY_NAME;
	}

	public Personnel getRECIPIENT() {
		return RECIPIENT;
	}
}
