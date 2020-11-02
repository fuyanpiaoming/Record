package com.once.test.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.once.test.R;

import java.lang.ref.WeakReference;

public class TestHandlerActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int MSG_SEND = 1;
    private static final int MSG_RECEIVE = 2;
    private TextView textView;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);

        myHandler = new MyHandler(this);

        findViewById(R.id.btn_send).setOnClickListener(this);
        textView = findViewById(R.id.tv_show_message);

        new LooperThread().start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                myHandler.sendEmptyMessage(MSG_SEND);
                break;
            default:
                break;
        }
    }

    class MyHandler extends Handler{
        private WeakReference<Activity>activity;
        public MyHandler(Context context) {
            super();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_SEND:
                    break;
                case MSG_RECEIVE:
                    break;
            }
        }
    }

    static class LooperThread extends Thread{
        public Handler mHandler;

        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                }
            };
            Looper.loop();
        }
    }
}