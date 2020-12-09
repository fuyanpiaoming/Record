package com.once.test.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.once.test.R;

import java.io.File;

public class TestVideoActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = TestVideoActivity.class.getSimpleName();
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_video);
        videoView = findViewById(R.id.video_view);

        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        findViewById(R.id.btn_resume).setOnClickListener(this);

        File file = new File(Environment.getExternalStorageDirectory(),"test.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(videoView != null){
            videoView.suspend();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                if (!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.btn_pause:
                if(videoView.isPlaying()){
                    videoView.pause();
                }
                break;
            case R.id.btn_resume:
                if(videoView.isPlaying()){
                    videoView.resume();
                }
                break;
        }
    }
}