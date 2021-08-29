package com.once.image.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.once.image.R;

//自定义动态壁纸功能
public class MyWallPaperService extends WallpaperService {

    private final Handler handler = new Handler();

    @Override
    public Engine onCreateEngine() {
        return new CustomEngine();
    }

    private class CustomEngine extends Engine {
        private final Paint mPaint = new Paint();
        private float centerX;
        private float centerY;
        private boolean visible;
        private int count = 0;

        private Bitmap bitmap1;
        private Bitmap bitmap2;
        private Bitmap bitmap3;

        private final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                draw();
            }
        };

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.leave1);
            bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.leave2);
            bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.leave3);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                draw();
            } else {
                handler.removeCallbacks(runnable);
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            centerX = width / 2.0f;
            centerY = height / 2.0f;
            draw();
            super.onSurfaceChanged(holder, format, width, height);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(runnable);
        }

        public void draw() {
            SurfaceHolder surfaceHolder = getSurfaceHolder();
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.WHITE);
                switch (count) {
                    case 0:
                        canvas.drawBitmap(bitmap1, centerX, centerY, mPaint);
                        break;
                    case 1:
                        canvas.drawBitmap(bitmap2, centerX, centerY, mPaint);
                        break;
                    case 2:
                        canvas.drawBitmap(bitmap3, centerX, centerY, mPaint);
                        break;
                }
                if (count < 3) {
                    count++;
                } else {
                    count = 0;
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            handler.removeCallbacks(runnable);
            if (visible) {
                handler.postDelayed(runnable, 3000);
            }
        }
    }
}
