package com.once.image.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


import androidx.appcompat.widget.AppCompatButton;

public class TouchView extends AppCompatButton {

    private final String TAG = TouchView.class.getSimpleName() + "-lzj";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.w(TAG,"dispatchTouchEvent-->start:event=" + event.getAction());
        boolean result = super.dispatchTouchEvent(event);
        Log.w(TAG,"dispatchTouchEvent-->end:result=" + result);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.w(TAG,"onTouchEvent-->start:event=" + event.getAction());
        boolean result = super.onTouchEvent(event);
        Log.w(TAG,"onTouchEvent-->end:result=" + result);
        return result;
    }
}
