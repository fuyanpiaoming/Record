package com.once.image.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.once.image.R;


public class AnimationTestActivity extends AppCompatActivity {

    private final String TAG = AnimationTestActivity.class.getSimpleName();

    private ImageView mImageView;
    private AnimationDrawable mAnimationDrawable;
    private boolean isAnimationRunning = false;
    private Animation animation;
    private int clickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);

        Button mBtnFrame = findViewById(R.id.anim_frame_btn);
        Button mBtnTween = findViewById(R.id.anim_tween_btn);
        Button mBtnProperty = findViewById(R.id.anim_property_btn);
        mImageView = findViewById(R.id.anim_iv);

        mBtnFrame.setOnClickListener(mOnclickListener);
        mBtnTween.setOnClickListener(mOnclickListener);
        mBtnProperty.setOnClickListener(mOnclickListener);

        //补间动画
        findViewById(R.id.anim_alpha).setOnClickListener(mOnclickListener);
        findViewById(R.id.anim_rotate).setOnClickListener(mOnclickListener);
        findViewById(R.id.anim_scale).setOnClickListener(mOnclickListener);
        findViewById(R.id.anim_translate).setOnClickListener(mOnclickListener);
        findViewById(R.id.anim_set).setOnClickListener(mOnclickListener);

        //属性动画
        findViewById(R.id.property_anim_alpha).setOnClickListener(mOnclickListener);
        findViewById(R.id.property_anim_rotate).setOnClickListener(mOnclickListener);
        findViewById(R.id.property_anim_scale).setOnClickListener(mOnclickListener);
        findViewById(R.id.property_anim_translate).setOnClickListener(mOnclickListener);
        findViewById(R.id.property_anim_set).setOnClickListener(mOnclickListener);

    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.anim_frame_btn:
                    createFrameAnim();
                    doFrameAnimation();
                    break;
                case R.id.anim_tween_btn:
                    createTweenAnim();
                    doTweenAnimation();
                    break;
                case R.id.anim_property_btn:
                    doPropertyAnimation();
                    break;
                //补间动画
                case R.id.anim_alpha:
                    doAlphaAnim();
                    break;
                case R.id.anim_rotate:
                    doRotateAnim();
                    break;
                case R.id.anim_scale:
                    doScaleAnim();
                    break;
                case R.id.anim_translate:
                    doTranslateAnim();
                    break;
                case R.id.anim_set:
                    doSetAnim();
                    break;
                //属性动画
                case R.id.property_anim_alpha:
                    doPropAlphaAnim();
                    break;
                case R.id.property_anim_rotate:
                    doPropRotateAnim();
                    break;
                case R.id.property_anim_scale:
                    doPropScaleAnim();
                    break;
                case R.id.property_anim_translate:
                    doPropTranslateAnim();
                    break;
                case R.id.property_anim_set:
                    doPropSetAnim();
                    break;
            }
        }
    };


    /*----------------------逐帧动画---------------------------------------*/
    //创建逐帧动画
    private void createFrameAnim(){
        //imageView用frame_anim作为背景,setBackground（）和setBackgroundResource（）都可以
        //mImageView.setBackground(getResources().getDrawable(R.drawable.frame_anim));//方法1
        mImageView.setBackgroundResource(R.drawable.frame_anim);//方法2
        mAnimationDrawable = (AnimationDrawable) mImageView.getBackground();
    }

    //逐帧动画,连续播放图片达到动画效果（视觉暂留原理）
    private void doFrameAnimation(){
        if (isAnimationRunning){
            mAnimationDrawable.stop();
        }else{
            mAnimationDrawable.start();
        }
        isAnimationRunning = !isAnimationRunning;
    }
    /*-----------------------逐帧动画--------------------------------------*/




    /*----------------------补间动画---------------------------------------*/
    private void createTweenAnim(){
        mImageView.setBackgroundResource(R.drawable.leave4);
    }

    //补间动画
    private void doTweenAnimation(){
        View view = findViewById(R.id.ll_tween_anim);
        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }

    private void doAlphaAnim(){
        animation = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        mImageView.startAnimation(animation);
    }

    private void doScaleAnim(){
        animation = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        mImageView.startAnimation(animation);
    }

    private void doTranslateAnim(){
        animation = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        //动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG,"[onAnimationStart]");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG,"[onAnimationEnd]");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(TAG,"[onAnimationRepeat]");
            }
        });
        mImageView.startAnimation(animation);
    }

    private void doRotateAnim(){
        animation = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        mImageView.startAnimation(animation);
    }

    private void doSetAnim(){
        animation = AnimationUtils.loadAnimation(this,R.anim.set_anim);
        mImageView.startAnimation(animation);
    }
    /*----------------------补间动画---------------------------------------*/




    /*----------------------属性动画---------------------------------------*/
    //view 的属性：alpha,translationX,translationY,translationZ,x,y,z,rotation,rotationX,rotationY,scaleX,scaleY
    //属性动画
    private void doPropertyAnimation(){
        mImageView.setBackgroundResource(R.drawable.leave15);
        View view = findViewById(R.id.ll_property_anim);
        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }

    private void doPropAlphaAnim(){
        //从xml文件加载透明度动画
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.alpha_animator);
        animator.setTarget(mImageView);
        animator.start();
    }

    private void doPropScaleAnim(){
        //从XML文件中加载组合动画
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.animator_set);
        animatorSet.setTarget(mImageView);
        animatorSet.start();
    }

    private void doPropRotateAnim(){
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.rotate_animator);
        animator.setTarget(mImageView);
        animator.start();
    }

    private void doPropTranslateAnim(){
        testObjectAnimator();
    }

    private void doPropSetAnim(){
        testValueAnimator();
    }

    private void testValueAnimator(){
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1.0f,0.5f);
        scaleAnimator.setDuration(3000);
        scaleAnimator.setInterpolator(new LinearInterpolator());
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                mImageView.setScaleX(scale);
                mImageView.setScaleY(scale);
            }
        });

        ValueAnimator alphaAnimator = ValueAnimator.ofFloat(0.2f,0.9f);
        alphaAnimator.setDuration(3000);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                mImageView.setAlpha(alpha);
            }
        });

        ValueAnimator translateAnimator = ValueAnimator.ofInt(100,400);
        translateAnimator.setDuration(3000);
        translateAnimator.setInterpolator(new AccelerateInterpolator());
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int translation = (int) animation.getAnimatedValue();
                mImageView.setTranslationX(translation);
                mImageView.setTranslationY(translation);
            }
        });

        ValueAnimator rotateAnimator = ValueAnimator.ofFloat(0,360);
        rotateAnimator.setDuration(3000);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float rotate = (float) animation.getAnimatedValue();
                mImageView.setRotation(rotate);
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alphaAnimator).after(translateAnimator).after(rotateAnimator).before(scaleAnimator);
        animatorSet.start();
    }

    private void testObjectAnimator(){
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mImageView,"alpha",0.2f,0.9f);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mImageView,"scaleX",0.1f,0.5f,1.0f,1.5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mImageView,"scaleY",0.1f,0.5f,1.0f,1.5f);

        ObjectAnimator translateX = ObjectAnimator.ofFloat(mImageView,"translationX",0,50,100,200);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(mImageView,"translationY",0,50,100,200);
        //ObjectAnimator translationZ = ObjectAnimator.ofFloat(mImageView,"translationZ",2,5);

        ObjectAnimator rotation = ObjectAnimator.ofFloat(mImageView,"rotation",0,360,0);
        //ObjectAnimator rotationX = ObjectAnimator.ofFloat(mImageView,"rotationX",0,360,0);
        //ObjectAnimator rotationY = ObjectAnimator.ofFloat(mImageView,"rotationY",0,360,0);

        clickTime++;
        switch (clickTime){
            case 1:
                alpha.setDuration(3000);
                alpha.start();
                break;
            case 2:
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(scaleX).with(scaleY);
                animatorSet.setDuration(3000);
                animatorSet.start();
                break;
            case 3:
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.playSequentially(translateX,translateY);
                animatorSet1.setDuration(3000);
                animatorSet1.setInterpolator(new LinearInterpolator());
                animatorSet1.start();
                break;
            case 4:
                rotation.setDuration(3000);
                rotation.setInterpolator(new AccelerateInterpolator());
                rotation.start();
                break;
        }
        if (clickTime >= 4){
            clickTime = 0;
        }
    }

    private void testListener(){
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mImageView,"rotation",0,360,0);
        //添加监听器
        rotation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });

        //监听动画更新
        rotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG,"[onAnimationUpdate]:fraction=" + animation.getAnimatedFraction());
                Log.i(TAG,"[onAnimationUpdate]:value=" + animation.getAnimatedValue());
            }
        });
    }
    /*----------------------属性动画---------------------------------------*/

}
