package com.once.test.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class CustomViewGroup extends FrameLayout {

    private final String TAG = CustomViewGroup.class.getSimpleName();
    private ViewDragHelper viewDragHelper;
    private View mainView;
    private View subView;
    private int width;

    public CustomViewGroup(@NonNull Context context) {
        this(context,null);
    }

    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View view, int i) {
                //判断哪个子view可以拖动
                Log.i(TAG,"[tryCaptureView]");
                return mainView == view;
            }

            @Override
            public void onViewDragStateChanged(int state) {
                //子view拖动状态变化时触发
                super.onViewDragStateChanged(state);
                Log.i(TAG,"[onViewDragStateChanged]state=" + state);
            }

            @Override
            public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
                //子view位置发生改变时触发
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                Log.i(TAG,"[onViewPositionChanged]");
            }

            @Override
            public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
                //手指点击view时触发，相当于MotionEvent.ACTION_DOWN
                super.onViewCaptured(capturedChild, activePointerId);
                Log.i(TAG,"[onViewCaptured]");
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                //手指离开view时触发，相对于MotionEvent.ACTION_UP
                super.onViewReleased(releasedChild, xvel, yvel);
                Log.i(TAG,"[onViewReleased]xvel=" + xvel + ",yvel=" + yvel);
                Log.i(TAG,"[onViewReleased]left=" + mainView.getLeft() + ",right=" + mainView.getRight());
                if(mainView.getLeft() < 500){
                    if (mainView.getRight() < 600){
                        viewDragHelper.smoothSlideViewTo(mainView,-400,0);
                        ViewCompat.postInvalidateOnAnimation(CustomViewGroup.this);
                    }else{
                        viewDragHelper.smoothSlideViewTo(mainView,0,0);
                        ViewCompat.postInvalidateOnAnimation(CustomViewGroup.this);
                    }
                }else{
                    viewDragHelper.smoothSlideViewTo(mainView,400,0);
                    ViewCompat.postInvalidateOnAnimation(CustomViewGroup.this);
                }
            }

            @Override
            public int getOrderedChildIndex(int index) {
                Log.i(TAG,"[getOrderedChildIndex]index=" + index);
                return super.getOrderedChildIndex(index);
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                //left水平方向上移动的距离,默认为0(表示不移动),dx表示增量
                return left;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                //top垂直方向上移动的距离，dy表示增量
                return 0;
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainView = getChildAt(1);
        subView = getChildAt(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = subView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if(viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
