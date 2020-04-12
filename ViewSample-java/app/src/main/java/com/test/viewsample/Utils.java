package com.test.viewsample;

import com.test.viewsample.spinner.SpinnerTestActivity;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static List<Class> classList = new ArrayList<>();
    private static List<String> nameList = new ArrayList<>();

    public static List<Class> getCalssList() {
        classList.clear();
        classList.add(SpinnerTestActivity.class);
        return classList;
    }

    public static List<String> getNameList() {
        nameList.clear();
        nameList.add("Spinner");
        return nameList;
    }
}
