package com.once.test.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.once.test.R;
import java.util.Set;

public class TestSharedPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shared_preferences);
    }

    private void setValue(String key,Object object){
        SharedPreferences sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (object instanceof Boolean){
            editor.putBoolean(key,(boolean)object);
        }else if(object instanceof Integer){
            editor.putInt(key,(int)object);
        }else if(object instanceof Float){
            editor.putFloat(key,(float)object);
        }else if(object instanceof Long){
            editor.putLong(key,(long)object);
        }else if(object instanceof String){
            editor.putString(key,(String)object);
        }else if(object instanceof Set){
            editor.putStringSet(key,(Set)object);
        }
        editor.apply();
    }

    private Object getValue(String key,Object defValue){
        SharedPreferences sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
        if (defValue instanceof Boolean){
            return sharedPreferences.getBoolean(key,(boolean)defValue);
        }else if(defValue instanceof Integer){
            return sharedPreferences.getInt(key,(int)defValue);
        }else if(defValue instanceof Float){
            return sharedPreferences.getFloat(key,(float)defValue);
        }else if(defValue instanceof Long){
            return sharedPreferences.getLong(key,(long)defValue);
        }else if(defValue instanceof String){
            return sharedPreferences.getString(key,(String)defValue);
        }else if(defValue instanceof Set){
            return sharedPreferences.getStringSet(key,(Set)defValue);
        }
        return null;
    }
}