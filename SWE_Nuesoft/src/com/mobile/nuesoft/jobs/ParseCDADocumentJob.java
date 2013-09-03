package com.mobile.nuesoft.jobs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.mobile.nuesoft.Nuesoft;
import com.mobile.nuesoft.patient.Gender;
import com.mobile.nuesoft.patient.IdentifierBuilder;
import com.mobile.nuesoft.patient.Marital;
import com.mobile.nuesoft.patient.MedicalEncounter;
import com.mobile.nuesoft.patient.Medication;
import com.mobile.nuesoft.patient.PatientBuilder;
import com.mobile.nuesoft.patient.PatientBuilder.Language;
import com.mobile.nuesoft.patient.PatientBuilder.PatientObj;
import com.mobile.nuesoft.patient.PatientTest;

public class ParseCDADocumentJob extends AsyncTask<String, PatientObj, PatientObj> {

	public static final String TAG = "ParseCDADocumentJob";

	public static final String IS_FINISHED_KEY = "com.mobile.nuesoft.job.FINISHED_KEY";

	private Bundle updateBundle;

	public ParseCDADocumentJob() {}

	@Override
	protected void onPostExecute(PatientObj result) {
		super.onPostExecute(result);

		updateBundle = new Bundle();
		updateBundle.putBoolean(ParseCDADocumentJob.IS_FINISHED_KEY, true);
		updateBundle.putSerializable(PatientUpdateEvent.PATIENT_OBJ_KEY, result);

		PatientUpdateEvent.broadcast(Nuesoft.getReference(),  updateBundle);
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
		updateBundle.putSerializable(PatientUpdateEvent.PATIENT_OBJ_KEY, updatedPatient);

		PatientUpdateEvent.broadcast(Nuesoft.getReference(), updateBundle);
	}

	@Override
	protected PatientObj doInBackground(String... docPath) {
		long runningTime = 0;

		PatientBuilder patBuilder = new PatientBuilder();
		IdentifierBuilder patIdBuilder = new IdentifierBuilder();
		List<Language> languages = new ArrayList<Language>();
		List<MedicalEncounter> medicalEncounters = new ArrayList<MedicalEncounter>();
		List<Medication> medicationCurrent = new ArrayList<Medication>();
		List<Medication> medicationPrevious = new ArrayList<Medication>();
		List<PatientTest> tests = new ArrayList<PatientTest>();

		//		while(runningTime < 2000) {
		//			try {
		//				Thread.sleep(250);
		//				runningTime += 250;
		//
		//				patBuilder.setEthnicGroup("White American");
		//				patBuilder.setFirstName("Nicholas");
		//				patBuilder.setLastName(new Integer(new Random().nextInt(999) + 1).toString());
		//				patBuilder.setGender(new Gender("Male", "00"));
		//				patBuilder.setLanguages(languages);
		//				patBuilder.setMaritalStatus(Marital.STATUS.SINGLE);
		//				patBuilder.setMedicalEncounters(medicalEncounters);
		//				patBuilder.setMedicationCurrent(medicationCurrent);
		//				patBuilder.setMedicationPrevious(medicationPrevious);
		//				patBuilder.setRace("American");
		//				patBuilder.setTests(tests);
		//				patBuilder.setId(patIdBuilder.build());
		//
		//				this.onProgressUpdate(patBuilder.build());
		//			} catch (InterruptedException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//		}



		File f = new File("");

		try {
			parseDocument(new File("storage/sdcard0/Download/sample_cda_file.xml"));
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

		return null;
	}

	private void parseDocument(final File mFile) throws SAXException, IOException, ParserConfigurationException {

		Log.d(TAG, "FILE EXIST: " + mFile.exists());
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(mFile);

		Log.d(TAG, "NCC - DOC: " + doc.getElementsByTagName("recordTarget").getLength());

		doc.getDocumentElement().normalize();

		Log.d(TAG, "THINGS: " + doc.getChildNodes());

		printNodes(doc.getChildNodes());

		NodeList nList = doc.getElementsByTagName("recordTarget");

		for(int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			Log.d(TAG, "NODE: " + node.getNodeName() + " TYPE: " + node.getNodeType());

			NodeList children = node.getChildNodes();
			for(int j = 0; j < children.getLength(); j++) {
				Log.d(TAG, "NODE 2: " + children.item(i).getLocalName());
			}
		}
	}

	private void printNodes(final NodeList nodeList) {
		ArrayList<String> attrNames = new ArrayList<String>();
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node tempNode = nodeList.item(i);
			//			Log.d(TAG, tempNode.getNodeName() + " HAS ATTR: " + tempNode.hasAttributes());
			if (tempNode.getNodeName().equalsIgnoreCase("recordTarget")) {
				// get attributes names and values
//				NamedNodeMap nodeMap = tempNode.getAttributes();
				Log.d(TAG, "attr name : " + tempNode.getNodeName());
				Log.d(TAG, "attr value : " + tempNode.getNodeValue());
				Log.d(TAG, "has child nodes: : " + tempNode.hasChildNodes());

				if(tempNode.hasChildNodes()) {
					NodeList children = tempNode.getChildNodes();

					for (int j = 0; j < children.getLength(); j++) {
						Node node = children.item(j);
						Log.d(TAG, "CHILD NODE: " + node.getNodeName());
						Log.d(TAG, "CHILD NODE ATTR: " + node.hasAttributes());
						if(node.getNodeName().equals("patientRole")) {
							NodeList children2 = node.getChildNodes();
							
							for(int k = 0; k < children2.getLength(); k++) {
								Node m = children2.item(k);
								Log.d(TAG, "attr value : " + m.getNodeName());
								Log.d(TAG, "attr value : " + m.getNodeValue());
							}
						}
					}
				}
			}
			if (tempNode.hasChildNodes()) {
				//				loop again if has child nodes
				printNodes(tempNode.getChildNodes());
			}
		}
	}

}
