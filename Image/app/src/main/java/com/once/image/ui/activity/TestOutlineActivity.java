package com.once.image.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;

import com.once.image.R;

import java.util.Locale;

public class TestOutlineActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnRect;
    private Button btnOutline;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_outline);
        imageView = findViewById(R.id.test_outline_iv);
        btnRect = findViewById(R.id.test_outline_btn_rect);
        btnOutline = findViewById(R.id.test_outline_btn_outline);

        imageView.setOutlineProvider(new TestOutline());

        btnRect.setOnClickListener(mOnClickListener);
        btnOutline.setOnClickListener(mOnClickListener);
        Log.i("TAG","lzj rtl:" + isRtl());
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.test_outline_btn_rect:
                    Rect rect = new Rect();
                    imageView.getGlobalVisibleRect(rect);
                    int centerX = (rect.right - rect.left)/2;
                    int centerY = (rect.bottom - rect.top)/2;
                    Rect rect1 = new Rect(centerX -50, centerY - 50,centerX + 50, centerY + 50);
                    imageView.setClipBounds(rect1);
                    break;
                case R.id.test_outline_btn_outline:
                    if (imageView.getClipToOutline()){
                        imageView.setClipToOutline(false);
                    }else{
                        imageView.setClipToOutline(true);
                    }
                    break;
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private class TestOutline extends ViewOutlineProvider{

        @Override
        public void getOutline(View view, Outline outline) {
            Rect rect = new Rect();
            imageView.getGlobalVisibleRect(rect);
            int marginLeft = 50;
            int marginTop = 50;
            Rect rect1 = new Rect(marginLeft,marginTop, rect.right-rect.left - marginLeft,rect.bottom - rect.top - marginTop);
            outline.setRoundRect(rect1,20);
        }
    }


    private boolean isRtl() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL;
    }
}
