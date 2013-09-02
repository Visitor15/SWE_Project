package com.mobile.nuesoft.patient;


public class IdentifierBuilder {
	private String firstName;
	private String lastName;
	private String ssn;
	private String address;
	private Telephone tel;
	private String email;

	public IdentifierBuilder() {}

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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Telephone getTel() {
		return tel;
	}

	public void setTel(final String areaCode, final String firstPart, final String secondPart) {
		this.tel = new Telephone(areaCode, firstPart, secondPart);
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public PatientIdentifier build() {
		return new PatientIdentifier(firstName, lastName, ssn, address, tel, email);
	}


	public class PatientIdentifier {

		private final String FIRST_NAME;
		private final String LAST_NAME;
		private final String SSN;
		private final String ADDRESS;
		private final Telephone PHONE_MOBILE;
		private final String EMAIL;

		private PatientIdentifier(final String FIRST_NAME, final String LAST_NAME, final String SSN, final String ADDRESS, final Telephone PHONE_MOBILE, final String EMAIL) {
			this.FIRST_NAME = FIRST_NAME;
			this.LAST_NAME = LAST_NAME;
			this.SSN = SSN;
			this.ADDRESS = ADDRESS;
			this.PHONE_MOBILE = PHONE_MOBILE;
			this.EMAIL = EMAIL;
		}

		public String getFIRST_NAME() {
			return FIRST_NAME;
		}

		public String getLAST_NAME() {
			return LAST_NAME;
		}

		public String getSSN() {
			return SSN;
		}

		public String getADDRESS() {
			return ADDRESS;
		}

		public Telephone getPHONE_MOBILE() {
			return PHONE_MOBILE;
		}

		public String getEMAIL() {
			return EMAIL;
		}
	}

	private class Telephone {
		private String AREA_CODE;
		private String FIRST_PART;
		private String SECOND_PART;

		public Telephone() {
			AREA_CODE = "000";
			FIRST_PART = "000";
			SECOND_PART = "0000";
		}

		public Telephone(final String areaCode, final String firstPart, final String secondPart) {
			this.AREA_CODE = areaCode;
			this.FIRST_PART = firstPart;
			this.SECOND_PART = secondPart;
		}

		@Override
		public String toString() {
			return "(" + AREA_CODE + ")" + "-" + FIRST_PART + "-" + SECOND_PART;
		}
	}
}