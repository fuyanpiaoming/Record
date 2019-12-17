package com.once.test.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.once.test.R;

public class CanvaView extends View {

    public CanvaView(Context context) {
        super(context);
    }

    public CanvaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (false) {
            init(canvas);
            testTrans(canvas);
            testScale(canvas);
            testRotate(canvas);
            testSkew(canvas);
            testClip(canvas);
            testSaveAndRestore(canvas);
            testSaveLayer(canvas);
        }
        testSaveLayerAlpha(canvas);
    }

    private void init(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        Rect rect = new Rect(0,0, 200,300);
        canvas.drawRect(rect, paint);
    }

    private void testTrans(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect(0, 0, 200,300);
        canvas.translate(100,200);
        canvas.drawRect(rect,paint);
    }

    private void testScale(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);
        Rect rect = new Rect(0,0, 200,300);
        canvas.scale(1,0.5f,100,200);
        canvas.drawRect(rect,paint);
    }

    private void testRotate(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);
        Rect rect = new Rect(0,0,200,300);
        canvas.rotate(30);
        canvas.drawRect(rect, paint);

    }

    private void testSkew(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        Rect rect = new Rect(0,0,200,300);
        //canvas.rotate(-30);
        //skew中的参数是tanX的值，比如tan45°=1
        canvas.skew(1.5f,0);
        canvas.drawRect(rect,paint);
    }

    private void testClip(Canvas canvas){
        canvas.clipRect(new Rect(0,0,400,400));
        canvas.drawColor(Color.CYAN);

        canvas.clipRect(new RectF(0,0,300,300));
        canvas.drawColor(Color.WHITE);

        canvas.clipRect(0, 0, 200,200);
        canvas.drawColor(Color.CYAN);

        Path path = new Path();
        path.lineTo(0,0);
        path.lineTo(200,50);
        path.lineTo(400,200);
        path.close();
        canvas.clipPath(path);
        canvas.drawColor(Color.BLUE);
    }

    private void testSaveAndRestore(Canvas canvas){
        canvas.drawColor(Color.BLUE);
        //把当前画布压入堆栈
        canvas.save();

        canvas.clipRect(new Rect(0,0,500,500));
        canvas.drawColor(Color.CYAN);
        canvas.save();

        canvas.clipRect(new Rect(0, 0, 400,400));
        canvas.drawColor(Color.YELLOW);
        canvas.save();

        canvas.clipRect(new Rect(0,0,300,300));
        canvas.drawColor(Color.WHITE);
        //取出栈顶画布，并将该画布置为当前使用的画布
        canvas.restore();
        canvas.drawColor(Color.DKGRAY);
    }

    private void testSaveLayer(Canvas canvas){
        canvas.drawColor(Color.GREEN);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int w = 200;
        int h = 200;
        int layId = canvas.saveLayer(0,0,w*2, h*2,paint,Canvas.ALL_SAVE_FLAG);
        Bitmap src = createSrcMap(w,h);
        Bitmap dst = createDstMap(w,h);
        canvas.drawBitmap(src,0,0,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(dst,w/2,h/2,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layId);
    }

    private Bitmap createSrcMap(int w,int h){
        Bitmap bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        canvas.drawOval(new RectF(0,0,w,h), paint);
        return bitmap;
    }

    private Bitmap createDstMap(int w,int h){
        Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFFFFCC44);
        canvas.drawRect(new RectF(0,0,w,h),paint);
        return bitmap;
    }

    private void testSaveLayerAlpha(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawColor(Color.RED);
        paint.setColor(Color.GREEN);
        canvas.drawRect(0,0,200,200,paint);
        int layId = canvas.saveLayerAlpha(0,0, 400,400,0xaa,Canvas.ALL_SAVE_FLAG);
        paint.setColor(Color.BLUE);
        canvas.drawRect(50,50, 300,300,paint);
        canvas.restoreToCount(layId);
    }
}
