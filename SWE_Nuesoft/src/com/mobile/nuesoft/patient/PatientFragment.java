package com.mobile.nuesoft.patient;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.mobile.neusoft.adapters.GridViewAdapter;
import com.mobile.nuesoft.NuesoftFragment;
import com.mobile.nuesoft.R;

public class PatientFragment extends NuesoftFragment {
	
	public static final String TAG = "PatientFragment";
	
	public static final int NUM_OF_CARDS = 5;

	private ViewPager mPager;

	private ScreenSlidePagerAdapter mPagerAdapter;
	
	private SlidingDrawer mDrawer;
	
	private GridView mDrawerGridView;

	public PatientFragment() {
		
	}

	@Override
	public void onFragmentCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
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
		View v = inflater.inflate(R.layout.profile_frag_layout, null);
		
		// Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) v.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager(), getActivity());
        mPager.setAdapter(mPagerAdapter);
        
        mDrawer = (SlidingDrawer) v.findViewById(R.id.drawer);
        mDrawerGridView = (GridView) mDrawer.findViewById(R.id.content);
        initDrawer();
		
		return v;
	}

	@Override
	public void onFragmentViewCreated(View v, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}
	
	private void initDrawer() {
		mDrawerGridView.setAdapter(new GridViewAdapter(getActivity().getApplicationContext()));
	}
	
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
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment(categoryList.get(position));
        }

        @Override
        public int getCount() {
        	return categoryList.size();
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
}
