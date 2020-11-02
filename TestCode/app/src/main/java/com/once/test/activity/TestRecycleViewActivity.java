package com.once.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.once.test.R;
import com.once.test.adapter.CustomRecycleViewAdapter;
import com.once.test.mode.CustomMode;

import java.util.ArrayList;
import java.util.List;

public class TestRecycleViewActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<CustomMode>customModeList = new ArrayList<>();
    private DividerItemDecoration verDivider;
    private DividerItemDecoration horDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycle_view);

        initData();
        recyclerView = findViewById(R.id.recycler_view);

        verDivider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        horDivider = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);

        findViewById(R.id.btn_vertical).setOnClickListener(this);
        findViewById(R.id.btn_horizontal).setOnClickListener(this);
        findViewById(R.id.btn_grid).setOnClickListener(this);
        findViewById(R.id.btn_stag).setOnClickListener(this);
    }

    private void initData(){
        customModeList.clear();
        customModeList.add(new CustomMode(R.drawable.q1,"YanLing"));
        customModeList.add(new CustomMode(R.drawable.q2,"YanLing"));
        customModeList.add(new CustomMode(R.drawable.q3,"GaoYue"));
        customModeList.add(new CustomMode(R.drawable.q4,"GaoYue"));
        customModeList.add(new CustomMode(R.drawable.q5,"XueNv"));
        customModeList.add(new CustomMode(R.drawable.q6,"Yan"));
        customModeList.add(new CustomMode(R.drawable.q7,"NvYao"));
        customModeList.add(new CustomMode(R.drawable.q8,"ShaoSiMing"));
        customModeList.add(new CustomMode(R.drawable.q10,"NvYao"));
        customModeList.add(new CustomMode(R.drawable.q11,"NongYu"));
        customModeList.add(new CustomMode(R.drawable.q12,"YanFei"));
        customModeList.add(new CustomMode(R.drawable.q13,"ZiNv"));
        customModeList.add(new CustomMode(R.drawable.q16,"ShaoSiMing"));
        customModeList.add(new CustomMode(R.drawable.q19,"XueNv"));
        customModeList.add(new CustomMode(R.drawable.q24,"LingJi"));
        customModeList.add(new CustomMode(R.drawable.q28,"XiaoMeng"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_vertical:
                setVertical();
                break;
            case R.id.btn_horizontal:
                setHorizontal();
                break;
            case R.id.btn_grid:
                setGrid();
                break;
            case R.id.btn_stag:
                setStaggeredGrid();
                break;
        }
    }

    private void setVertical(){
        //默认垂直显示
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.removeItemDecoration(horDivider);
        recyclerView.addItemDecoration(verDivider);

        CustomRecycleViewAdapter customRecycleViewAdapter = new CustomRecycleViewAdapter(customModeList);

        recyclerView.setAdapter(customRecycleViewAdapter);
    }

    private void setHorizontal(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.removeItemDecoration(verDivider);
        recyclerView.addItemDecoration(horDivider);
        CustomRecycleViewAdapter customRecycleViewAdapter = new CustomRecycleViewAdapter(customModeList);
        recyclerView.setAdapter(customRecycleViewAdapter);
    }

    private void setGrid(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.removeItemDecoration(verDivider);
        recyclerView.removeItemDecoration(horDivider);
        CustomRecycleViewAdapter customRecycleViewAdapter = new CustomRecycleViewAdapter(customModeList);
        recyclerView.setAdapter(customRecycleViewAdapter);
    }

    private void setStaggeredGrid(){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.addItemDecoration(verDivider);
        recyclerView.addItemDecoration(horDivider);
        CustomRecycleViewAdapter customRecycleViewAdapter = new CustomRecycleViewAdapter(customModeList);
        recyclerView.setAdapter(customRecycleViewAdapter);
    }
}