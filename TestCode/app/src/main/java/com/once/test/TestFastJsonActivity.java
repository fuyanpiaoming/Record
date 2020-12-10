package com.once.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestFastJsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fast_json);

        JSONObject jsonObject = new JSONObject();
        JSON json = new JSON() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        JSONArray jsonArray = new JSONArray();
    }
}