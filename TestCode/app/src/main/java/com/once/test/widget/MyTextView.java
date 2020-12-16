package com.once.test.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.once.test.R;


public class MyTextView extends AppCompatTextView {

    private final String TAG = MyTextView.class.getSimpleName();

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint;
    private int mWidth = 0;
    private LinearGradient mLinearGradient;
    private Matrix matrix;
    private int translate;

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "[onDraw]");
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint1);
        canvas.save();
        canvas.translate(10, 0);
        super.onDraw(canvas);
        canvas.restore();
        if (matrix != null) {
            translate += mWidth / 5;
            if (translate > 2 * mWidth) {
                translate = -mWidth;
            }
            matrix.setTranslate(translate, 0);
            mLinearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(300);
        }
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(R.color.color_ceng));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(getResources().getColor(R.color.color_red));
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "[onSizeChanged]w=" + w + ",h=" + h + ",oldw=" + oldw + ",oldh=" + oldh);
        if (mWidth == 0) {
            mWidth = getMeasuredWidth();
            if (mWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mWidth, 0, new int[]{Color.BLUE, 0xffffff, Color.GREEN}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                matrix = new Matrix();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG,"ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG,"ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG,"ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG,"ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i(TAG,"ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG,"ACTION_POINTER_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
