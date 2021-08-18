package com.once.image.utils;

import android.util.Log;
import android.view.animation.LinearInterpolator;

//LinearLayout插值器
public class CustomInterpolator extends LinearInterpolator {
    @Override
    public float getInterpolation(float input) {
        Log.i("CustomInterpolator","CustomInterpolator[getInterpolation]input=" + input);
        return input;
    }
}