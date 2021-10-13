package com.once.image.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.once.image.R;
import com.once.image.view.TouchLayout;
import com.once.image.view.TouchView;

public class TestTouchActivity extends AppCompatActivity {

    private final String TAG = TestTouchActivity.class.getSimpleName() + "-lzj";

    private TouchLayout touchLayout;
    private TouchView touchView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_touch);
        touchLayout = findViewById(R.id.test_touch_layout);
        touchView = findViewById(R.id.test_touch_view);

        //TouchLayout
        touchLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG,"touchLayout-->onTouch event=" + event.getAction());
                return false;
            }
        });

        touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"touchLayout-->onClick");
            }
        });

        //TouchView
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG,"touchView-->onTouch event=" + event.getAction());
                //true 表示touchView消费了这个事件，则不会去执行touchView的onClick事件
                //false 表示没有消费这个事件，会继续执行touchView的onClick事件
                return true;
            }
        });

        touchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"touchView-->onClick");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,"dispatchTouchEvent-->start:ev=" + ev.getAction());
        boolean result = super.dispatchTouchEvent(ev);
        Log.i(TAG,"dispatchTouchEvent-->end:result=" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,"onTouchEvent-->start:ev=" + event.getAction());
        boolean result = super.onTouchEvent(event);
        Log.i(TAG,"onTouchEvent-->end:result=" + result);
        return result;
    }
}
