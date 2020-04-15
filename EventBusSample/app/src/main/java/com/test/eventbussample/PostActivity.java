package com.test.eventbussample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "PostTest";

    private Button btnPostMain;
    private Button btnPostSticky;
    private static boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        findViewById(R.id.btn_post_main).setOnClickListener(this);
        findViewById(R.id.btn_post_posting).setOnClickListener(this);
        findViewById(R.id.btn_post_async).setOnClickListener(this);
        findViewById(R.id.btn_post_background).setOnClickListener(this);
        findViewById(R.id.btn_post_sticky).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        isClicked = !isClicked;
        switch (id) {
            case R.id.btn_post_main:
                if (isClicked) {
                    Log.i(TAG, "[onClick]btn_post_main thread1:" + Thread.currentThread().getName());
                    EventBus.getDefault().post(new MessageMain("main1", "in main thread"));
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "[onClick]btn_post_main thread2:" + Thread.currentThread().getName());
                            EventBus.getDefault().post(new MessageMain("main2", "in child thread"));
                        }
                    }).start();
                }
                finish();
                break;
            case R.id.btn_post_sticky:
                if (isClicked) {
                    Log.i(TAG, "[onClick]btn_post_sticky thread1:" + Thread.currentThread().getName());
                    EventBus.getDefault().postSticky(new EventbusMessage(1, "in main thread"));
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "[onClick]btn_post_sticky thread2:" + Thread.currentThread().getName());
                            EventBus.getDefault().postSticky(new EventbusMessage(2, "in sticky thread"));
                        }
                    }).start();
                }
                finish();
                break;
            case R.id.btn_post_posting:
                if (isClicked) {
                    Log.i(TAG, "[onClick]btn_post_posting thread1:" + Thread.currentThread().getName());
                    EventBus.getDefault().post(new MessagePost("post1", "in main thread"));
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "[onClick]btn_post_posting thread2:" + Thread.currentThread().getName());
                            EventBus.getDefault().post(new MessagePost("post2", "in posting thread"));
                        }
                    }).start();
                }
                finish();
                break;
            case R.id.btn_post_background:
                if (isClicked) {
                    Log.i(TAG, "[onClick]btn_post_background thread1:" + Thread.currentThread().getName());
                    EventBus.getDefault().post(new MessageBackground("background1", "in main thread"));
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "[onClick]btn_post_background thread2:" + Thread.currentThread().getName());
                            EventBus.getDefault().post(new MessageBackground("background2", "in background thread"));
                        }
                    }).start();
                }
                finish();
                break;
            case R.id.btn_post_async:
                if (isClicked) {
                    Log.i(TAG, "[onClick]btn_post_async thread1:" + Thread.currentThread().getName());
                    EventBus.getDefault().post(new MessageAync("async1", "in main thread"));
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "[onClick]btn_post_async thread2:" + Thread.currentThread().getName());
                            EventBus.getDefault().post(new MessageAync("async2", "in async thread"));
                        }
                    }).start();
                }
                finish();
                break;
        }
    }
}
