package com.example.sample;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;

import com.example.provider.TestContactProviderActivity;
import com.example.service.TestPowerManagerActiivty;
import com.example.service.TestServiceActivity;
import com.example.service.TestStorageActivity;
import com.example.service.TestTelephonyActivity;
import com.example.widget.TestButtonActivity;
import com.example.widget.TestDialogActivity;
import com.example.widget.TestTextViewActivity;

public class TestItemClass {
	
	private HashMap<String, Class< ? extends Activity>> testItemMap;
	
	public  ArrayList<String>itemNames;
	public  ArrayList<Class> itemClasses;
	
	
	
	public TestItemClass(){
		
		itemNames = new ArrayList<String>();
		itemClasses = new ArrayList<Class>();
		loadItem();
	}
	
	public void loadItem(){
		
		itemNames.add("TestButton");
		itemClasses.add(TestButtonActivity.class);
		
		itemNames.add("TestTextView");
		itemClasses.add(TestTextViewActivity.class);
		
		itemNames.add("TestPower");
		itemClasses.add(TestPowerManagerActiivty.class);
		
		itemNames.add("TestDialog");
		itemClasses.add(TestDialogActivity.class);
		
		itemNames.add("TestContact");
		itemClasses.add(TestContactProviderActivity.class);
		
		itemNames.add("TestService");
		itemClasses.add(TestServiceActivity.class);
		
		itemNames.add("TestTelephony");
		itemClasses.add(TestTelephonyActivity.class);
		
		itemNames.add("TestStorage");
		itemClasses.add(TestStorageActivity.class);
		
	}
	



}
