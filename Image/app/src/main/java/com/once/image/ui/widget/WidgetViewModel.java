package com.once.image.ui.widget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.once.image.ui.activity.AlertDialogTestActivity;
import com.once.image.ui.activity.AnimationTestActivity;
import com.once.image.ui.activity.AnimatorTestActivity;
import com.once.image.ui.activity.ConfigTestActivity;
import com.once.image.ui.activity.JsonAnimatorActivity;
import com.once.image.ui.activity.MathTestActivity;

import java.util.ArrayList;
import java.util.List;

public class WidgetViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<WidgetMode>> mTestBean;

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
        List<WidgetMode> list = new ArrayList<>();
        //Animation(逐帧动画,补间动画,属性动画)
        WidgetMode testBean = new WidgetMode();
        testBean.setName("Animation");
        testBean.setClassName(AnimationTestActivity.class);
        list.add(testBean);
        //Animator(属性动画接口)
        list.add(new WidgetMode("Animator", AnimatorTestActivity.class));
        //Config
        list.add(new WidgetMode("Config", ConfigTestActivity.class));
        //Math
        list.add(new WidgetMode("Math", MathTestActivity.class));
        //json动画
        list.add(new WidgetMode("JsonAnim", JsonAnimatorActivity.class));
        //AlertDialog
        list.add(new WidgetMode("AlertDialog", AlertDialogTestActivity.class));

        //添加到LiveData
        mTestBean.setValue(list);
    }

    LiveData<List<WidgetMode>> getTestBean() {
        return mTestBean;
    }

}
