package com.once.test.activity;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.once.test.R;
import com.once.test.adapter.MyViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestViewPagerFourActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private List<View> viewList = new ArrayList<>();

    private int bmWidth = 0;
    private int offset = 0;
    private int index = 0;
    private int one = 0;
    private int two = 0;

    private ViewPager viewPager;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_pager_four);
        initView();

        findViewById(R.id.tv_first).setOnClickListener(this);
        findViewById(R.id.tv_second).setOnClickListener(this);
        findViewById(R.id.tv_third).setOnClickListener(this);

    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.test_layout_one, null, false);
        View view1 = inflater.inflate(R.layout.test_layout_two, null, false);
        View view2 = inflater.inflate(R.layout.test_layout_three, null, false);
        viewList.clear();
        viewList.add(view);
        viewList.add(view1);
        viewList.add(view2);

        viewPager = findViewById(R.id.view_pager);
        imageView = findViewById(R.id.iv_cursor);

        bmWidth = BitmapFactory.decodeResource(getResources(), R.drawable.line).getWidth();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        offset = (screenWidth / 3 - bmWidth) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);

        one = offset * 2 + bmWidth;
        two = one * 2;

        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(viewList);
        viewPager.setAdapter(myViewPageAdapter);

        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation = null;
        switch (position) {
            case 0:
                if (index == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (index == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (index == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (index == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (index == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (index == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        index = position;
        if (animation != null) {
            animation.setFillAfter(true);
            animation.setDuration(500);
            imageView.startAnimation(animation);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_first:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_second:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_third:
                viewPager.setCurrentItem(2);
                break;
        }

    }

}