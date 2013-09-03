package com.mobile.nuesoft.patient;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.nuesoft.Nuesoft;
import com.mobile.nuesoft.NuesoftFragment;
import com.mobile.nuesoft.R;
import com.mobile.nuesoft.jobs.ParseCDADocumentJob;
import com.mobile.nuesoft.jobs.PatientUpdateEvent;
import com.mobile.nuesoft.patient.PatientBuilder.PatientObj;
import com.mobile.nuesoft.ui.ActiveReferralsFragment;
import com.mobile.nuesoft.ui.NuesoftBroadcastReceiver;

public class PatientFragment extends NuesoftFragment implements OnPatientObjUpdated {

	public static final String TAG = "PatientFragment";

	public static final int NUM_OF_CARDS = 5;

	private ViewPager mPager;

	private ScreenSlidePagerAdapter mPagerAdapter;

//	private SlidingDrawer mDrawer;
//
//	private GridView mDrawerGridView;
	
	private TextView mPatientTitleName;
	
	private PatientObj mPatient;
	
	private ParseCDADocumentJob docParseJob;
	
	private boolean shouldParse = true;
	
	private String docPath = "mnt/sdcard0/download";
	
	private LinearLayout activeReferralContainer;
	
	private OnPatientUpdatedListener onPatientUpdatedListener = new OnPatientUpdatedListener();

	public PatientFragment() {}

	@Override
	public void onFragmentCreated(Bundle savedInstanceState) {
		if(docParseJob == null || shouldParse) {
			docParseJob = new ParseCDADocumentJob();
			docParseJob.execute(docPath);
		}
	}

	@Override
	public void onFragmentPaused() {
		onPatientUpdatedListener.unregister();
	}

	@Override
	public void onFragmentResume() {
		onPatientUpdatedListener.register();
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
		View v = inflater.inflate(R.layout.profile_frag_layout, null);
		
		activeReferralContainer = (LinearLayout) v.findViewById(R.id.ll_active_referrals_container);
		mPatientTitleName = (TextView) v.findViewById(R.id.nt_name);

		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) v.findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager(), getActivity());
		mPager.setAdapter(mPagerAdapter);
		
		initActiveReferralFragment();

		return v;
	}

	@Override
	public void onFragmentViewCreated(View v, Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}
	
	private void initActiveReferralFragment() {
		this.getChildFragmentManager().beginTransaction().replace(R.id.ll_active_referrals_container, new ActiveReferralsFragment()).commit();
	}

//	private void initDrawer() {
//		mDrawerGridView.setAdapter(new GridViewAdapter(getActivity().getApplicationContext()));
//	}

	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
	 * sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

		private ArrayList<Fragment> dataList = new ArrayList<Fragment>();
		
		

		private ArrayList<String> categoryList = new ArrayList<String>();

		public ScreenSlidePagerAdapter(final FragmentManager fm, final Context c) {
			super(fm);
			categoryList.add(c.getResources().getString(R.string.patient_summary));

			String[] tempList = c.getResources().getStringArray(R.array.profile_categories);
			for(String s : tempList) {
				categoryList.add(s);
			}
			
			dataList.add(new SummaryFragment());
			dataList.add(new ScreenSlidePageFragment("Allergies"));
			dataList.add(new MedicationFragment());
			dataList.add(new ScreenSlidePageFragment("Family History"));
			dataList.add(new ScreenSlidePageFragment("Patient History"));
		}

		@Override
		public Fragment getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public int getCount() {
			return dataList.size();
		}
	}

	public class ScreenSlidePageFragment extends Fragment {

		private String mTitle = "";

		ScreenSlidePageFragment(final String title) {
			this.mTitle = title;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			ViewGroup rootView = (ViewGroup) inflater.inflate(
					R.layout.profile_card_layout, container, false);

			((TextView) rootView.findViewById(R.id.nt_title)).setText(mTitle);

			return rootView;
		}
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
				mPatient = (PatientObj) b.getSerializable(PatientUpdateEvent.PATIENT_OBJ_KEY);
				
				if(b.containsKey(ParseCDADocumentJob.IS_FINISHED_KEY)) {
					if(!(b.getBoolean(ParseCDADocumentJob.IS_FINISHED_KEY))) {
						Log.d(TAG, "GOT PATIENT: " + mPatient.toString());
						
						mPatientTitleName.setText(mPatient.getFIRST_NAME() + " " + mPatient.getLAST_NAME());
						
						return;
					}
				}
				Log.d(TAG, "JOB FINISHED");
			}
		}
	}
}
