package com.once.image.ui.home;

public class HomeMode {
    private String name;
    private Class className;
    private int drawable;

    HomeMode(String name, Class className, int drawable) {
        this.name = name;
        this.className = className;
        this.drawable = drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public int getDrawable() {
        return drawable;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
