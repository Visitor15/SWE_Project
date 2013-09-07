package com.mobile.nuesoft.document;

public class LegalAuthenticator {

	private final String TIME_AUTHENTICATED;
	private Organization ORGANIZATION;
	private Personnel AUTHENTICATOR;

	public LegalAuthenticator(final String TIME_AUTHENTICATED, final Organization ORGANIZATION,
	        final Personnel AUTHENTICATOR) {
		this.TIME_AUTHENTICATED = TIME_AUTHENTICATED;
		this.ORGANIZATION = ORGANIZATION;
		this.AUTHENTICATOR = AUTHENTICATOR;
	}

	public Organization getORGANIZATION() {
		return ORGANIZATION;
	}

	public void setORGANIZATION(Organization oRGANIZATION) {
		ORGANIZATION = oRGANIZATION;
	}

	public Personnel getAUTHENTICATOR() {
		return AUTHENTICATOR;
	}

	public void setAUTHENTICATOR(Personnel aUTHENTICATOR) {
		AUTHENTICATOR = aUTHENTICATOR;
	}

	public String getTIME_AUTHENTICATED() {
		return TIME_AUTHENTICATED;
	}
}
