package com.mobile.nuesoft;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.widget.RelativeLayout;

import com.mobile.nuesoft.patient.PatientFragment;



public class MainActivity extends FragmentActivity {
	
	private RelativeLayout mainContainer;
	
	private DrawerLayout navDrawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		navDrawer = (DrawerLayout) findViewById(R.id.nav_drawer);
		mainContainer = (RelativeLayout) findViewById(R.id.content_frame);
		
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onStart() {
		super.onStart();
	}
    
	@Override
	public void onRestart() {
		super.onRestart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	private void init() {
		navDrawer.setScrimColor(Color.parseColor("#CC000000"));
		
		Fragment frag = new PatientFragment();
		this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, frag, PatientFragment.TAG).addToBackStack(PatientFragment.TAG).commit();
		
		frag = new NavigationFragment();
		this.getSupportFragmentManager().beginTransaction().replace(R.id.left_drawer, frag, PatientFragment.TAG).addToBackStack(NavigationFragment.TAG).commit();
	}
}
