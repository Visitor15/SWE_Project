package com.mobile.nuesoft.jobs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.mobile.nuesoft.Nuesoft;
import com.mobile.nuesoft.data.Codes;
import com.mobile.nuesoft.document.CDADocumentBuilder;
import com.mobile.nuesoft.patient.Address;
import com.mobile.nuesoft.patient.Allergy;
import com.mobile.nuesoft.patient.Allergy.STATUS;
import com.mobile.nuesoft.patient.AllergyReaction;
import com.mobile.nuesoft.patient.AllergyType;
import com.mobile.nuesoft.patient.Drug;
import com.mobile.nuesoft.patient.Gender;
import com.mobile.nuesoft.patient.IdentifierBuilder;
import com.mobile.nuesoft.patient.Language;
import com.mobile.nuesoft.patient.Marital;
import com.mobile.nuesoft.patient.Medication;
import com.mobile.nuesoft.patient.PatientBuilder;
import com.mobile.nuesoft.patient.PatientBuilder.PatientObj;
import com.mobile.nuesoft.patient.Severity;
import com.mobile.nuesoft.patient.Telephone;
import com.mobile.nuesoft.util.XMLParserUtil;

public class ParseCDADocumentJob extends AsyncTask<String, PatientObj, PatientObj> {

	public static final String TAG = "ParseCDADocumentJob";

	public static final String IS_FINISHED_KEY = "com.mobile.nuesoft.job.FINISHED_KEY";

	private Bundle updateBundle;

	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	private File docFile;

	private ArrayList<Node> componentNodeList;

	private CDADocumentBuilder docBuilder;
	private PatientBuilder patBuilder;
	private PatientObj patientObj;
	private IdentifierBuilder patIdBuilder;

	public ParseCDADocumentJob() {
	}

