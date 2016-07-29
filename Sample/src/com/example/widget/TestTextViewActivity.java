package com.example.widget;

import com.example.sample.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;



public class TestTextViewActivity extends Activity{
	
	private TextView textView;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_textview);
		
		textView = (TextView)findViewById(R.id.test_textview);
		
		int result = 0x00000001;
		Log.i("lzjun","result="+result);
		int jie1 = ~result;
		Log.i("lzjun","jie1="+jie1);
		result &= jie1;
		Log.i("lzjun","result="+result);
	}

}
