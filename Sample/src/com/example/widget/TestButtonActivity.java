package com.example.widget;

import com.example.sample.R;//引入该包，否则R找不到

import android.app.Activity;
import android.os.Bundle;

public class TestButtonActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_button);
	}

}
