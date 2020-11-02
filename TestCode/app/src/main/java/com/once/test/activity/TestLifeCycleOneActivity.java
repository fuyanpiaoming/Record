package com.once.test.activity;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.once.test.R;

/**
 * 未配置android:configChanges属性
 * android:configChanges="orientation
 * |screenSize|fontScale|keyboard|keyboardHidden|screenLayout|
 * smallestScreenSize|density|touchscreen|locale|uiMode|
 * colorMode|navigation|mcc|mnc|layoutDirection"/>
 */
public class TestLifeCycleOneActivity extends AppCompatActivity {

    private final String TAG = TestLifeCycleOneActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_life_cycle_one);
        Log.i(TAG,"[onCreate]");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"[onStart]");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"[onRestoreInstanceState]");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"[onResume]");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG,"[onConfigurationChanged]");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"[onPause]");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"[onSaveInstanceState]");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"[onStop]");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"[onDestroy]");
    }


}