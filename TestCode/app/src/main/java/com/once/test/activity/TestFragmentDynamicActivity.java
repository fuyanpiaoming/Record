package com.once.test.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.once.test.R;
import com.once.test.fragment.BlankFragment;
import com.once.test.fragment.DynamicFragment1;
import com.once.test.fragment.DynamicFragment2;

public class TestFragmentDynamicActivity extends AppCompatActivity implements View.OnClickListener{

    private BlankFragment blankFragment;
    private DynamicFragment1 dynamicFragment1;
    private DynamicFragment2 dynamicFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment_dynamic);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
        findViewById(R.id.btn_hide).setOnClickListener(this);

        blankFragment = BlankFragment.newInstance("hello","hi");
        dynamicFragment1 = new DynamicFragment1();
        dynamicFragment2 = new DynamicFragment2();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                replaceFragment(dynamicFragment1);
                break;
            case R.id.btn_two:
                replaceFragment(dynamicFragment2);
                break;
            case R.id.btn_add:
                addFragment(blankFragment);
                break;
            case R.id.btn_remove:
                removeFragment(dynamicFragment1);
                break;
            case R.id.btn_show:
                showFragment(dynamicFragment1);
                break;
            case R.id.btn_hide:
                hideFragment(dynamicFragment1);
                break;
        }
    }

    private void addFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void removeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    private void hideFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}