package com.mobile.nuesoft.patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mobile.nuesoft.patient.IdentifierBuilder.PatientIdentifier;

public class PatientBuilder {
	private PatientIdentifier id;
	private String firstName;
	private String lastName;
	private Address address;
	private String birthTime;
	private Gender gender;
	private String race;
	private String religion;
	private String ethnicGroup;
	private Marital.STATUS maritalStatus;
	private ArrayList<Language> languages;
	private ArrayList<Medication> medicationCurrent;
	private ArrayList<Medication> medicationPrevious;
	private ArrayList<MedicalEncounter> medicalEncounters;
	private ArrayList<PatientTest> tests;

	public PatientBuilder() {
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}

	public Marital.STATUS getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Marital.STATUS maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public List<Medication> getMedicationCurrent() {
		return medicationCurrent;
	}

	public void setMedicationCurrent(ArrayList<Medication> medicationCurrent) {
		this.medicationCurrent = medicationCurrent;
	}

	public List<Medication> getMedicationPrevious() {
		return medicationPrevious;
	}

	public void setMedicationPrevious(ArrayList<Medication> medicationPrevious) {
		this.medicationPrevious = medicationPrevious;
	}

	public List<MedicalEncounter> getMedicalEncounters() {
		return medicalEncounters;
	}

	public void setMedicalEncounters(ArrayList<MedicalEncounter> medicalEncounters) {
		this.medicalEncounters = medicalEncounters;
	}

	public List<PatientTest> getTests() {
		return tests;
	}

	public void setTests(ArrayList<PatientTest> tests) {
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

	public void setLanguages(ArrayList<Language> languages) {
		this.languages = languages;
	}

	public PatientObj build() {
		return new PatientObj(id, firstName, lastName, birthTime, address,
		        gender, race, religion, ethnicGroup, languages,
		        medicationCurrent, medicationPrevious, medicalEncounters,
		        tests, maritalStatus);
	}

	public class PatientObj implements Serializable {

		/**
		 * Serializing purposes
		 */
		private static final long serialVersionUID = -5213206329002435084L;

		private final PatientIdentifier IDENTIFIER;
		private final String FIRST_NAME;
		private final String LAST_NAME;
		private final String BIRTH_TIME;
		private final String RACE;
		private final String RELIGION;
		private final String ETHNIC_GROUP;
		private final Gender GENDER;
		private final Marital.STATUS MARITAL;
		private final Address ADDRESS;

		private ArrayList<Language> LANGUAGES;
		private ArrayList<Allergy> ALLERGIES;
		private ArrayList<Medication> MEDICATION_CURRENT;
		private ArrayList<Medication> MEDICATION_PREVIOUS;
		private ArrayList<MedicalEncounter> MEDICAL_ENCOUNTERS;
		private ArrayList<PatientTest> TESTS;

		private PatientObj(final PatientIdentifier IDENTIFIER,
		        final String FIRST_NAME, final String LAST_NAME,
		        final String BIRTH_TIME, final Address ADDRESS,
		        final Gender GENDER, final String RACE, final String RELIGION,
		        final String ETHNIC_GROUP, final ArrayList<Language> LANGUAGES,
		        final ArrayList<Medication> MEDICATION_CURRENT,
		        final ArrayList<Medication> MEDICATION_PREVIOUS,
		        final ArrayList<MedicalEncounter> MEDICATION_ENCOUNTERS,
		        final ArrayList<PatientTest> TESTS,
		        final Marital.STATUS MARITAL_STATUS) {

			this.IDENTIFIER = IDENTIFIER;
			this.FIRST_NAME = FIRST_NAME;
			this.LAST_NAME = LAST_NAME;
			this.BIRTH_TIME = BIRTH_TIME;
			this.ADDRESS = ADDRESS;
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

		public String getBIRTH_TIME() {
			return BIRTH_TIME;
		}

		public Address getADDRESS() {
			return ADDRESS;
		}
		
		public ArrayList<Allergy> getALLERGIES() {
			return ALLERGIES;
		}

		public void setALLERGIES(ArrayList<Allergy> aLLERGIES) {
			ALLERGIES = aLLERGIES;
		}

		public ArrayList<Medication> getMEDICATION_CURRENT() {
			return (ArrayList<Medication>) MEDICATION_CURRENT.clone();
		}

		public void setMEDICATION_CURRENT(
		        ArrayList<Medication> mEDICATION_CURRENT) {
			MEDICATION_CURRENT = mEDICATION_CURRENT;
		}

		public ArrayList<Medication> getMEDICATION_PREVIOUS() {
			return (ArrayList<Medication>) MEDICATION_PREVIOUS.clone();
		}

		public void setMEDICATION_PREVIOUS(
		        ArrayList<Medication> mEDICATION_PREVIOUS) {
			MEDICATION_PREVIOUS = mEDICATION_PREVIOUS;
		}

		public ArrayList<MedicalEncounter> getMEDICAL_ENCOUNTERS() {
			return (ArrayList<MedicalEncounter>) MEDICAL_ENCOUNTERS.clone();
		}

		public void setMEDICAL_ENCOUNTERS(
		        ArrayList<MedicalEncounter> mEDICAL_ENCOUNTERS) {
			MEDICAL_ENCOUNTERS = mEDICAL_ENCOUNTERS;
		}

		public ArrayList<PatientTest> getTESTS() {
			return (ArrayList<PatientTest>) TESTS.clone();
		}

		public void setTESTS(ArrayList<PatientTest> tESTS) {
			TESTS = tESTS;
		}

		public Gender getGENDER() {
			return GENDER;
		}

		public Marital.STATUS getMARITAL() {
			return MARITAL;
		}

		public void setLANGUAGES(ArrayList<Language> lANGUAGES) {
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

		public ArrayList<Language> getLANGUAGES() {
			return (ArrayList<Language>) LANGUAGES.clone();
		}

		@Override
		public String toString() {
			String results = "";

			results += "Name: " + this.getIDENTIFIER().getFIRST_NAME() + " "
			        + this.getIDENTIFIER().getLAST_NAME() + "\n";
			results += "Gender: " + this.getGENDER().toString() + "\n";
			results += "Marital Status: " + this.getMARITAL().getTitle() + "\n";
			results += "Race: " + this.getRACE() + "\n";
			results += "Religion: " + this.getRELIGION() + "\n";
			results += "Ethnic Group: " + this.getETHNIC_GROUP() + "\n";
			results += "# Langages: " + this.getLANGUAGES().size() + "\n";
//			results += "# Meds Current: " + this.getMEDICATION_CURRENT().size()
//			        + "\n";
//			results += "# Meds Previous: "
//			        + this.getMEDICATION_PREVIOUS().size() + "\n";
//			results += "# Medical Events: "
//			        + this.getMEDICAL_ENCOUNTERS().size() + "\n";

			return results;
		}
	}
}