	@Override
	protected void onPostExecute(PatientObj result) {
		super.onPostExecute(result);

		if (result != null) {
			updateBundle = new Bundle();
			updateBundle.putBoolean(ParseCDADocumentJob.IS_FINISHED_KEY, true);
			updateBundle.putSerializable(PatientUpdateEvent.PATIENT_OBJ_KEY, result);

			Log.d(TAG, "On Post Execute for PATIENT: " + result);

			Nuesoft.getReference().setPatientToCurrent(result);

			PatientUpdateEvent.broadcast(Nuesoft.getReference(), updateBundle);
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		componentNodeList = new ArrayList<Node>();
		patIdBuilder = new IdentifierBuilder();
		patBuilder = new PatientBuilder();
		docBuilder = new CDADocumentBuilder();
	}

	@Override
	protected void onProgressUpdate(PatientObj... values) {
		super.onProgressUpdate(values);
		PatientObj updatedPatient = values[0];

		updateBundle = new Bundle();
		updateBundle.putBoolean(ParseCDADocumentJob.IS_FINISHED_KEY, false);
		updateBundle.putSerializable(PatientUpdateEvent.PATIENT_OBJ_KEY, updatedPatient);

		Nuesoft.getReference().setPatientToCurrent(updatedPatient);

		PatientUpdateEvent.broadcast(Nuesoft.getReference(), updateBundle);
	}

	@Override
	protected PatientObj doInBackground(String... docPath) {
		long runningTime = 0;

		try {
			docFile = new File("storage/sdcard0/Download/cda_sample_file.xml");
			if (docFile.exists()) {
				patientObj = parseDocument(docFile);
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return patientObj;
	}

	private PatientObj parseDocument(final File mFile) throws SAXException, IOException, ParserConfigurationException {
		dbFactory = DocumentBuilderFactory.newInstance();
		dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(mFile);

		NodeList rootList = doc.getElementsByTagName("ClinicalDocument");
		Node root = XMLParserUtil.getNode("ClinicalDocument", rootList);
		Node record = XMLParserUtil.getNode("recordTarget", root.getChildNodes());
		root = XMLParserUtil.getCDADocumentBodySection(root);
		componentNodeList = XMLParserUtil.getComponentNodesFromBody(root);

		parsePatientGeneralInfo(record, patBuilder);

		for (Node n : componentNodeList) {
			Node sectionNode = XMLParserUtil.getNode("section", n.getChildNodes());
			Node code = XMLParserUtil.getNode("code", sectionNode.getChildNodes());

			int id = Codes.getInstance().codeMap.get(XMLParserUtil.getNodeAttr("code", code));
			switch (id) {
				case Codes.ADVANCED_DIRECTIVES_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientAdvancedDirectivesFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.ALLERGY_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientAllergiesFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.FAMILY_HISTORY_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientFamilyHistoryFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.FUNCTIONAL_COGNITIVE_STATUS_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientFunctionalCognitiveStatusFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.IMMUNIZATIONS_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientImmunizationsFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.INSTRUCTIONS_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientInstructionsFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.MEDICATIONS_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientMedicationsFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.PLAN_OF_CARE_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientPlanOfCareFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.PROBLEM_LIST_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientProblemsFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.PROCEDURES_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientProceduresFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.REASON_FOR_REFERRAL_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientReasonForReferralFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.REASON_FOR_VISIT_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientReasonForVisitFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.SOCIAL_HISTORY_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientSocialHistoryFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.TEST_RESULTS_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientTestResultsFromNode(sectionNode, patBuilder);
					break;
				}
				case Codes.VITAL_SIGNS_ID: {
					Log.d(TAG, "GOT COMPONTENT: " + id);
					parsePatientVitalSignsFromNode(sectionNode, patBuilder);
					break;
				}
				default: {
					Log.d(TAG, "DOCUMENT CODE NOT FOUND: " + id);
				}
			}
		}

		return patBuilder.build();
	}

	private void parsePatientVitalSignsFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientTestResultsFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientSocialHistoryFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientReasonForVisitFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientReasonForReferralFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientProceduresFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientProblemsFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientPlanOfCareFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientInstructionsFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientImmunizationsFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientFunctionalCognitiveStatusFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientFamilyHistoryFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientAdvancedDirectivesFromNode(final Node sectionNode, final PatientBuilder patBuilder) {
		// TODO Auto-generated method stub

	}

	private void parsePatientAllergiesFromNode(final Node root, final PatientBuilder patient) {
		ArrayList<Drug> allergyDrugList = new ArrayList<Drug>();
		ArrayList<String> allergyNames = new ArrayList<String>();
		Drug allergyDrug;
		String displayName = "";
		Allergy mAllergy = null;
		Node codeNode = XMLParserUtil.getNode("code", root.getChildNodes());
		String code = XMLParserUtil.getNodeAttr("code", codeNode);
		if (code.equals(Codes.ALLERGY_KEY)) {

			Node allergyTextNode = XMLParserUtil.getNode("text", root.getChildNodes());
			if (allergyTextNode != null) {
				Node allergyListNode = XMLParserUtil.getNode("list", allergyTextNode.getChildNodes());
				if (allergyListNode != null) {
					if (allergyListNode.hasChildNodes()) {
						ArrayList<Node> itemList = XMLParserUtil.getNamedNodes("item", allergyListNode);

						Node content;
						for (int j = 0; j < itemList.size(); j++) {
							Node item = itemList.get(j);
							if (item.getNodeName().equals("item")) {
								content = XMLParserUtil.getNode("content", item.getChildNodes());
								displayName = XMLParserUtil.getNodeValue(content);
								allergyNames.add(displayName);
							}
						}

						ArrayList<Node> entryList = XMLParserUtil.getNamedNodes("entry", root);
						Node observationNode;
						Node tempRootNode;
						for (int q = 0; q < entryList.size(); q++) {
							content = entryList.get(q);
							content = XMLParserUtil.getNode("act", content.getChildNodes());
							tempRootNode = XMLParserUtil.getNode("statusCode", content.getChildNodes());
							// String status = XMLParserUtil.getNodeAttr("code",
							// tempRootNode);

							tempRootNode = XMLParserUtil.getNode("effectiveTime", content.getChildNodes());
							tempRootNode = XMLParserUtil.getNode("low", tempRootNode.getChildNodes());
							// String effectiveTimeLow =
							// XMLParserUtil.getNodeAttr("value", tempRootNode);

							tempRootNode = XMLParserUtil.getNode("code", content.getChildNodes());
							String allergyCode = XMLParserUtil.getNodeAttr("code", tempRootNode);

							String allergyCodeSystem = XMLParserUtil.getNodeAttr("codeSystem", tempRootNode);

							String allergyCodeSystemName = XMLParserUtil.getNodeAttr("codeSystemName", tempRootNode);

							// tempRootNode =
							// XMLParserUtil.getNode("effectiveTime",
							// content.getChildNodes());
							// tempRootNode =
							// XMLParserUtil.getNode("low",
							// tempRootNode.getChildNodes());
							// String effectiveTimeLow =
							// XMLParserUtil.getNodeAttr("low",
							// tempRootNode);
							// Log.d(TAG,
							// "GOT ALLERGY EFFECTIVE LOW DATE: " +
							// effectiveTimeLow);

							content = XMLParserUtil.getNode("entryRelationship", content.getChildNodes());
							observationNode = XMLParserUtil.getNode("observation", content.getChildNodes());

							// tempRootNode =
							// XMLParserUtil.getNode("originalText",
							// observationNode.getChildNodes());
							// Log.d(TAG, "TEMP ROOT NODE: " +
							// tempRootNode.getNodeName());
							// tempDisplayName =
							// XMLParserUtil.getNodeAttr("reference",
							// "value", tempRootNode.getChildNodes());
							// Log.d(TAG, "TEMP DISPLAY NAME: " +
							// tempDisplayName);

							String allergyTypeDisplayName = XMLParserUtil.getNodeAttr("displayName",
							        XMLParserUtil.getNode("value", observationNode.getChildNodes()));

							String allergyTypeCode = XMLParserUtil.getNodeAttr("code",
							        XMLParserUtil.getNode("value", observationNode.getChildNodes()));

							String allergyTypeCodeSystem = XMLParserUtil.getNodeAttr("codeSystem",
							        XMLParserUtil.getNode("value", observationNode.getChildNodes()));

							String allergyTypeCodeSystemName = XMLParserUtil.getNodeAttr("codeSystemName",
							        XMLParserUtil.getNode("value", observationNode.getChildNodes()));

							AllergyType allergyType = new AllergyType(allergyTypeDisplayName, allergyTypeCode,
							        allergyTypeCodeSystem, allergyTypeCodeSystemName);

							ArrayList<Node> participantList = XMLParserUtil.getNamedNodes("participant",
							        observationNode);

							for (int k = 0; k < participantList.size(); k++) {
								tempRootNode = XMLParserUtil.getNode("participantRole", participantList.get(k)
								        .getChildNodes());
								tempRootNode = XMLParserUtil.getNode("playingEntity", tempRootNode.getChildNodes());

								String drugCode;
								String drugDisplayName;
								String drugCodeSystem;
								String drugCodeSystemName;

								drugCode = XMLParserUtil.getNodeAttr("code", "code", tempRootNode.getChildNodes());
								drugDisplayName = XMLParserUtil.getNodeAttr("code", "displayName",
								        tempRootNode.getChildNodes());
								drugCodeSystem = XMLParserUtil.getNodeAttr("code", "codeSystem",
								        tempRootNode.getChildNodes());
								drugCodeSystemName = XMLParserUtil.getNodeAttr("code", "codeSystemName",
								        tempRootNode.getChildNodes());

								allergyDrug = new Drug(drugDisplayName, drugCode, drugCodeSystem, drugCodeSystemName);
								allergyDrugList.add(allergyDrug);
							}

							content = XMLParserUtil.getNode("entryRelationship", observationNode.getChildNodes());
							content = XMLParserUtil.getNode("observation", content.getChildNodes());

							String drugReactionEffectiveDateLow = "";
							String drugReactionEffectiveDateHigh = "";
							String drugReactionDisplayName = "";
							String drugReactionCode = "";
							String drugReactionCodeSystem = "";
							String drugReactionCodeSystemName = "";

							tempRootNode = XMLParserUtil.getNode("value", content.getChildNodes());
							drugReactionDisplayName = XMLParserUtil.getNodeAttr("displayName", tempRootNode);
							drugReactionCode = XMLParserUtil.getNodeAttr("code", tempRootNode);
							drugReactionCodeSystem = XMLParserUtil.getNodeAttr("codeSystem", tempRootNode);
							drugReactionCodeSystemName = XMLParserUtil.getNodeAttr("codeSystemName", tempRootNode);

							tempRootNode = XMLParserUtil.getNode("effectiveTime", content.getChildNodes());
							drugReactionEffectiveDateLow = XMLParserUtil.getNodeAttr("low", "value",
							        tempRootNode.getChildNodes());

							AllergyReaction reaction = new AllergyReaction(drugReactionDisplayName, drugReactionCode,
							        drugReactionCodeSystem, drugReactionCodeSystemName);

							if (drugReactionEffectiveDateLow.trim().length() > 0) {
								reaction.setEffectiveDateLow(drugReactionEffectiveDateLow);
							}
							if (drugReactionEffectiveDateHigh.trim().length() > 0) {
								reaction.setEffectiveDateHigh(drugReactionEffectiveDateHigh);
							}

							String reactionSeverityDisplayName;
							String reactionSeverityCode;
							String reactionSeverityCodeSystem;
							String reactionSeverityCodeSystemName;

							tempRootNode = XMLParserUtil.getNode("entryRelationship", content.getChildNodes());
							tempRootNode = XMLParserUtil.getNode("observation", tempRootNode.getChildNodes());
							tempRootNode = XMLParserUtil.getNode("value", tempRootNode.getChildNodes());

							reactionSeverityDisplayName = XMLParserUtil.getNodeAttr("displayName", tempRootNode);
							reactionSeverityCode = XMLParserUtil.getNodeAttr("code", tempRootNode);
							reactionSeverityCodeSystem = XMLParserUtil.getNodeAttr("codeSystem", tempRootNode);
							reactionSeverityCodeSystemName = XMLParserUtil.getNodeAttr("codeSystemName", tempRootNode);

							Severity allergySeverity = new Severity(reactionSeverityDisplayName, reactionSeverityCode,
							        reactionSeverityCodeSystem, reactionSeverityCodeSystemName);

							// Getting narrative display name
							displayName = allergyNames.get(q);

							// Creating allergy object
							mAllergy = new Allergy(displayName, displayName, allergyCode, allergyCodeSystem,
							        allergyCodeSystemName, reaction, allergySeverity, 0L, 0L, allergyType,
							        STATUS.ACTIVE);
							mAllergy.addAllergicDruglist(allergyDrugList);
							patient.addAllergy(mAllergy);
						}
					}
				}
			}
		}
	}

	private void parsePatientMedicationsFromNode(final Node root, final PatientBuilder patient) {
		String mTitle = "";
		String mInstructions = "";
		String mDateLow = "";
		String mDateHigh = "";
		String mStatus = "";
		String mManufacturerCode = "";
		String mManufacturerCodeSystem = "";
		String mManufacturerCodeSystemName = "";
		String mAdministeredType = "";
		String mAdministeredMethod = "";
		String mAdministeredFreq = "";
		String mDosageQuantity = "";

		ArrayList<Node> itemList;
		ArrayList<Node> entryList;

		Node dataNode = XMLParserUtil.getNode("code", root.getChildNodes());
		String code = XMLParserUtil.getNodeAttr("code", dataNode);
		Log.d(TAG, "GOT MEDICATION CODE: " + code);

		ArrayList<String> medicationNarrativeName = new ArrayList<String>();
		dataNode = XMLParserUtil.getNode("text", root.getChildNodes());
		dataNode = XMLParserUtil.getNode("list", dataNode.getChildNodes());
		itemList = XMLParserUtil.getNamedNodes("item", dataNode);

		Log.d(TAG, "# OF MEDICATIONS IN PATIENT HISTORY: " + itemList.size());

		Node tempNode;
		for (Node n : itemList) {
			tempNode = XMLParserUtil.getNode("content", n.getChildNodes());
			medicationNarrativeName.add(XMLParserUtil.getNodeValue(tempNode));
		}

		entryList = XMLParserUtil.getNamedNodes("entry", root);
		Log.d(TAG, "# OF MEDICATION ENTRIES: " + entryList.size());

		for (int i = 0; i < entryList.size(); i++) {
			tempNode = XMLParserUtil.getNode("substanceAdministration", entryList.get(i).getChildNodes());

			tempNode = XMLParserUtil.getNode("statusCode", tempNode.getChildNodes());
			mStatus = XMLParserUtil.getNodeAttr("code", tempNode);
			Log.d(TAG, "MEDICATION STATUS: " + mStatus);

			tempNode = entryList.get(i);
			tempNode = XMLParserUtil.getNode("substanceAdministration", entryList.get(i).getChildNodes());

			ArrayList<Node> effectiveTimeNodes = XMLParserUtil.getNamedNodes("effectiveTime", tempNode);

			for (int j = 0; j < effectiveTimeNodes.size(); j++) {
				Node dateNode = effectiveTimeNodes.get(j);

				for (int k = 0; k < dateNode.getChildNodes().getLength(); k++) {
					tempNode = dateNode.getChildNodes().item(k);
					Log.d(TAG, "MEDICATION DATE NODE NAME: " + tempNode.getNodeName());
					if (tempNode.getNodeName().equals("low")) {
						mDateLow = XMLParserUtil.getNodeAttr("value", tempNode);
					} else if (tempNode.getNodeName().equals("high")) {
						mDateHigh = XMLParserUtil.getNodeAttr("value", tempNode);
					} else if (tempNode.getNodeName().equals("period")) {
						mAdministeredFreq = XMLParserUtil.getNodeAttr("value", tempNode);
						mAdministeredFreq += XMLParserUtil.getNodeAttr("unit", tempNode);
					}
				}
			}

			Log.d(TAG, "MEDICATION DATE LOW: " + mDateLow);
			Log.d(TAG, "MEDICATION DATE HIGH: " + mDateHigh);

			tempNode = entryList.get(i);
			tempNode = XMLParserUtil.getNode("substanceAdministration", entryList.get(i).getChildNodes());
			tempNode = XMLParserUtil.getNode("routeCode", tempNode.getChildNodes());
			mAdministeredMethod = XMLParserUtil.getNodeAttr("displayName", tempNode);
			Log.d(TAG, "MEDICATION ADMINISTERED METHOD: " + mAdministeredMethod);

			tempNode = XMLParserUtil.getNode("substanceAdministration", entryList.get(i).getChildNodes());
			tempNode = XMLParserUtil.getNode("doseQuantity", tempNode.getChildNodes());
			mDosageQuantity = XMLParserUtil.getNodeAttr("value", tempNode);
			Log.d(TAG, "MEDICATION DOSAGE QUANTITY: " + mDosageQuantity);

			tempNode = XMLParserUtil.getNode("substanceAdministration", entryList.get(i).getChildNodes());
			tempNode = XMLParserUtil.getNode("administrationUnitCode", tempNode.getChildNodes());
			mAdministeredType = XMLParserUtil.getNodeAttr("displayName", tempNode);
			Log.d(TAG, "MEDICATION TYPE: " + mAdministeredType);

			tempNode = XMLParserUtil.getNode("substanceAdministration", entryList.get(i).getChildNodes());
			tempNode = XMLParserUtil.getNode("consumable", tempNode.getChildNodes());
			tempNode = XMLParserUtil.getNode("manufacturedProduct", tempNode.getChildNodes());
			tempNode = XMLParserUtil.getNode("manufacturedMaterial", tempNode.getChildNodes());
			tempNode = XMLParserUtil.getNode("code", tempNode.getChildNodes());
			mManufacturerCode = XMLParserUtil.getNodeAttr("code", tempNode);
			mManufacturerCodeSystem = XMLParserUtil.getNodeAttr("codeSystem", tempNode);
			mManufacturerCodeSystemName = XMLParserUtil.getNodeAttr("codeSystemName", tempNode);
			mTitle = XMLParserUtil.getNodeAttr("displayName", tempNode);

			mInstructions = medicationNarrativeName.get(i);

			if (mDateHigh.trim().length() > 0) {
				patient.addMedicationPrevious(new Medication(mTitle, mInstructions, mDateLow, mDateHigh, mStatus,
				        mManufacturerCode, mManufacturerCodeSystem, mManufacturerCodeSystemName, mAdministeredType,
				        mAdministeredMethod, mAdministeredFreq, mDosageQuantity));
			} else {
				patient.addMedicationCurrent(new Medication(mTitle, mInstructions, mDateLow, mDateHigh, mStatus,
				        mManufacturerCode, mManufacturerCodeSystem, mManufacturerCodeSystemName, mAdministeredType,
				        mAdministeredMethod, mAdministeredFreq, mDosageQuantity));
			}
		}
	}

	private void parsePatientGeneralInfo(final Node root, final PatientBuilder patient) {
		Node node = XMLParserUtil.getNode("patientRole", root.getChildNodes());
		Node dataNode = null;
		String data = "";

		patIdBuilder = new IdentifierBuilder();

		// Finding the id node
		dataNode = XMLParserUtil.getNode("id", node.getChildNodes());
		data = XMLParserUtil.getNodeAttr("extension", dataNode);
		patIdBuilder.setSsn(data);

		dataNode = XMLParserUtil.getNode("addr", node.getChildNodes());
		patIdBuilder.setAddress(getAddressFromNode(dataNode));

		dataNode = XMLParserUtil.getNode("telecom", node.getChildNodes());
		patIdBuilder.setTel(getTelephoneFromNode(dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("name", dataNode.getChildNodes());
		data = XMLParserUtil.getNodeValue("given", dataNode.getChildNodes());

		patIdBuilder.setFirstName(data);
		data = XMLParserUtil.getNodeValue("family", dataNode.getChildNodes());

		patIdBuilder.setLastName(data);
		patIdBuilder.setEmail("N/A");

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("administrativeGenderCode", dataNode.getChildNodes());
		patient.setGender(getGenderFromNote(dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("birthTime", dataNode.getChildNodes());
		patient.setBirthTime(XMLParserUtil.getNodeAttr("value", dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("maritalStatusCode", dataNode.getChildNodes());
		patient.setMaritalStatus(getMaritalStatus(dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("religiousAffiliationCode", dataNode.getChildNodes());
		patient.setReligion(XMLParserUtil.getNodeAttr("displayName", dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("raceCode", dataNode.getChildNodes());
		patient.setRace(XMLParserUtil.getNodeAttr("displayName", dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("ethnicGroupCode", dataNode.getChildNodes());
		patient.setEthnicGroup(XMLParserUtil.getNodeAttr("displayName", dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("languageCommunication", dataNode.getChildNodes());
		patient.setLanguages(getLanguagesFromNode(dataNode));

		patient.setId(patIdBuilder.build());
	}

	private ArrayList<Language> getLanguagesFromNode(final Node node) {
		ArrayList<Language> languages = new ArrayList<Language>();

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node lang = node.getChildNodes().item(i);
			if (lang.getNodeName().equals("languageCommunication")) {
				String data = XMLParserUtil.getNodeAttr("code", lang);

				Language newLang = new Language(data);
				languages.add(newLang);
			}
		}

		return languages;
	}

	private Marital.STATUS getMaritalStatus(final Node node) {
		Marital.STATUS status;

		String statusStr = XMLParserUtil.getNodeAttr("displayName", node);

		status = Marital.STATUS.fromTitle(statusStr);

		return status;
	}

	private Gender getGenderFromNote(final Node node) {
		Gender mGender;
		String genderCode;
		String gender;

		genderCode = XMLParserUtil.getNodeAttr("code", node);
		gender = XMLParserUtil.getNodeAttr("displayName", node);

		mGender = new Gender(gender, genderCode);

		return mGender;
	}

	private Address getAddressFromNode(final Node addrNode) {
		String street = "";
		String city = "";
		String state = "";
		String postal = "";
		String country = "";

		street = XMLParserUtil.getNodeValue("streetAddressLine", addrNode.getChildNodes());
		city = XMLParserUtil.getNodeValue("city", addrNode.getChildNodes());
		state = XMLParserUtil.getNodeValue("state", addrNode.getChildNodes());
		postal = XMLParserUtil.getNodeValue("postalCode", addrNode.getChildNodes());
		country = XMLParserUtil.getNodeValue("country", addrNode.getChildNodes());

		Address addr = new Address(street, city, state, postal, country);

		return addr;
	}

	private Telephone getTelephoneFromNode(final Node telNode) {
		String areaCode = "";
		String firstPart = "";
		String secondPart = "";

		String data = XMLParserUtil.getNodeAttr("value", telNode);

		areaCode = data.substring(data.indexOf("+") + 3, data.lastIndexOf(")"));
		firstPart = data.substring(data.lastIndexOf(")") + 1, data.lastIndexOf(")") + 4);
		secondPart = data.substring(data.length() - 4);

		Telephone phone = new Telephone(areaCode, firstPart, secondPart);

		return phone;
	}
}
