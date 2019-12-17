package com.once.test.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class XfmodeView1 extends View {

    private float dx = 0f;
    private Bitmap srcBitmap;
    private Bitmap dstBitmap;
    private Paint paint;
    private Paint textPaint;
    private Path path;
    private float textWidth;
    private float mItemWaveLength;
    private String text = "How are you?";

    public XfmodeView1(Context context) {
        super(context);
        init();
    }

    public XfmodeView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfmodeView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //禁用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Canvas srcCanvas = new Canvas(srcBitmap);
        srcCanvas.drawText(text,(srcBitmap.getWidth() - textWidth)/2, srcBitmap.getHeight()/2,textPaint);
        canvas.drawBitmap(srcBitmap, 100, 100, paint);

        int layerID = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);

        path.reset();
        path.moveTo(-mItemWaveLength + dx, srcBitmap.getHeight() / 2);
        float halfWavelen = mItemWaveLength / 2;
        for (float i = -mItemWaveLength; i <= textWidth + mItemWaveLength; i += mItemWaveLength) {
            path.rQuadTo(halfWavelen / 2, 50, halfWavelen, 0);
            path.rQuadTo(halfWavelen / 2, -50, halfWavelen, 0);
        }

        path.lineTo(srcBitmap.getWidth(), srcBitmap.getHeight());
        path.lineTo(0, srcBitmap.getHeight());
        path.close();

        Canvas dstCanvas = new Canvas(dstBitmap);
        //擦除 dstCanvas 这个画布上的信息（这个很重要）
        dstCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        dstCanvas.drawPath(path, paint);
        canvas.drawBitmap(dstBitmap, 100, 100, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(srcBitmap, 100, 100, paint);
        //如下代码不能缺少，不然会导致画面空白
        paint.setXfermode(null);
        canvas.restoreToCount(layerID);
    }

    private void init() {
        //初始化画笔
        paint = new Paint();
        //设置画笔颜色（不能为完全透明）
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 源图像
        srcBitmap = Bitmap.createBitmap(500, 400, Bitmap.Config.ARGB_8888);
        //目标图像
        dstBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //路径（贝塞尔曲线）
        path = new Path();
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(80);
        //获取文字宽度
        textWidth = textPaint.measureText(text);
        //贝塞尔曲线波长
        mItemWaveLength = textWidth;
        startAnim();
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

}
