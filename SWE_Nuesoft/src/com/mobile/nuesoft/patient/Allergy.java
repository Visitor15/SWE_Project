package com.mobile.nuesoft.patient;

public class Allergy {

	private final String code;
	private final String displayName;
	private final long effectiveDateLow;
	private final long effectiveDateHigh;
	private final STATUS currentStatus;

	public enum STATUS {
		ACTIVE, UNACTIVE
	};

	public Allergy(final String displayName, final String code,
	        final long effectiveDateLow, final long effectiveDateHigh,
	        final STATUS currentStatus) {
		this.code = code;
		this.displayName = displayName;
		this.effectiveDateLow = effectiveDateLow;
		this.effectiveDateHigh = effectiveDateHigh;
		this.currentStatus = currentStatus;
	}

	public String getCode() {
		return code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public long getEffectiveDateLow() {
		return effectiveDateLow;
	}

	public long getEffectiveDateHigh() {
		return effectiveDateHigh;
	}

	public STATUS getCurrentStatus() {
		return currentStatus;
	}

	@Override
	public String toString() {
		return "";
	}
}
