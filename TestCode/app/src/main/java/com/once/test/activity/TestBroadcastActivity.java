package com.once.test.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.once.test.R;
import com.once.test.receiver.MyReceiver;


public class TestBroadcastActivity extends AppCompatActivity implements View.OnClickListener{

    private MyReceiver myReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast);
        findViewById(R.id.btn_normal).setOnClickListener(this);
        findViewById(R.id.btn_order).setOnClickListener(this);
        findViewById(R.id.btn_local).setOnClickListener(this);


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.once.test.say");
        intentFilter.addAction("com.once.test.answer");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver,intentFilter);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_normal:
                sendBroadcast(new Intent("com.once.test.say"));
                break;
            case R.id.btn_order:
                sendOrderedBroadcast(new Intent("com.once.test.answer"),null);
                break;
            case R.id.btn_local:
                localBroadcastManager.sendBroadcast(new Intent("com.once.test.say"));
                break;
        }
    }

    private static class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"Local Broadcast Receive ok",Toast.LENGTH_SHORT).show();
        }
    }
}