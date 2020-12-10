package com.once.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.once.test.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient okHttpClient = new OkHttpClient();
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.connectTimeout(10, TimeUnit.SECONDS);
                    builder.readTimeout(10,TimeUnit.SECONDS);
                    builder.writeTimeout(10,TimeUnit.SECONDS);
                    Request request = new Request.Builder()
                            .url("https://www.runoob.com/")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.body() != null){
                        String data = response.body().string();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}