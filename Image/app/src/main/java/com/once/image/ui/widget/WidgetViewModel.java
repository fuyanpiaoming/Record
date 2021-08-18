package com.once.image.ui.widget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.once.image.ui.activity.AnimationTestActivity;
import com.once.image.ui.activity.AnimatorTestActivity;
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
        //Animation(逐帧动画,补间动画,属性动画)
        TestBean testBean = new TestBean();
        testBean.setName("Animation");
        testBean.setClassName(AnimationTestActivity.class);
        list.add(testBean);
        //Animator(属性动画接口)
        list.add(new TestBean("Animator", AnimatorTestActivity.class));
        //Config
        list.add(new TestBean("Config", ConfigTestActivity.class));

        //添加到LiveData
        mTestBean.setValue(list);
    }

    LiveData<List<TestBean>> getTestBean() {
        return mTestBean;
    }

}
