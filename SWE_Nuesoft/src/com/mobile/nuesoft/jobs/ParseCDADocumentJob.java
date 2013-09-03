package com.mobile.nuesoft.jobs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.mobile.nuesoft.patient.Address;
import com.mobile.nuesoft.patient.Gender;
import com.mobile.nuesoft.patient.IdentifierBuilder;
import com.mobile.nuesoft.patient.Language;
import com.mobile.nuesoft.patient.Marital;
import com.mobile.nuesoft.patient.MedicalEncounter;
import com.mobile.nuesoft.patient.Medication;
import com.mobile.nuesoft.patient.PatientBuilder;
import com.mobile.nuesoft.patient.PatientBuilder.PatientObj;
import com.mobile.nuesoft.patient.PatientTest;
import com.mobile.nuesoft.patient.Telephone;
import com.mobile.nuesoft.util.XMLParserUtil;

public class ParseCDADocumentJob extends
        AsyncTask<String, PatientObj, PatientObj> {

	public static final String TAG = "ParseCDADocumentJob";

	public static final String IS_FINISHED_KEY = "com.mobile.nuesoft.job.FINISHED_KEY";

	private Bundle updateBundle;

	public ParseCDADocumentJob() {
	}

	@Override
	protected void onPostExecute(PatientObj result) {
		super.onPostExecute(result);

		if (result != null) {
			updateBundle = new Bundle();
			updateBundle.putBoolean(ParseCDADocumentJob.IS_FINISHED_KEY, true);
			updateBundle.putSerializable(PatientUpdateEvent.PATIENT_OBJ_KEY,
			        result);

			Log.d(TAG, "On Post Execute for PATIENT: " + result);

			PatientUpdateEvent.broadcast(Nuesoft.getReference(), updateBundle);
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(PatientObj... values) {
		super.onProgressUpdate(values);
		PatientObj updatedPatient = values[0];

		updateBundle = new Bundle();
		updateBundle.putBoolean(ParseCDADocumentJob.IS_FINISHED_KEY, false);
		updateBundle.putSerializable(PatientUpdateEvent.PATIENT_OBJ_KEY,
		        updatedPatient);

		PatientUpdateEvent.broadcast(Nuesoft.getReference(), updateBundle);
	}

	@Override
	protected PatientObj doInBackground(String... docPath) {
		long runningTime = 0;

		PatientObj patientObj = null;

		IdentifierBuilder patIdBuilder = new IdentifierBuilder();
		List<Language> languages = new ArrayList<Language>();
		List<MedicalEncounter> medicalEncounters = new ArrayList<MedicalEncounter>();
		List<Medication> medicationCurrent = new ArrayList<Medication>();
		List<Medication> medicationPrevious = new ArrayList<Medication>();
		List<PatientTest> tests = new ArrayList<PatientTest>();

		try {
			patientObj = parseDocument(new File(
			        "storage/sdcard0/Download/sample_cda_file.xml"));

			Log.d(TAG, "GOT PATIENT: " + patientObj);
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

	private PatientObj parseDocument(final File mFile) throws SAXException,
	        IOException, ParserConfigurationException {
		PatientBuilder patBuilder = new PatientBuilder();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(mFile);

		NodeList rootList = doc.getElementsByTagName("ClinicalDocument");
		Node root = XMLParserUtil.getNode("ClinicalDocument", rootList);
		Node record = XMLParserUtil.getNode("recordTarget",
		        root.getChildNodes());

		parsePatientGeneralInfo(record, patBuilder);

		return patBuilder.build();
	}
	
	private void parsePatientAllergies(final Node root, final PatientBuilder patient) {
		
	}

	private void parsePatientGeneralInfo(final Node root,
	        final PatientBuilder patient) {
		IdentifierBuilder identifier = new IdentifierBuilder();
		Node node = XMLParserUtil.getNode("patientRole", root.getChildNodes());
		Node dataNode = null;

		String data = "";

		// Finding the id node
		dataNode = XMLParserUtil.getNode("id", node.getChildNodes());
		data = XMLParserUtil.getNodeAttr("extension", dataNode);
		identifier.setSsn(data);

		dataNode = XMLParserUtil.getNode("addr", node.getChildNodes());
		identifier.setAddress(getAddressFromNode(dataNode));

		dataNode = XMLParserUtil.getNode("telecom", node.getChildNodes());
		identifier.setTel(getTelephoneFromNode(dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("name", dataNode.getChildNodes());
		data = XMLParserUtil.getNodeValue("given", dataNode.getChildNodes());

		Log.d(TAG, "GOT FIRST NAME: " + data);

		identifier.setFirstName(data);
		data = XMLParserUtil.getNodeValue("family", dataNode.getChildNodes());

		Log.d(TAG, "GOT LAST NAME: " + data);

		identifier.setLastName(data);
		identifier.setEmail("N/A");

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("administrativeGenderCode",
		        dataNode.getChildNodes());
		patient.setGender(getGenderFromNote(dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("birthTime", dataNode.getChildNodes());
		patient.setBirthTime(XMLParserUtil.getNodeAttr("value", dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("maritalStatusCode",
		        dataNode.getChildNodes());
		patient.setMaritalStatus(getMaritalStatus(dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("religiousAffiliationCode",
		        dataNode.getChildNodes());
		patient.setReligion(XMLParserUtil.getNodeAttr("displayName", dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("raceCode", dataNode.getChildNodes());
		patient.setRace(XMLParserUtil.getNodeAttr("displayName", dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("ethnicGroupCode",
		        dataNode.getChildNodes());
		patient.setEthnicGroup(XMLParserUtil.getNodeAttr("displayName",
		        dataNode));

		dataNode = XMLParserUtil.getNode("patient", node.getChildNodes());
		dataNode = XMLParserUtil.getNode("languageCommunication",
		        dataNode.getChildNodes());
		patient.setLanguages(getLanguagesFromNode(dataNode));

		patient.setId(identifier.build());
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

		Log.d(TAG, "GOT MARITAL STATUS: " + status);

		return status;
	}

	private Gender getGenderFromNote(final Node node) {
		Gender mGender;
		String genderCode;
		String gender;

		genderCode = XMLParserUtil.getNodeAttr("code", node);
		gender = XMLParserUtil.getNodeAttr("displayName", node);

		mGender = new Gender(gender, genderCode);

		Log.d(TAG, "GOT GENDER: " + mGender.toString());

		return mGender;
	}

	private Address getAddressFromNode(final Node addrNode) {
		String street = "";
		String city = "";
		String state = "";
		String postal = "";
		String country = "";

		street = XMLParserUtil.getNodeValue("streetAddressLine",
		        addrNode.getChildNodes());
		city = XMLParserUtil.getNodeValue("city", addrNode.getChildNodes());
		state = XMLParserUtil.getNodeValue("state", addrNode.getChildNodes());
		postal = XMLParserUtil.getNodeValue("postalCode",
		        addrNode.getChildNodes());
		country = XMLParserUtil.getNodeValue("country",
		        addrNode.getChildNodes());

		Address addr = new Address(street, city, state, postal, country);

		Log.d(TAG, "GOT ADDRESS: " + addr);

		return addr;
	}

	private Telephone getTelephoneFromNode(final Node telNode) {
		String areaCode = "";
		String firstPart = "";
		String secondPart = "";

		String data = XMLParserUtil.getNodeAttr("value", telNode);

		areaCode = data.substring(data.indexOf("+") + 3, data.lastIndexOf(")"));
		firstPart = data.substring(data.lastIndexOf(")") + 1,
		        data.lastIndexOf(")") + 4);
		secondPart = data.substring(data.length() - 4);

		Telephone phone = new Telephone(areaCode, firstPart, secondPart);

		Log.d(TAG, "GOT TELELPHONE: " + phone.toString());

		return phone;
	}
}
