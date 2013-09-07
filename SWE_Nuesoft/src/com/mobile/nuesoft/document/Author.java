package com.mobile.nuesoft.document;

public class Author {

	private final Personnel AUTHOR;
	private final Organization ORGANIZATION;
	private final String DATE_AUTHORED;
	
	public Author(final Personnel AUTHOR, final Organization ORGANIZATION, final String DATE_AUTHORED) {
		this.AUTHOR = AUTHOR;
		this.ORGANIZATION = ORGANIZATION;
		this.DATE_AUTHORED = DATE_AUTHORED;
	}

	public Personnel getAUTHOR() {
		return AUTHOR;
	}

	public Organization getORGANIZATION() {
		return ORGANIZATION;
	}

	public String getDATE_AUTHORED() {
		return DATE_AUTHORED;
	}
}
