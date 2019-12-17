package com.once.test.sample;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;

import com.once.test.eventbus.TestEventBus2Activity;
import com.once.test.eventbus.TestEventBusActivity;
import com.once.test.math.TestCanvasActivity;
import com.once.test.math.TestMatrixActivity;
import com.once.test.math.TestXfmodeActivity;
import com.once.test.network.TestHttpUrlActivity;
import com.once.test.network.TestOkhttpActivity;
import com.once.test.network.TestVolley;
import com.once.test.provider.TestContactProviderActivity;
import com.once.test.testhread.TestThreadPoolActivity;
import com.once.test.testretrofit.TestRetrofitActivity;


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

		itemNames.add("TestRetrofit");
		itemClasses.add(TestRetrofitActivity.class);

		itemNames.add("TestEventBus");
		itemClasses.add(TestEventBusActivity.class);

		itemNames.add("TestEventBus2");
		itemClasses.add(TestEventBus2Activity.class);

		itemNames.add("TestThreadPool");
		itemClasses.add(TestThreadPoolActivity.class);

		itemNames.add("TestMatrix");
		itemClasses.add(TestMatrixActivity.class);

		itemNames.add("TestCanvas");
		itemClasses.add(TestCanvasActivity.class);

		itemNames.add("TestXfmode");
		itemClasses.add(TestXfmodeActivity.class);
	}
	



}
