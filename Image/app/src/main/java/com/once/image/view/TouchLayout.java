package com.once.image.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TouchLayout extends LinearLayout {

    private final String TAG = TouchLayout.class.getSimpleName() + "-lzj";

    public TouchLayout(Context context) {
        super(context);
    }

    public TouchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG,"dispatchTouchEvent-->start:ev=" + ev.getAction());
        boolean result = super.dispatchTouchEvent(ev);
        Log.d(TAG,"dispatchTouchEvent-->end:result=" + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG,"onInterceptTouchEvent-->start:ev=" + ev.getAction());
        boolean result = super.onInterceptTouchEvent(ev);
        Log.d(TAG,"onInterceptTouchEvent-->end:result=" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"onTouchEvent-->start:event=" + event.getAction());
        boolean result = super.onTouchEvent(event);
        Log.d(TAG,"onTouchEvent-->end:result=" + result);
        return result;
    }
}
