package com.once.test.sample;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;

import com.once.test.provider.TestContactProviderActivity;


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
		itemNames.add("TestContact");
		itemClasses.add(TestContactProviderActivity.class);
	}
	



}
