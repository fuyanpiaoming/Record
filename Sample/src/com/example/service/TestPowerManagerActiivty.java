package com.example.service;


import com.example.sample.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

public class TestPowerManagerActiivty extends Activity {
	
	private Button btnScreenOn;
	private Button btnScreenOff;
	
	private PowerManager mPowerManager;
	
	private boolean isScreenOn;
	
	private PowerManager.WakeLock mWakeLock;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_power_manager);
		
		mPowerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		mWakeLock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK , "sample.mwakelock");
		
		btnScreenOff = (Button)findViewById(R.id.test_power_off);
		btnScreenOn = (Button)findViewById(R.id.test_power_on);
		
		btnScreenOff.setOnClickListener(listener);
		btnScreenOn.setOnClickListener(listener);
		
		
	}
	
	
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.test_power_off:
				if (!mWakeLock.isHeld()){
					mWakeLock.acquire();
				}
				
				if (mPowerManager.isScreenOn()){
					//mPowerManager.goToSleep();
					mPowerManager.reboot(getPackageName());
				}
				if (mWakeLock.isHeld()){
					mWakeLock.release();
				}			
				break;
			case R.id.test_power_on:
				
				if (!mWakeLock.isHeld()){
					mWakeLock.acquire();
				}
				if (!mPowerManager.isScreenOn()){
					mPowerManager.wakeUp(System.currentTimeMillis());
				}
				
				if (mWakeLock.isHeld()){
					mWakeLock.release();
				}
				break;

			default:
				break;
			}
			
		}
	};
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.i("lzjun","onKeyDown...event="+event);
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
            Toast.makeText(TestPowerManagerActiivty.this, getPackageName(), Toast.LENGTH_SHORT).show();
			
			break;
		case KeyEvent.KEYCODE_BACK:
            Toast.makeText(TestPowerManagerActiivty.this, "Click Back down!!", Toast.LENGTH_SHORT).show();
			
			break;
		case KeyEvent.KEYCODE_HOME:
            Toast.makeText(TestPowerManagerActiivty.this, "Click Home down!!", Toast.LENGTH_SHORT).show();
		
			break;
		default:
			break;
		}
		
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.i("lzjun","onKeyUp...event="+event);
		switch (keyCode){
		case KeyEvent.KEYCODE_MENU:
            Toast.makeText(TestPowerManagerActiivty.this, "Click Menu up!!", Toast.LENGTH_SHORT).show();
	
			break;
		case KeyEvent.KEYCODE_HOME:
            Toast.makeText(TestPowerManagerActiivty.this, "Click Home up!!", Toast.LENGTH_SHORT).show();
			
			break;
		case KeyEvent.KEYCODE_BACK:
            Toast.makeText(TestPowerManagerActiivty.this, "Click Back up!!", Toast.LENGTH_SHORT).show();
			
			break;
		}
		return super.onKeyUp(keyCode, event);
	}	

}
