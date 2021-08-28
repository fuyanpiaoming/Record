package com.once.image.ui.slideshow;

import android.graphics.drawable.Drawable;

public class SlideMode {
    private String mName;
    private Class mClassName;
    private int mDrawable;

    public SlideMode(String name, Class cls, int drawable){
        mName = name;
        mClassName = cls;
        mDrawable = drawable;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getName() {
        return mName;
    }

    public void setClassName(Class mClass) {
        this.mClassName = mClass;
    }

    public Class getClassName() {
        return mClassName;
    }

    public void setDrawable(int drawable) {
        this.mDrawable = drawable;
    }

    public int getDrawable() {
        return mDrawable;
    }
}
