package com.once.test.eventbus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.once.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TestEventBusActivity extends AppCompatActivity {

    private final String TAG = "TestEventBusTag";

    private Button getBtn;
    private TextView showTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus);
        //注册eventbus
        EventBus.getDefault().register(this);
        getBtn = findViewById(R.id.eventbus_show_btn);
        showTxt = findViewById(R.id.eventbus_show_txt);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MessageEvent messageEvent = new MessageEvent();
                //messageEvent.setMessage("Haha!!!");
                //EventBus.getDefault().post(messageEvent);
                Intent intent = new Intent(TestEventBusActivity.this, TestEventBus2Activity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册eventbus
        EventBus.getDefault().unregister(this);
    }

    //订阅MessageEvent事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        String threadname = Thread.currentThread().getName();
        Log.i(TAG,"onMessageEventMain...name=" + threadname);
        showTxt.setText(event.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageEventAsync(MessageEvent event){
        String threadname = Thread.currentThread().getName();
        Log.i(TAG,"onMessageEventAsync...name=" + threadname);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEventBackGround(MessageEvent messageEvent){
        String threadname = Thread.currentThread().getName();
        Log.i(TAG,"onMessageEventBackGround...name=" + threadname);
    }

    //默认的线程模式
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEventPosting(MessageEvent messageEvent){
        String threadname = Thread.currentThread().getName();
        Log.i(TAG,"onMessageEventPosting...name=" + threadname);
    }

}
