package com.mobile.nuesoft.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.AsyncTask;
import android.os.Bundle;

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
	protected PatientObj doInBackground(String... arg0) {
		long runningTime = 0;
		
		PatientBuilder patBuilder = new PatientBuilder();
		IdentifierBuilder patIdBuilder = new IdentifierBuilder();
		List<Language> languages = new ArrayList<Language>();
		List<MedicalEncounter> medicalEncounters = new ArrayList<MedicalEncounter>();
		List<Medication> medicationCurrent = new ArrayList<Medication>();
		List<Medication> medicationPrevious = new ArrayList<Medication>();
		List<PatientTest> tests = new ArrayList<PatientTest>();
		
		while(runningTime < 10000) {
		try {
			Thread.sleep(1000);
			runningTime += 1000;
			
			patBuilder.setEthnicGroup("White American");
			patBuilder.setFirstName("Nicholas");
			patBuilder.setLastName(new Integer(new Random().nextInt(999) + 1).toString());
			patBuilder.setGender(new Gender("Male", "00"));
			patBuilder.setLanguages(languages);
			patBuilder.setMaritalStatus(Marital.STATUS.SINGLE);
			patBuilder.setMedicalEncounters(medicalEncounters);
			patBuilder.setMedicationCurrent(medicationCurrent);
			patBuilder.setMedicationPrevious(medicationPrevious);
			patBuilder.setRace("American");
			patBuilder.setTests(tests);
			patBuilder.setId(patIdBuilder.build());
			
			this.onProgressUpdate(patBuilder.build());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		return null;
	}

}
