package com.once.test.math;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.once.test.R;


public class TestMatrixActivity extends Activity {

    private Button btn_scale, btn_rotate, btn_translate, btn_skew,btn_XMirror,btn_YMirror;
    private ImageView iv_base, iv_after;
    private Bitmap baseBitmap;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_matrix);

        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_skew = (Button) findViewById(R.id.btn_skew);
        btn_XMirror = (Button) findViewById(R.id.btn_x_mirror);
        btn_YMirror = (Button) findViewById(R.id.btn_y_mirror);

        btn_scale.setOnClickListener(click);
        btn_rotate.setOnClickListener(click);
        btn_translate.setOnClickListener(click);
        btn_skew.setOnClickListener(click);
        btn_XMirror.setOnClickListener(click);
        btn_YMirror.setOnClickListener(click);

        iv_base = (ImageView) findViewById(R.id.iv_base);
        iv_after = (ImageView) findViewById(R.id.iv_after);

        baseBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.book);
        iv_base.setImageBitmap(baseBitmap);

        // 设置画笔，消除锯齿
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_scale:
                    bitmapScale(2.0f, 4.0f);
                    break;
                case R.id.btn_rotate:
                    bitmapRotate(180);
                    break;
                case R.id.btn_translate:
                    bitmapTranslate(20f, 20f);
                    break;
                case R.id.btn_skew:
                    bitmapSkew(0.2f, 0.4f);
                    break;
                case R.id.btn_x_mirror:
                    bitmapXMirror();
                    break;
                case R.id.btn_y_mirror:
                    bitmapYMirror();
                    break;
                default:
                    break;
            }

        }
    };

    /**
     * 缩放图片
     */
    protected void bitmapScale(float x, float y) {
        // 由于要将图片放大。所以要依据放大的尺寸又一次创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() * x),
                (int) (baseBitmap.getHeight() * y), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        matrix.setScale(x, y);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }
    protected void bitmapXMirror() {
        Bitmap afterBitmap = Bitmap.createBitmap(
                baseBitmap.getWidth() ,
                baseBitmap.getHeight() , baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1);
        matrix.postTranslate(baseBitmap.getWidth(), 0);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }


    /**
     * y轴镜像
     */
    protected void bitmapYMirror() {
        Matrix matrix = new Matrix();
        matrix.postScale(1, -1);
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap, 0, 0,baseBitmap.getWidth(),baseBitmap.getHeight(), matrix, true);
        iv_after.setImageBitmap(afterBitmap);
    }




    /**
     * 倾斜图片
     */
    protected void bitmapSkew(float dx, float dy) {
        // 依据图片的倾斜比例。计算变换后图片的大小，
        Matrix matrix = new Matrix();
        matrix.setSkew(dx, dy);
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap, 0, 0,baseBitmap.getWidth(),baseBitmap.getHeight(), matrix, true);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片移动
     */
    protected void bitmapTranslate(float dx, float dy) {
        // 须要依据移动的距离来创建图片的拷贝图大小
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() + dx),
                (int) (baseBitmap.getHeight() + dy), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        matrix.setTranslate(dx, dy);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片旋转
     */
    protected void bitmapRotate(float degrees) {
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, baseBitmap.getWidth() / 2, baseBitmap.getHeight() / 2);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        iv_after.setImageBitmap(afterBitmap);
    }


}
