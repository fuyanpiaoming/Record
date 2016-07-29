package com.example.service;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.sample.R;

public class TestShowFileActivity extends Activity{
	
	private final String TAG = "TestShowFileActivity";
	
	private Button btnShowFile;
	private Button btnShowSdcard;
	
	private File fileDir;
	private File sdcardDir;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_show_file);
		
		btnShowFile = (Button)findViewById(R.id.test_show_file_btn1);
		btnShowSdcard = (Button)findViewById(R.id.test_show_file_btn2);
		
		fileDir = this.getFilesDir();
		Log.i(TAG,"lzjun...fileDir="+fileDir);
		
		sdcardDir = Environment.getExternalStorageDirectory();
		Log.i(TAG,"lzjun....sdcardDir="+sdcardDir);
		
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)){
			btnShowSdcard.setEnabled(false);
		}
		
		btnShowFile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String path = fileDir.getParent() + java.io.File.separator + fileDir.getName();
				Log.i(TAG, "lzjun..file..path="+path);
				showListActivity(path);
			}
		});
		
		btnShowSdcard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String path = sdcardDir.getParent() + sdcardDir.getName();
				Log.i(TAG, "lzjun..sdcard..path="+path);

				showListActivity(path);
			}
		});
	}
	
	private void showListActivity(String path){
		
		Intent intent = new Intent();
		intent.setClass(TestShowFileActivity.this, TestShowFileActivity_1.class);
		Bundle bundle = new Bundle();
		bundle.putString("path", path);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
