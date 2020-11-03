
package com.once.test.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once.test.R;

import java.util.ArrayList;
import java.util.List;

public class TestViewPageTwoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> viewList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_page_two);
        initView();
        initTitle();

        viewPager = findViewById(R.id.view_page);
        MyPageAdapter myPageAdapter = new MyPageAdapter(viewList, titleList);
        viewPager.setAdapter(myPageAdapter);
    }

    private void initView() {
        viewList.clear();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.test_layout_one, null);
        View view1 = inflater.inflate(R.layout.test_layout_two, null);
        View view2 = inflater.inflate(R.layout.test_layout_three, null);

        viewList.add(view);
        viewList.add(view1);
        viewList.add(view2);
    }

    private void initTitle() {
        titleList.clear();
        titleList.add("首页");
        titleList.add("通讯录");
        titleList.add("朋友圈");
        titleList.add("我的");
    }

    private class MyPageAdapter extends PagerAdapter {

        private List<View> viewList;
        private List<String> titleList;

        public MyPageAdapter(List<View> views, List<String> titles) {
            viewList = views;
            titleList = titles;
        }

        @Override
        public void startUpdate(@NonNull ViewGroup container) {
            super.startUpdate(container);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ((ViewPager) container).addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public void finishUpdate(@NonNull ViewGroup container) {
            super.finishUpdate(container);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}