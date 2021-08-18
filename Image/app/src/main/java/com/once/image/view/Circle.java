package com.once.image.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import androidx.annotation.Nullable;

import com.once.image.model.Point;
import com.once.image.utils.ColorEvaluator;
import com.once.image.utils.CustomInterpolator;
import com.once.image.utils.PointEvaluator;

public class Circle extends View {

    private final String TAG = Circle.class.getSimpleName();

    private Paint mPaint;
    private Point mPoint;
    private float mRadius = 50.0f;
    private int mColor;

    public Circle(Context context) {
        super(context);
        init();
    }

    public Circle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
    }

    public void setColor(int color) {
        Log.i(TAG, "[setColor]color=" + color);
        mColor = color;
        mPaint.setColor(mColor);
        invalidate();
    }

    public int getColor() {
        return mColor;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
    }


    private void drawCircle(Canvas canvas) {
        if (mPoint != null) {
            canvas.drawCircle(mPoint.getX(), mPoint.getY(), mRadius, mPaint);
        } else {
            canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
            doAnimation();
        }
    }

    private void doAnimation() {
        Point point1 = new Point(getWidth() / 2.0f, getHeight() / 4.0f);
        Point point2 = new Point(getWidth() / 2.0f, getHeight() - mRadius * 2);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), Color.GREEN, Color.RED);
        objectAnimator.setInterpolator(new CustomInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator).with(objectAnimator);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }
}
