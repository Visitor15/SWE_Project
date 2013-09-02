package com.mobile.nuesoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobile.neusoft.adapters.ListViewAdapter;

public class NavigationFragment extends NuesoftFragment {
	
	public static final String TAG = "NavigationFragment";
	
	private ListView mListview;
	
	private ListViewAdapter mAdapter;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFragmentResume() {
		// TODO Auto-generated method stub
		
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
		
		mListview = (ListView) v.findViewById(R.id.list_view);
		mListview.setAdapter(mAdapter);
		
		return v;
	}

	@Override
	public void onFragmentViewCreated(View v, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	
}
