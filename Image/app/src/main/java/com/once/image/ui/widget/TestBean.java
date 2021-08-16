package com.once.image.ui.widget;

public class TestBean {
    private String mName;
    private Class mClassName;

    TestBean(){

    }

    public TestBean(String name,Class className){
        mName = name;
        mClassName = className;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setClassName(Class className) {
      mClassName = className;
    }

    public Class getClassName() {
        return mClassName;
    }
}
