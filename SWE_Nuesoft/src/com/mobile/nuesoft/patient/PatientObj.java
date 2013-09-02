package com.mobile.nuesoft.patient;

import java.util.List;

public class PatientObj {

	private final PatientIdentifier IDENTIFIER;
	private final String FIRST_NAME;
	private final String LAST_NAME;
	private final String RACE;
	private final String RELIGION;
	private final String ETHNIC_GROUP;
	private final Gender GENDER;
	private final Marital MARITAL;
	
	private List<Language> LANGUAGES;
	private List<Medication> MEDICATION_CURRENT;
	private List<Medication> MEDICATION_PREVIOUS;
	private List<MedicalEncounter> MEDICAL_ENCOUNTERS;
	private List<PatientTest> TESTS;
	
	private PatientObj(final PatientIdentifier IDENTIFIER, final String FIRST_NAME, final String LAST_NAME, final Gender GENDER, final String RACE, final String RELIGION, final String ETHNIC_GROUP, final List<Language> LANGUAGES, final List<Medication> MEDICATION_CURRENT, final List<Medication> MEDICATION_PREVIOUS, final List<MedicalEncounter> MEDICATION_ENCOUNTERS, final List<PatientTest> TESTS, final Marital MARITAL_STATUS) {
		this.IDENTIFIER = IDENTIFIER;
		this.FIRST_NAME = FIRST_NAME;
		this.LAST_NAME = LAST_NAME;
		this.GENDER = GENDER;
		this.RACE = RACE;
		this.RELIGION = RELIGION;
		this.ETHNIC_GROUP = ETHNIC_GROUP;
		this.LANGUAGES = LANGUAGES;
		this.MEDICATION_CURRENT = MEDICATION_CURRENT;
		this.MEDICATION_PREVIOUS = MEDICATION_PREVIOUS;
		this.MEDICAL_ENCOUNTERS = MEDICATION_ENCOUNTERS;
		this.TESTS = TESTS;
		this.MARITAL = MARITAL_STATUS;
	}
	
	public PatientIdentifier getIDENTIFIER() {
		return IDENTIFIER;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public List<Medication> getMEDICATION_CURRENT() {
		return MEDICATION_CURRENT;
	}

	public void setMEDICATION_CURRENT(List<Medication> mEDICATION_CURRENT) {
		MEDICATION_CURRENT = mEDICATION_CURRENT;
	}

	public List<Medication> getMEDICATION_PREVIOUS() {
		return MEDICATION_PREVIOUS;
	}

	public void setMEDICATION_PREVIOUS(List<Medication> mEDICATION_PREVIOUS) {
		MEDICATION_PREVIOUS = mEDICATION_PREVIOUS;
	}

	public List<MedicalEncounter> getMEDICAL_ENCOUNTERS() {
		return MEDICAL_ENCOUNTERS;
	}

	public void setMEDICAL_ENCOUNTERS(List<MedicalEncounter> mEDICAL_ENCOUNTERS) {
		MEDICAL_ENCOUNTERS = mEDICAL_ENCOUNTERS;
	}

	public List<PatientTest> getTESTS() {
		return TESTS;
	}

	public void setTESTS(List<PatientTest> tESTS) {
		TESTS = tESTS;
	}

	public Gender getGENDER() {
		return GENDER;
	}

	public Marital getMARITAL() {
		return MARITAL;
	}

	public void setLANGUAGES(List<Language> lANGUAGES) {
		LANGUAGES = lANGUAGES;
	}

	public String getRACE() {
		return RACE;
	}

	public String getRELIGION() {
		return RELIGION;
	}

	public String getETHNIC_GROUP() {
		return ETHNIC_GROUP;
	}

	public List<Language> getLANGUAGES() {
		return LANGUAGES;
	}

	public class PatientBuilder {
		private PatientIdentifier id;
		private String firstName;
		private String lastName;
		private Gender gender;
		private String race;
		private String religion;
		private String ethnicGroup;
		private Marital maritalStatus;
		private List<Language> languages;
		private List<Medication> medicationCurrent;
		private List<Medication> medicationPrevious;
		private List<MedicalEncounter> medicalEncounters;
		private List<PatientTest> tests;
		
		public PatientBuilder() {}
		
		public Gender getGender() {
			return gender;
		}

		public void setGender(Gender gender) {
			this.gender = gender;
		}
		
		public Marital getMaritalStatus() {
			return maritalStatus;
		}

		public void setMaritalStatus(Marital maritalStatus) {
			this.maritalStatus = maritalStatus;
		}

		public List<Medication> getMedicationCurrent() {
			return medicationCurrent;
		}

		public void setMedicationCurrent(List<Medication> medicationCurrent) {
			this.medicationCurrent = medicationCurrent;
		}

		public List<Medication> getMedicationPrevious() {
			return medicationPrevious;
		}

		public void setMedicationPrevious(List<Medication> medicationPrevious) {
			this.medicationPrevious = medicationPrevious;
		}

		public List<MedicalEncounter> getMedicalEncounters() {
			return medicalEncounters;
		}

		public void setMedicalEncounters(List<MedicalEncounter> medicalEncounters) {
			this.medicalEncounters = medicalEncounters;
		}

		public List<PatientTest> getTests() {
			return tests;
		}

		public void setTests(List<PatientTest> tests) {
			this.tests = tests;
		}

		public PatientIdentifier getId() {
			return id;
		}

		public void setId(PatientIdentifier id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getRace() {
			return race;
		}

		public void setRace(String race) {
			this.race = race;
		}

		public String getReligion() {
			return religion;
		}

		public void setReligion(String religion) {
			this.religion = religion;
		}

		public String getEthnicGroup() {
			return ethnicGroup;
		}

		public void setEthnicGroup(String ethnicGroup) {
			this.ethnicGroup = ethnicGroup;
		}

		public List<Language> getLanguages() {
			return languages;
		}

		public void setLanguages(List<Language> languages) {
			this.languages = languages;
		}
		
		public PatientObj build() {
			return new PatientObj(id, ethnicGroup, ethnicGroup, gender, ethnicGroup, ethnicGroup, ethnicGroup, languages, medicationCurrent, medicationCurrent, medicalEncounters, tests, maritalStatus);
		}
	}
	
	public class Language {
		private final String displayName;
		
		public Language(final String name) {
			this.displayName = name;
		}
		
		public String getDisplayName() {
			return displayName;
		}
	}
}
