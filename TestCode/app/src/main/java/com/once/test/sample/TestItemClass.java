package com.once.test.sample;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;

import com.once.test.eventbus.TestEventBus2Activity;
import com.once.test.eventbus.TestEventBusActivity;
import com.once.test.network.TestHttpUrlActivity;
import com.once.test.network.TestOkhttpActivity;
import com.once.test.network.TestVolley;
import com.once.test.provider.TestContactProviderActivity;
import com.once.test.testretrofit.TestRetrofitActivity;
import com.once.test.utils.TestOtherActivity;


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

		itemNames.add("TestHttpUrlConnection");
		itemClasses.add(TestHttpUrlActivity.class);

		itemNames.add("TestVolley");
		itemClasses.add(TestVolley.class);

		itemNames.add("TestOkHttp3");
		itemClasses.add(TestOkhttpActivity.class);

		itemNames.add("TestOther");
		itemClasses.add(TestOtherActivity.class);

		itemNames.add("TestRetrofit");
		itemClasses.add(TestRetrofitActivity.class);

		itemNames.add("TestEventBus");
		itemClasses.add(TestEventBusActivity.class);

		itemNames.add("TestEventBus2");
		itemClasses.add(TestEventBus2Activity.class);

	}
	



}
