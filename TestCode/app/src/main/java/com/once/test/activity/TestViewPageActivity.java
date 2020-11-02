package com.once.test.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.once.test.R;
import com.once.test.adapter.MyFragmentPagerAdapter;
import com.once.test.adapter.MyFragmentStatePagerAdapter;
import com.once.test.adapter.MyViewPageAdapter;
import com.once.test.fragment.BlankFragment;
import com.once.test.fragment.DynamicFragment1;
import com.once.test.fragment.DynamicFragment2;

import java.util.ArrayList;
import java.util.List;

public class TestViewPageActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = TestViewPageActivity.class.getSimpleName();

    private List<View> viewList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_page);

        initViewList();
        initFragmentList();

        viewPager = findViewById(R.id.view_page);
        viewPager.addOnPageChangeListener(onPageChangeListener);


        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViewList() {
        viewList.clear();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.fragment_blank, null);
        View view2 = inflater.inflate(R.layout.fragment_dynamic1, null);
        View view3 = inflater.inflate(R.layout.fragment_dynamic2, null);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
    }

    private void initFragmentList() {
        fragmentList.clear();
        fragmentList.add(new BlankFragment());
        fragmentList.add(new DynamicFragment1());
        fragmentList.add(new DynamicFragment2());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(viewList);
                viewPager.setAdapter(myViewPageAdapter);
                viewPager.setPageTransformer(true, new ScalePageTransformer());
                break;
            case R.id.btn_two:
                MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
                viewPager.setCurrentItem(1);
                viewPager.setAdapter(myFragmentPagerAdapter);
                viewPager.setPageTransformer(true, new DepthPageTransformer());
                break;
            case R.id.btn_three:
                MyFragmentStatePagerAdapter myFragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(),fragmentList);
                viewPager.setCurrentItem(1);
                viewPager.setAdapter(myFragmentStatePagerAdapter);
                viewPager.setPageTransformer(true,new RotatePagerTransform());
                break;
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            Log.i(TAG, "[onPageScrolled] i=" + i + ",v=" + v + ",i1=" + i1);
        }

        @Override
        public void onPageSelected(int i) {
            Log.d(TAG, "[onPageSelected]i=" + i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            Log.w(TAG, "[onPageScrollStateChanged]i=" + i);
        }
    };


    public class ScalePageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(View page, float position) {
            // out of left screen
            if (position < -1.0f) {
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            }
            // slide left
            else if (position <= 0.0f) {
                page.setAlpha(1.0f);
                page.setTranslationX(0.0f);
                page.setScaleX(1.0f);
                page.setScaleY(1.0f);
            }
            // slide right
            else if (position <= 1.0f) {
                page.setAlpha(1.0f - position);
                page.setTranslationX(-page.getWidth() * position);
                float scale = MIN_SCALE + (1.0f - MIN_SCALE) * (1.0f - position);
                page.setScaleX(scale);
                page.setScaleY(scale);
            }
            // out of right screen
            else {
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            }
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public class RotatePagerTransform implements ViewPager.PageTransformer{

        private static final float ROTATION = 30.0f;
        @Override
        public void transformPage(@NonNull View page, float position) {
            if (position < -1){
                rotate(page,-ROTATION);
            }else if(position <= 1){
                rotate(page,ROTATION * position);
            }else{
                rotate(page,ROTATION);
            }
        }
    }

    private void rotate(View page,float rotation){
        page.setPivotX(page.getWidth()*0.5f);
        page.setPivotY(page.getHeight());
        page.setRotation(rotation);
    }

}