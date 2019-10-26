package com.once.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service{
	
	private Handler objHandler = new Handler();
	
	private int counter =  0;
	
	private Runnable mTasks = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			counter++;
			Log.i("lzjun","counter="+counter);
			objHandler.postDelayed(mTasks, 1000);
			
		}
	};
	
	@Override
	public void onCreate(){
		objHandler.postDelayed(mTasks, 1000);
	    super.onCreate();	
	}
	
	@Override
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy(){
		objHandler.removeCallbacks(mTasks);
		super.onDestroy();
	}

}
