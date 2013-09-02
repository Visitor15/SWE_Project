package com.mobile.nuesoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.neusoft.adapters.ListViewAdapter;
import com.mobile.nuesoft.jobs.ParseCDADocumentJob;
import com.mobile.nuesoft.jobs.PatientUpdateEvent;
import com.mobile.nuesoft.patient.OnPatientObjUpdated;
import com.mobile.nuesoft.patient.PatientBuilder.PatientObj;
import com.mobile.nuesoft.ui.NuesoftBroadcastReceiver;

public class NavigationFragment extends NuesoftFragment  implements OnPatientObjUpdated {

	public static final String TAG = "NavigationFragment";

	private TextView mTitleName;

	private ListView mListview;

	private ListViewAdapter mAdapter;

	private OnPatientUpdatedListener patientUpdatedListener = new OnPatientUpdatedListener();

	public NavigationFragment() {

	}

	@Override
	public void onFragmentCreated(Bundle savedInstanceState) {
		HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
		ArrayList<String> contentData = new ArrayList<String>();

		contentData.addAll(Arrays.asList(getActivity().getResources().getStringArray(R.array.document_categories)));
		data.put(getActivity().getResources().getString(R.string.current_referral), (ArrayList<String>) contentData.clone());

		contentData = new ArrayList<String>();
		contentData.addAll(Arrays.asList(getActivity().getResources().getStringArray(R.array.profile_categories)));
		data.put(getActivity().getResources().getString(R.string.patient_records), (ArrayList<String>) contentData.clone());

		mAdapter = new ListViewAdapter(getActivity(), data);
	}

	@Override
	public void onFragmentPaused() {
		patientUpdatedListener.unregister();
	}

	@Override
	public void onFragmentResume() {
		patientUpdatedListener.register();
	}

	@Override
	public void onSave(Bundle outState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFragmentStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public View onFragmentCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.navigation_frag_layout, container, false);

		mTitleName = (TextView) v.findViewById(R.id.nt_title_text);
		mListview = (ListView) v.findViewById(R.id.list_view);
		mListview.setAdapter(mAdapter);

		return v;
	}

	@Override
	public void onFragmentViewCreated(View v, Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	public class OnPatientUpdatedListener extends NuesoftBroadcastReceiver {
		void register() {
			final IntentFilter filter = PatientUpdateEvent.createFilter();
			registerLocalReceiver(Nuesoft.getReference(), this, filter);
		}

		void unregister() {
			unregisterLocalReciever(Nuesoft.getReference(), this);
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			onPatientObjUpdated(intent.getExtras());
		}
	}

	@Override
	public void onPatientObjUpdated(Bundle b) {
		if(b != null) {
			if(b.containsKey(PatientUpdateEvent.PATIENT_OBJ_KEY)) {
				if(b.containsKey(ParseCDADocumentJob.IS_FINISHED_KEY)) {
					if(!(b.getBoolean(ParseCDADocumentJob.IS_FINISHED_KEY))) {
						PatientObj mPatient = (PatientObj) b.getSerializable(PatientUpdateEvent.PATIENT_OBJ_KEY);
						mTitleName.setText(mPatient.getFIRST_NAME() + " " + mPatient.getLAST_NAME());
						return;
					}
				}
			}
		}

		Log.d(TAG, "JOB FINISHED");
	}
}
