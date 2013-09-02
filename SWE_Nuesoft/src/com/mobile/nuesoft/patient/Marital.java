package com.mobile.nuesoft.patient;

public class Marital {
	private final STATUS mMaritalStatus;
	private final String mStatusCode;

	public Marital(final STATUS mMaritalStatus, final String mStatusCode) {
		this.mMaritalStatus = mMaritalStatus;
		this.mStatusCode = mStatusCode;
	}

	@Override
	public String toString() {
		return mMaritalStatus.getTitle();
	}

	public enum STATUS {
		SINGLE("Single"),
		DIVORCED("Divorced"),
		MARRIED("Married");

		private String mTitle;

		private STATUS(final String title) {
			mTitle = title;
		}

		public String getTitle() {
			return mTitle;
		}
	}
}
