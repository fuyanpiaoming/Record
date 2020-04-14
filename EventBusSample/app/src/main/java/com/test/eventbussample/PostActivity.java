package com.test.eventbussample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class PostActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "PostActivity";

    private Button btnPost;
    private Button btnPostSticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        btnPost = findViewById(R.id.btn_post);
        btnPostSticky = findViewById(R.id.btn_post_sticky);

        btnPost.setOnClickListener(this);
        btnPostSticky.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_post:
                Log.i(TAG,"[onClick]post current thread:" + Thread.currentThread().getName());
                EventBus.getDefault().post(new EventbusMessage(1,"good"));
                finish();
                break;
            case R.id.btn_post_sticky:
                Log.i(TAG,"[onClick]postSticky current thread:" + Thread.currentThread().getName());
                EventBus.getDefault().postSticky(new EventbusMessage(2,"hello"));
                finish();
                break;
        }
    }
}
