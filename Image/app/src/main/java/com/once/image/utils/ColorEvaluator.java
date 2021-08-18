package com.once.image.utils;

import android.animation.TypeEvaluator;
import android.graphics.Color;
import android.util.Log;

//Color估值器
public class ColorEvaluator implements TypeEvaluator<Integer> {

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        Log.i("ColorEvaluator","ColorEvaluator[evaluate]fraction=" + fraction);
        int alpha = (int)(Color.alpha(startValue) + fraction*(Color.alpha(endValue) -Color.alpha(startValue)));
        int red = (int)(Color.red(startValue) + fraction*(Color.red(endValue) - Color.red(startValue)));
        int green = (int)(Color.green(startValue) + fraction*(Color.green(endValue) - Color.green(startValue)));
        int blue = (int)(Color.blue(startValue) + fraction*(Color.blue(endValue) - Color.blue(startValue)));
        return Color.argb(alpha,red,green,blue);
    }
}