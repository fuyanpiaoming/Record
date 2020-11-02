package com.once.test.sample;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;

import com.once.test.activity.TestCustomProviderActivity;
import com.once.test.activity.TestDrawLayoutActivity;
import com.once.test.activity.TestFragmentActivity;
import com.once.test.activity.TestFragmentDynamicActivity;
import com.once.test.activity.TestHandlerActivity;
import com.once.test.activity.TestLifeCycleOneActivity;
import com.once.test.activity.TestListActivity;
import com.once.test.activity.TestNotificationActivity;
import com.once.test.activity.TestPermissionActivity;
import com.once.test.activity.TestRecycleViewActivity;
import com.once.test.activity.TestServiceActivity;
import com.once.test.activity.TestSqlActivity;
import com.once.test.activity.TestToolbarActivity;
import com.once.test.activity.TestViewPageActivity;
import com.once.test.eventbus.TestEventBus2Activity;
import com.once.test.eventbus.TestEventBusActivity;
import com.once.test.math.TestCanvasActivity;
import com.once.test.math.TestMatrixActivity;
import com.once.test.math.TestXfmodeActivity;
import com.once.test.network.TestHttpUrlActivity;
import com.once.test.network.TestOkhttpActivity;
import com.once.test.network.TestVolley;
import com.once.test.provider.TestContactProviderActivity;
import com.once.test.store.TestSqlOpenHelper;
import com.once.test.testhread.TestThreadPoolActivity;
import com.once.test.testretrofit.TestRetrofitActivity;


public class TestItemClass {

    private HashMap<String, Class<? extends Activity>> testItemMap;

    public ArrayList<String> itemNames;
    public ArrayList<Class> itemClasses;


    public TestItemClass() {

        itemNames = new ArrayList<String>();
        itemClasses = new ArrayList<Class>();
        loadItem();
    }

    public void loadItem() {
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

        itemNames.add("SqlHelper");
        itemClasses.add(TestSqlActivity.class);

        itemNames.add("Permission");
        itemClasses.add(TestPermissionActivity.class);

        itemNames.add("Provider");
        itemClasses.add(TestCustomProviderActivity.class);

        itemNames.add("Service");
        itemClasses.add(TestServiceActivity.class);

        itemNames.add("Notification");
        itemClasses.add(TestNotificationActivity.class);

        itemNames.add("Toolbar");
        itemClasses.add(TestToolbarActivity.class);

        itemNames.add("DrawLayout");
        itemClasses.add(TestDrawLayoutActivity.class);

        itemNames.add("Fragment");
        itemClasses.add(TestFragmentActivity.class);

        itemNames.add("DynamicFragment");
        itemClasses.add(TestFragmentDynamicActivity.class);

        itemNames.add("ListView");
        itemClasses.add(TestListActivity.class);

        itemNames.add("RecycleView");
        itemClasses.add(TestRecycleViewActivity.class);

        itemNames.add("ViewPager");
        itemClasses.add(TestViewPageActivity.class);

        itemNames.add("LifeCycleActivity");
        itemClasses.add(TestLifeCycleOneActivity.class);

        itemNames.add("Handler");
        itemClasses.add(TestHandlerActivity.class);
    }


}
