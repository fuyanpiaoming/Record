package com.once.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.once.test.R;

/**
 * Fragment: [onAttach]
 * Fragment: [onCreate]
 * Fragment: [onCreateView]
 * Fragment: [onViewCreated]
 * Activity: [onCreate]
 * Fragment: [onActivityCreated]
 * Fragment: [onViewStateRestored]
 * Activity: [onStart]
 * Fragment: [onStart]
 * Activity: [onPostCreate]
 * Activity: [onResume]
 * Fragment: [onResume]
 * Activity: [onPostResume]
 * Activity: [onAttachedToWindow]
 * Fragment: [onPause]
 * Activity: [onPause]
 * Fragment: [onStop]
 * Activity: [onStop]
 * Fragment: [onDestroyView]
 * Fragment: [onDestroy]
 * Fragment: [onDetach]
 * Activity: [onDestroy]
 * Activity: [onDetachedFromWindow]
 */

public class TestFragmentActivity extends Activity {

    private final String TAG = TestFragmentActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        Log.i(TAG,"[onCreate]");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"[onRestoreInstanceState]");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG,"[onPostCreate]");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"[onStart]");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"[onRestart]");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"[onResume]");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG,"[onPostResume]");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG,"[onNewIntent]");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"[onSaveInstanceState]");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"[onPause]");
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG,"[onConfigurationChanged]");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG,"[onAttachedToWindow]");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG,"[onDetachedFromWindow]");
    }
}