package com.example.service;

import java.io.File;
import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sample.R;

public class TestStorageActivity extends Activity{
	
	private ProgressBar mProgressBar;
	private TextView mShowSizeTxt;
	private Button mBtn;
	private Button mBtnTestParse;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_storage);
		
		mProgressBar = (ProgressBar)findViewById(R.id.test_storage_pb);
	    mShowSizeTxt = (TextView)findViewById(R.id.test_storage_txt);
	    mBtn = (Button)findViewById(R.id.test_storage_btn);
	    mBtnTestParse = (Button)findViewById(R.id.test_storage_btn2);
	    
	    mBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showSize();
				
			}
		});
	    
	    mBtnTestParse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String valueString = "11256";
				int val = Integer.parseInt(valueString);
				mShowSizeTxt.setText("hello" + val);
			}
		});
	}
	
	
	public void showSize(){
		
		mShowSizeTxt.setText("");
		mProgressBar.setProgress(0);
		//Environment.getExternalStorageState()=/storage/emulator/0/
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
		      File path = Environment.getExternalStorageDirectory(); 
		      /* StatFs¿ŽÎÄŒþÏµÍ³¿ÕŒäÊ¹ÓÃ×Ž¿ö */
		      StatFs statFs = new StatFs(path.getPath());
		      /* BlockµÄsize */
		      long blockSize = statFs.getBlockSize(); 
		      /* ×ÜBlockÊýÁ¿ */ 
		      long totalBlocks = statFs.getBlockCount(); 
		      /* ÒÑÊ¹ÓÃµÄBlockÊýÁ¿ */
		      long availableBlocks = statFs.getAvailableBlocks(); 
		      String[] total = fileSize(totalBlocks * blockSize); 
		      String[] available = fileSize(availableBlocks * blockSize);
		      /* getMaxÈ¡µÃÔÚmain.xmlÀïProgressBarÉè¶šµÄ×îŽóÖµ */
		      Log.i("lzjun","total[o]="+total[0]);
		      Log.i("lzjun","available[0]=" + available[0]);
		      
		     // int ss = Integer.parseInt(available[0]) * mProgressBar.getMax() / Integer.parseInt(total[0]); 
		      long ss = availableBlocks * blockSize * mProgressBar.getMax() / (totalBlocks * blockSize);
		      mProgressBar.setProgress((int)ss); 
		      String text = "total:" + total[0] + total[1] + "\n"; text += "avail:" + available[0] + available[1]; 
		      mShowSizeTxt.setText(text); 
		 }else if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)){
			String text = "Sd card removable";
			mShowSizeTxt.setText(text);
		 }
	  }
	
	
	  private String[] fileSize(long size) 
	  {
	    String str = ""; 
	    if (size >= 1024)
	    {
	      str = "KB"; 
	      size /= 1024; 
	      if (size >= 1024)
	      {
	        str = "MB"; 
	        size /= 1024;
	        } 
	      } 
	    DecimalFormat formatter = new DecimalFormat();
	    formatter.setGroupingSize(3); 
	    String result[] = new String[2];
	    result[0] = formatter.format(size);
	    result[1] = str; 

	    return result;

	  } 

}
