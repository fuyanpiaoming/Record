package com.once.test.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.once.test.R;

import java.lang.ref.WeakReference;

public class TestHandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = TestHandlerActivity.class.getSimpleName();

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
        findViewById(R.id.btn_receive).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);

        textView = findViewById(R.id.tv_show_message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                myHandler.sendEmptyMessage(MSG_SEND);
                break;
            case R.id.btn_receive:
                Message msg = new Message();
                msg.what = MSG_RECEIVE;
                msg.arg1 = 100;
                msg.arg2 = 200;
                myHandler.sendMessage(msg);
                break;
            case R.id.btn_post:
                myHandler.post(runnable);
                break;
            default:
                break;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try{
                Thread.sleep(2000);
                Log.i(TAG,"Runnable run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    //静态内部类没有持有外部类的引用，可以避免内存泄漏。
    //使用弱引用也可以避免内存泄漏
    static class MyHandler extends Handler {
        private WeakReference<TestHandlerActivity> weakReference;

        public MyHandler(TestHandlerActivity testHandlerActivity) {
            weakReference = new WeakReference<>(testHandlerActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            TestHandlerActivity testHandlerActivity = weakReference.get();
            switch (msg.what) {
                case MSG_SEND:
                    testHandlerActivity.textView.setText("Send message ok!!!");
                    break;
                case MSG_RECEIVE:
                    testHandlerActivity.textView.setText("receive message msg.arg1=" + msg.arg1 + ",ms.arg2=" + msg.arg2);
                    break;
            }
        }
    }

    //在子线程中实现Handler需要按如下方式添加
    static class LooperThread extends Thread {
        public Handler mHandler;

        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                }
            };
            Looper.loop();
        }
    }
}