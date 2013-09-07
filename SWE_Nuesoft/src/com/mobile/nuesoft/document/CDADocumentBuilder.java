package com.mobile.nuesoft.document;

import java.util.ArrayList;


public class CDADocumentBuilder {
	
	public static final String TAG = "CDADocumentBuilder";
	
	private ArrayList<Author> mAuthor = new ArrayList<Author>();
	private ArrayList<Custodian> mCustodian = new ArrayList<Custodian>();
	private ArrayList<DataEnterer> mDataEnterer = new ArrayList<DataEnterer>();
	private ArrayList<DocRecipient> mRecipient = new ArrayList<DocRecipient>();
	private ArrayList<LegalAuthenticator> mLegalAuthenticator = new ArrayList<LegalAuthenticator>();
	private ArrayList<Participant> mParticipants = new ArrayList<Participant>();
	private ServiceEvent mServiceEvent;
	private Encounter mEncounter;
	private String mDisplayTitle;
	private String mCode;
	private String mCodeSystem;
	private String mCodeSystemName;
	
	public CDADocumentBuilder() {
		
	}

	public String getmDisplayTitle() {
		return mDisplayTitle;
	}

	public void setmDisplayTitle(String mDisplayTitle) {
		this.mDisplayTitle = mDisplayTitle;
	}

	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	public String getmCodeSystem() {
		return mCodeSystem;
	}

	public void setmCodeSystem(String mCodeSystem) {
		this.mCodeSystem = mCodeSystem;
	}

	public String getmCodeSystemName() {
		return mCodeSystemName;
	}

	public void setmCodeSystemName(String mCodeSystemName) {
		this.mCodeSystemName = mCodeSystemName;
	}

	public ArrayList<Author> getmAuthor() {
		return mAuthor;
	}

	public void setmAuthor(ArrayList<Author> mAuthor) {
		this.mAuthor = mAuthor;
	}

	public ArrayList<Custodian> getmCustodian() {
		return mCustodian;
	}

	public void setmCustodian(ArrayList<Custodian> mCustodian) {
		this.mCustodian = mCustodian;
	}

	public ArrayList<DataEnterer> getmDataEnterer() {
		return mDataEnterer;
	}

	public void setmDataEnterer(ArrayList<DataEnterer> mDataEnterer) {
		this.mDataEnterer = mDataEnterer;
	}

	public ArrayList<DocRecipient> getmRecipient() {
		return mRecipient;
	}

	public void setmRecipient(ArrayList<DocRecipient> mRecipient) {
		this.mRecipient = mRecipient;
	}

	public ArrayList<LegalAuthenticator> getmLegalAuthenticator() {
		return mLegalAuthenticator;
	}

	public void setmLegalAuthenticator(ArrayList<LegalAuthenticator> mLegalAuthenticator) {
		this.mLegalAuthenticator = mLegalAuthenticator;
	}

	public ArrayList<Participant> getmParticipants() {
		return mParticipants;
	}

	public void setmParticipants(ArrayList<Participant> mParticipants) {
		this.mParticipants = mParticipants;
	}

	public ServiceEvent getmServiceEvent() {
		return mServiceEvent;
	}

	public void setmServiceEvent(ServiceEvent mServiceEvent) {
		this.mServiceEvent = mServiceEvent;
	}

	public Encounter getmEncounter() {
		return mEncounter;
	}

	public void setmEncounter(Encounter mEncounter) {
		this.mEncounter = mEncounter;
	}
	
	@Override
	public String toString() {
		return "CDADocument";
	}

	public class CDADocument {

		public static final String TAG = "CDADocument";
		
		private final ArrayList<Author> AUTHOR;
		private final ArrayList<Custodian> CUSTODIAN;
		private final ArrayList<DataEnterer> DATA_ENTERER;
		private final ArrayList<DocRecipient> RECIPIENT;
		private final ArrayList<LegalAuthenticator> LEGAL_AUTHENTICATOR;
		private final ArrayList<Participant> PARTICIPANTS;
		private final ServiceEvent SERVICE_EVENT;
		private final Encounter ENCOUNTER;
		private final String DISPLAY_TITLE;
		private final String CODE;
		private final String CODE_SYSTEM;
		private final String CODE_SYSTEM_NAME;
		
		private CDADocument(final CDADocumentBuilder builder) {
			this.AUTHOR = builder.getmAuthor();
			this.CUSTODIAN = builder.getmCustodian();
			this.DATA_ENTERER = builder.getmDataEnterer();
			this.RECIPIENT = builder.getmRecipient();
			this.LEGAL_AUTHENTICATOR = builder.getmLegalAuthenticator();
			this.PARTICIPANTS = builder.getmParticipants();
			this.SERVICE_EVENT = builder.getmServiceEvent();
			this.ENCOUNTER = builder.getmEncounter();
			this.DISPLAY_TITLE = builder.getmDisplayTitle();
			this.CODE = builder.getmCode();
			this.CODE_SYSTEM = builder.getmCodeSystem();
			this.CODE_SYSTEM_NAME = builder.getmCodeSystemName();
		}

		public String getDISPLAY_TITLE() {
			return DISPLAY_TITLE;
		}

		public String getCODE() {
			return CODE;
		}

		public String getCODE_SYSTEM() {
			return CODE_SYSTEM;
		}

		public String getCODE_SYSTEM_NAME() {
			return CODE_SYSTEM_NAME;
		}

		public ArrayList<Author> getAUTHOR() {
			return AUTHOR;
		}

		public ArrayList<Custodian> getCUSTODIAN() {
			return CUSTODIAN;
		}

		public ArrayList<DataEnterer> getDATA_ENTERER() {
			return DATA_ENTERER;
		}

		public ArrayList<DocRecipient> getRECIPIENT() {
			return RECIPIENT;
		}

		public ArrayList<LegalAuthenticator> getLEGAL_AUTHENTICATOR() {
			return LEGAL_AUTHENTICATOR;
		}

		public ArrayList<Participant> getPARTICIPANTS() {
			return PARTICIPANTS;
		}

		public ServiceEvent getSERVICE_EVENT() {
			return SERVICE_EVENT;
		}

		public Encounter getENCOUNTER() {
			return ENCOUNTER;
		}
	}
}
