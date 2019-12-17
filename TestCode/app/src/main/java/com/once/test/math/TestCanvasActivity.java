package com.once.test.math;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import com.once.test.R;

/*
使用Canvas的两种情况
1.使用bitmap作为参数创建canvas对象，用于处理图片，如图片的平移，缩放，错切，旋转等
2.在自定义view中的onDraw()和dispatchDraw（）方法中Canvas作为方法的参数传递，用于绘制view的图形
 */

public class TestCanvasActivity extends Activity {

    private ImageView imageView;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_canvas);
        imageView = findViewById(R.id.test_canvas_iv1);
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.book);
        createCanvasWithBitmap();
    }

    private void createCanvasWithBitmap(){
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight(),mBitmap.getConfig());
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Matrix matrix = new Matrix();
        matrix.setRotate(180, mBitmap.getWidth()/2, mBitmap.getHeight()/2);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(mBitmap,matrix,paint);
        imageView.setImageBitmap(bitmap);
    }
}
