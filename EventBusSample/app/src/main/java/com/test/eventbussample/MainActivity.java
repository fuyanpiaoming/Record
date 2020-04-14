package com.test.eventbussample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * https://github.com/greenrobot/EventBus
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvShowPostMessage;
    private Button btnGoPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShowPostMessage = findViewById(R.id.tv_show_post_message);
        btnGoPost = findViewById(R.id.btn_main);
        btnGoPost.setOnClickListener(this);
        //注册EventBus
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //注册EventBus
        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //解注册
        //EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.btn_main:
                Intent intent = new Intent(MainActivity.this,PostActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false,priority = 1)
    public void onMessageEvent(EventbusMessage eventbusMessage){
        String threadName = Thread.currentThread().getName();
        String message = "[onMessageEvent]ThreadName:" + threadName + "--" + eventbusMessage.toString();
        tvShowPostMessage.setText(message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority = 1)
    public void onMessageEventSticky(EventbusMessage eventbusMessage){
        String threadName = Thread.currentThread().getName();
        String message = "[onMessageEventSticky]ThreadName:" + threadName + "--" + eventbusMessage.toString();
        tvShowPostMessage.setText(message);
    }
}
