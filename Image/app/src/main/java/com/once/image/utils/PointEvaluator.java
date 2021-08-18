package com.once.image.utils;

import android.animation.TypeEvaluator;
import android.util.Log;

import com.once.image.model.Point;

//Point估值器
public class PointEvaluator implements TypeEvaluator<Point> {

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        Log.i("PointEvaluator","PointEvaluator[evaluate]fraction=" + fraction);
        float x = startValue.getX() + fraction * (endValue.getX() - startValue.getX());
        float y = startValue.getY() + fraction * (endValue.getY() - startValue.getY());
        return new Point(x, y);
    }
}