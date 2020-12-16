package com.once.test.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class CustomView extends View {

    private Paint mPaint;
    private int lastX;
    private int lastY;
    private Scroller scroller;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        //mPaint.setStrokeWidth(2);
        scroller = new Scroller(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 100, 100, mPaint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        //useLayout(event);
        //useLayout1(event);
        //useOffsetFun(event);
        //useLayoutParams(event);
        //useScrollBy(event);
        useScroller(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }

    private void useLayout(MotionEvent event){
        //点击事件相对于view控件左上角的位置坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    private void useLayout1(MotionEvent event){
        //点击事件相对于屏幕左上角的位置坐标
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    private void useOffsetFun(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    private void  useLayoutParams(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                //ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    private void useScrollBy(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
    }

    private void useScroller(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                View view = (View)getParent();
                scroller.startScroll(view.getScrollX(),view.getScrollY(),-view.getScrollX(), -view.getScrollY());
                break;
        }
    }


}
