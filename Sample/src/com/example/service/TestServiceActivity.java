package com.example.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.sample.R;

public class TestServiceActivity extends Activity{
	
	private Button btnStart;
	private Button btnStop;
	
	@Override
	public void onCreate(Bundle savedInStanceState){
		super.onCreate(savedInStanceState);
		setContentView(R.layout.test_service);
		
		btnStart = (Button)findViewById(R.id.test_service_btn1);
		btnStop = (Button)findViewById(R.id.test_service_btn2);
		
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(TestServiceActivity.this, TestService.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startService(intent);
				
			}
		});
		
		btnStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TestServiceActivity.this, TestService.class);
				stopService(intent);
				
			}
		});
	}

}
