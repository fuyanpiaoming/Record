package com.once.test.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.once.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TestEventBus2Activity extends Activity {

    private final String TAG = "TestEventBus2Activity";

    private TextView showTxt;
    private Button getBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus2);

        showTxt = findViewById(R.id.eventbus_show_txt2);
        getBtn = findViewById(R.id.eventbus_show_btn2);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //eventbus发送MessageEvent事件
                EventBus.getDefault().post(new MessageEvent("haha!!!"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
