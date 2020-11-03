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

public class TestViewPagerThreeActivity extends AppCompatActivity {

    private List<View>viewList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_pager_three);
        initView();
        initTitle();
        viewPager = findViewById(R.id.view_pager_three);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(viewList,titleList);
        viewPager.setAdapter(myPagerAdapter);
    }

    private void initView(){
        viewList.clear();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.test_layout_one,null,false);
        View view1 = layoutInflater.inflate(R.layout.test_layout_two,null,false);
        View view2 = layoutInflater.inflate(R.layout.test_layout_three,null,false);

        viewList.add(view);
        viewList.add(view1);
        viewList.add(view2);
    }

    private void initTitle(){
        titleList.clear();
        titleList.add("首页");
        titleList.add("搜索");
        titleList.add("我的");
    }

    private class MyPagerAdapter extends PagerAdapter{
        private List<View>viewList;
        private List<String>titleList;

        public MyPagerAdapter(List<View>views, List<String>titles) {
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
            container.addView(viewList.get(position));
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