package com.once.image.ui.widget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.once.image.ui.activity.AnimationTestActivity;
import com.once.image.ui.activity.ConfigTestActivity;

import java.util.ArrayList;
import java.util.List;

public class WidgetViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<TestBean>> mTestBean;

    public WidgetViewModel() {
        mText = new MutableLiveData<>();
        mTestBean = new MutableLiveData<>();
    }

    void setText(String text) {
        mText.setValue(text);
    }

    LiveData<String> getText() {
        return mText;
    }

    void setTestBean() {
        List<TestBean> list = new ArrayList<>();
        //Animation
        TestBean testBean = new TestBean();
        testBean.setName("Animation");
        testBean.setClassName(AnimationTestActivity.class);
        list.add(testBean);
        //Config
        list.add(new TestBean("Config", ConfigTestActivity.class));

        //添加到LiveData
        mTestBean.setValue(list);
    }

    LiveData<List<TestBean>> getTestBean() {
        return mTestBean;
    }

}
