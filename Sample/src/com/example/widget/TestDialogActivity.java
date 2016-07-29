package com.example.widget;

import com.example.sample.R;//添加这个import语句，否则找不到R

import android.app.Activity;
import android.os.Bundle;

public class TestDialogActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_dialog);
	}


}
