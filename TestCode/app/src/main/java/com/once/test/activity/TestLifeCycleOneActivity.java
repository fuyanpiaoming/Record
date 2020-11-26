package com.once.test.activity;

import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.once.test.R;
import com.once.test.mode.ParcelableMode;
import com.once.test.mode.SerializableMode;

import java.util.ArrayList;

/**
 * 未配置android:configChanges属性
 * android:configChanges="orientation
 * |screenSize|fontScale|keyboard|keyboardHidden|screenLayout|
 * smallestScreenSize|density|touchscreen|locale|uiMode|
 * colorMode|navigation|mcc|mnc|layoutDirection"/>
 * [onCreate]
 * [onStart]
 * [onResume]
 * [onPause]
 * [onStop]
 * [onSaveInstanceState]
 * [onDestroy]
 * [onCreate]
 * [onStart]
 * [onRestoreInstanceState]
 * [onResume]
 */
public class TestLifeCycleOneActivity extends AppCompatActivity {

    private final String TAG = TestLifeCycleOneActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_life_cycle_one);
        Log.i(TAG,"[onCreate]");
        if(savedInstanceState != null){
            int a = savedInstanceState.getInt("intValue",0);
            byte b = savedInstanceState.getByte("byte");
        }
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
        byte byteValue = 12;
        byte[] byteArr = {1,2,3,4};
        int intValue = 1;
        int[] intArr = {1,2,3,4};
        ArrayList<Integer>intArrayList = new ArrayList<>();
        intArrayList.add(1);
        intArrayList.add(2);
        char charValue = 'q';
        char[] charArr = {'q','b','m'};

        CharSequence charSequence="abc";
        CharSequence[] charSequences ={"abc","efg"};
        ArrayList<CharSequence>charSequenceArrayList = new ArrayList<>();
        charSequenceArrayList.add("egh");
        charSequenceArrayList.add("bcd");

        Bundle bundle = new Bundle();
        bundle.putInt("int",12);

        ArrayList<String>stringArrayList = new ArrayList<>();
        stringArrayList.add("abc");
        stringArrayList.add("ecf");

        outState.putByte("byte",byteValue);
        outState.putByteArray("byte",byteArr);
        outState.putInt("int",intValue);
        outState.putIntArray("intArr",intArr);
        outState.putIntegerArrayList("intArrayList",intArrayList);
        outState.putChar("char",charValue);
        outState.putCharArray("charArr",charArr);
        outState.putAll(new Bundle());
        outState.putBundle("bundle",bundle);
        outState.putCharSequence("charSequence",charSequence);
        outState.putCharSequenceArray("charSequences",charSequences);
        outState.putCharSequenceArrayList("charSequenceArr",charSequenceArrayList);
        outState.putFloat("float",12.3f);
        outState.putFloatArray("floatArr",new float[]{12.3f,13.4f});
        outState.putDouble("double",21.3);
        outState.putDoubleArray("doubleArr",new double[]{12.3,34.5,45.3});
        outState.putString("string","abc");
        outState.putStringArray("stringArr",new String[]{"abc","bcd","efg"});
        outState.putStringArrayList("stringArrList",stringArrayList);
        outState.putParcelable("parcelable",new ParcelableMode());
        outState.putParcelableArray("parcelableArr",new Parcelable[]{});
        //outState.putParcelableArrayList();
        outState.putSerializable("serialable",new SerializableMode());
        outState.putBoolean("boolean",true);
        outState.putBooleanArray("booleanArr",new boolean[]{true,false,true});
        outState.putLong("long",1234);
        outState.putLongArray("longArr",new long[]{12,3,4,45});
        //outState.putSparseParcelableArray();

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