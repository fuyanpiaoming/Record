package com.once.image.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.once.image.R;

public class JsonAnimatorActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView1;
    private LottieAnimationView lottieAnimationView2;
    private LottieAnimationView lottieAnimationView3;
    private LottieAnimationView lottieAnimationView4;
    private LottieAnimationView lottieAnimationView5;
    private LottieAnimationView lottieAnimationView6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_animator);

        lottieAnimationView1 = findViewById(R.id.json_lottie_view1);
        lottieAnimationView2 = findViewById(R.id.json_lottie_view2);
        lottieAnimationView3 = findViewById(R.id.json_lottie_view3);
        lottieAnimationView4 = findViewById(R.id.json_lottie_view4);
        lottieAnimationView5 = findViewById(R.id.json_lottie_view5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lottieAnimationView1.playAnimation();
        lottieAnimationView2.playAnimation();
        lottieAnimationView3.playAnimation();
        lottieAnimationView4.playAnimation();
        lottieAnimationView5.playAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lottieAnimationView1.pauseAnimation();
        lottieAnimationView2.pauseAnimation();
        lottieAnimationView3.pauseAnimation();
        lottieAnimationView4.pauseAnimation();
        lottieAnimationView5.pauseAnimation();
    }
}
