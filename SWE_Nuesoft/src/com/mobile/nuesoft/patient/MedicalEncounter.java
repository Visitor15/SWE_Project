package com.mobile.nuesoft.patient;

import java.util.Date;

public class MedicalEncounter {

	private final String TITLE;
	private final String REASON_FOR_VISIT;
	private final Date DATE_LOW;
	private final Date DATE_HIGH;
	private final String ADDRESS;
	private final String PRIMARY_DOCTOR;
	private final String SUMMARY;
	private final String PLAN_OF_CARE;
	private final VitalSigns VITAL_SIGNS_LOW;
	private final VitalSigns VITAL_SIGNS_HIGH;

	public MedicalEncounter(final String TITLE, final String REASON_FOR_VISIT, final Date DATE_LOW, Date DATE_HIGH, String ADDRESS, String PRIMARY_DOCTOR, String SUMMARY, String PLAN_OF_CARE, VitalSigns vITAL_SIGNS_LOW, VitalSigns vITAL_SIGNS_HIGH) {
		this.TITLE = TITLE;
		this.REASON_FOR_VISIT = REASON_FOR_VISIT;
		this.DATE_LOW = DATE_LOW;
		this.DATE_HIGH = DATE_HIGH;
		this.ADDRESS = ADDRESS;
		this.PRIMARY_DOCTOR = PRIMARY_DOCTOR;
		this.SUMMARY = SUMMARY;
		this.PLAN_OF_CARE = PLAN_OF_CARE;
		this.VITAL_SIGNS_LOW = vITAL_SIGNS_LOW;
		this.VITAL_SIGNS_HIGH = vITAL_SIGNS_HIGH;
	}
	
	public String getTITLE() {
		return TITLE;
	}

	public String getREASON_FOR_VISIT() {
		return REASON_FOR_VISIT;
	}
	
	public Date getDATE_LOW() {
		return DATE_LOW;
	}
	
	public Date getDATE_HIGH() {
		return DATE_HIGH;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public String getPRIMARY_DOCTOR() {
		return PRIMARY_DOCTOR;
	}
	
	public String getSUMMARY() {
		return SUMMARY;
	}
	public String getPLAN_OF_CARE() {
		return PLAN_OF_CARE;
	}
	public VitalSigns getVITAL_SIGNS_LOW() {
		return VITAL_SIGNS_LOW;
	}
	
	public VitalSigns getVITAL_SIGNS_HIGH() {
		return VITAL_SIGNS_HIGH;
	}

	public class VitalSigns {
		private final double mTemperature;
		private final double mSystolicPressure;
		private final double mDiastolicPressure;
		private final int mPulse;
		private final int mBreathsPerMinute;

		public VitalSigns(final double mTemperature, final double mSystolicPressure, final double mDiastolicPressure, final int mPulse, final int mBreathsPerMinute) {
			this.mTemperature = mTemperature;
			this.mSystolicPressure = mSystolicPressure;
			this.mDiastolicPressure = mDiastolicPressure;
			this.mPulse = mPulse;
			this.mBreathsPerMinute = mBreathsPerMinute;
		}
	}
}

