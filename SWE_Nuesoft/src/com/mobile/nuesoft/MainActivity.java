package com.mobile.nuesoft;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mobile.nuesoft.patient.PatientFragment;
import com.mobile.nuesoft.ui.FragmentCallbackEvent;
import com.mobile.nuesoft.ui.NuesoftBroadcastReceiver;


public class MainActivity extends FragmentActivity {
	
	public static final String TAG = "MainActivity";
	
	private RelativeLayout mainContainer;
	
	private DrawerLayout navDrawer;
	
	private OnFragmentCallbackListener fragCallbackListener = new OnFragmentCallbackListener();

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
		
		fragCallbackListener.register();
	}

	@Override
	public void onPause() {
		super.onPause();
		
		fragCallbackListener.unregister();
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
	
	private void onHandleFragmentCallback(final int actionID) {
		switch(actionID) {
			default: {
				Toast.makeText(getApplicationContext(), "ID is: " + actionID, Toast.LENGTH_LONG).show();
			}
		}
	}
	
	public class OnFragmentCallbackListener extends NuesoftBroadcastReceiver {
		void register() {
			final IntentFilter filter = FragmentCallbackEvent.createFilter();
			registerLocalReceiver(Nuesoft.getReference(), this, filter);
		}

		void unregister() {
			unregisterLocalReciever(Nuesoft.getReference(), this);
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d(TAG, "onReceive HIT");
			onHandleFragmentCallback(intent.getIntExtra(FragmentCallbackEvent.ACTION_KEY, -1));
		}
	}
}
