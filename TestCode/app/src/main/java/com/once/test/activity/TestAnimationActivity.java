package com.once.test.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.once.test.R;

public class TestAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = TestAnimationActivity.class.getSimpleName();

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    int clickTime = 0;
    boolean isFrame = false;
    Build build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);

        imageView1 = findViewById(R.id.image_q1);
        imageView2 = findViewById(R.id.image_q2);
        imageView3 = findViewById(R.id.image_q3);
        imageView4 = findViewById(R.id.image_q4);


        findViewById(R.id.btn_frame_anim).setOnClickListener(this);
        findViewById(R.id.btn_property_anim).setOnClickListener(this);
        findViewById(R.id.btn_hloder_anim).setOnClickListener(this);
        findViewById(R.id.btn_object_anim).setOnClickListener(this);
        findViewById(R.id.btn_rotate_anim).setOnClickListener(this);
        findViewById(R.id.btn_scale_anim).setOnClickListener(this);
        findViewById(R.id.btn_translate_anim).setOnClickListener(this);
        findViewById(R.id.btn_alpha_anim).setOnClickListener(this);
        findViewById(R.id.btn_set_anim).setOnClickListener(this);
        findViewById(R.id.btn_value_anim).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_frame_anim:
                isFrame = !isFrame;
                if (isFrame) {
                    frameAnimation();
                } else {
                    frameAnimation2();
                }

                break;
            case R.id.btn_property_anim:
                animatorSet(imageView3);
                break;
            case R.id.btn_hloder_anim:
                propertyValuesHolder(imageView2);
                break;
            case R.id.btn_object_anim:
                clickTime++;
                objectAnimator(clickTime);
                if (clickTime == 13) {
                    clickTime = 0;
                }
                break;
            case R.id.btn_rotate_anim:
                rotateAnim();
                break;
            case R.id.btn_scale_anim:
                scaleAnim();
                break;
            case R.id.btn_translate_anim:
                translateAnim();
                break;
            case R.id.btn_alpha_anim:
                alphaAnim();
                break;
            case R.id.btn_set_anim:
                animationSet();
                break;
            case R.id.btn_value_anim:
                valueAnimator(imageView2);
                break;
        }
    }

    private void alphaAnim() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(5000);
        alphaAnimation.setRepeatCount(10);
        imageView1.startAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "{onAnimationStart}");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "{onAnimationEnd}");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(TAG, "{onAnimationRepeat}");
            }
        });
    }

    private void rotateAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setRepeatCount(10);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        imageView2.startAnimation(rotateAnimation);
    }

    private void scaleAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        scaleAnimation.setDuration(5000);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.RESTART);
        imageView1.startAnimation(scaleAnimation);
    }

    private void translateAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 50, 0, 50);
        translateAnimation.setDuration(5000);
        translateAnimation.setRepeatCount(10);
        translateAnimation.setRepeatMode(Animation.RESTART);
        imageView2.startAnimation(translateAnimation);
    }

    private void animationSet() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(5000);
        animationSet.addAnimation(alphaAnimation);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360);
        rotateAnimation.setDuration(5000);
        animationSet.addAnimation(rotateAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 100);
        translateAnimation.setDuration(5000);
        animationSet.addAnimation(translateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        scaleAnimation.setDuration(5000);
        animationSet.addAnimation(scaleAnimation);

        imageView3.startAnimation(animationSet);
    }


    private void objectAnimator(int type) {
        switch (type) {
            case 1:
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView1, "translationX", 100);
                objectAnimator.setDuration(5000);
                objectAnimator.start();
                break;
            case 2:
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView1, "translationY", 100);
                objectAnimator1.setDuration(5000);
                objectAnimator1.start();
                break;
            case 3:
                ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(imageView2, "alpha", 0.0f, 1.0f);
                objectAnimatorAlpha.setDuration(5000);
                objectAnimatorAlpha.setRepeatMode(ObjectAnimator.REVERSE);
                objectAnimatorAlpha.setRepeatCount(ObjectAnimator.INFINITE);
                objectAnimatorAlpha.start();
                break;
            case 4:
                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(imageView1, "rotation", 0, 360);
                objectAnimator3.setDuration(5000);
                objectAnimator3.start();
                break;
            case 5:
                ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(imageView1, "rotationX", 0, 180);
                objectAnimator4.setDuration(5000);
                objectAnimator4.start();
                break;
            case 6:
                ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(imageView1, "rotationY", 0, 180);
                objectAnimator5.setDuration(5000);
                objectAnimator5.start();
                break;
            case 7:
                ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(imageView1, "scaleX", 0, 1);
                objectAnimator6.setDuration(5000);
                objectAnimator6.start();
                break;
            case 8:
                ObjectAnimator objectAnimator7 = ObjectAnimator.ofFloat(imageView1, "scaleY", 0, 1);
                objectAnimator7.setDuration(5000);
                objectAnimator7.start();
                break;
            case 9:
                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imageView1, "x", 200);
                objectAnimatorX.setDuration(5000);
                objectAnimatorX.start();
                break;
            case 10:
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imageView1, "y", 200);
                objectAnimatorY.setDuration(5000);
                objectAnimatorY.start();
                break;
            case 11:
                ViewWrapper viewWrapper = new ViewWrapper(imageView1);
                ObjectAnimator objectAnimatorWidth = ObjectAnimator.ofInt(viewWrapper, "width", 500);
                objectAnimatorWidth.setDuration(5000);
                objectAnimatorWidth.start();
                break;
            case 12:
                ObjectAnimator objectAnimatorPivotX = ObjectAnimator.ofFloat(imageView1, "pivotX", 100);
                objectAnimatorPivotX.setDuration(5000);
                objectAnimatorPivotX.start();
                break;
            case 13:
                ObjectAnimator objectAnimatorPivotY = ObjectAnimator.ofFloat(imageView1, "pivotY", 100);
                objectAnimatorPivotY.setDuration(5000);
                objectAnimatorPivotY.start();
                break;
        }
    }

    private void propertyValuesHolder(View view) {
        PropertyValuesHolder pvh = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f, 0.5f, 1.0f);
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 100);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("translationY", 200);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, pvh, pvh1, pvh2, pvh3);
        objectAnimator.setDuration(5000);
        objectAnimator.setRepeatCount(5);
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        //objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }

    private void valueAnimator(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100.0f);
        valueAnimator.setTarget(view);
        valueAnimator.setDuration(5000);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                Log.i(TAG, "[onAnimationStart]");
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                Log.i(TAG, "[onAnimationEnd]");
            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.i(TAG, "[onAnimationUpdate]value=" + value);
            }
        });
        valueAnimator.start();
    }

    private void animatorSet(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f, 0.5f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleY", 0, 1);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2, objectAnimator3);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

    private void tweenAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_test);
        animation.setDuration(5000);
        imageView3.startAnimation(animation);
    }

    //逐帧动画，xml文件放置在drawable目录下，用作背景图片
    private void frameAnimation() {
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView4.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }

    private void frameAnimation2() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (int i = 1; i < 10; i++) {
            int id = getResources().getIdentifier("q" + i, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            animationDrawable.addFrame(drawable, 1000);
        }
        imageView3.setImageDrawable(animationDrawable);
        animationDrawable.start();
        //animationDrawable.stop();
    }

    private void loadAnimator() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.object_animator_test);
        animator.setTarget(imageView1);
        animator.start();
    }

    private void animationView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                imageView4.animate()
                        .alphaBy(0)
                        .alpha(1)
                        .setDuration(5000)
                        .x(200)
                        .y(200)
                        .rotationBy(0)
                        .rotation(360)
                        .translationXBy(0)
                        .translationX(50)
                        .translationYBy(0)
                        .translationY(100)
                        .scaleXBy(0)
                        .scaleX(1)
                        .scaleYBy(0)
                        .scaleY(1)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {

                            }
                        })
                        .withStartAction(new Runnable() {
                            @Override
                            public void run() {

                            }
                        })
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }).start();
            }
        }
    }

    //布局动画
    private void testLayoutAnim() {
        LinearLayout linearLayout = new LinearLayout(this);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(5000);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(alphaAnimation);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(layoutAnimationController);
    }

    private void testAnimatedVector() {
        Drawable drawable = imageView1.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    static class ViewWrapper {
        private View view;

        public ViewWrapper(View view) {
            this.view = view;
        }

        public int getWidth() {
            return view.getLayoutParams().width;
        }

        public void setWidth(int width) {
            view.getLayoutParams().width = width;
            view.requestLayout();
        }
    }
}