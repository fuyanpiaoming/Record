package com.once.test.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.once.test.R;
import com.once.test.service.ForegroundService;
import com.once.test.service.HelloService;
import com.once.test.service.MessengerService;
import com.once.test.service.MyService;

public class TestServiceActivity extends Activity {

    private final String TAG = TestServiceActivity.class.getSimpleName();

    private MyService myService;
    private boolean mBound = false;

    private Messenger messenger;
    private boolean messengerBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
        findViewById(R.id.btn_start_service).setOnClickListener(onClickListener);
        findViewById(R.id.btn_stop_service).setOnClickListener(onClickListener);

        findViewById(R.id.btn_bind_service).setOnClickListener(onClickListener);
        findViewById(R.id.btn_unbind_service).setOnClickListener(onClickListener);
        findViewById(R.id.btn_gen_number).setOnClickListener(onClickListener);

        findViewById(R.id.btn_bind_messenger).setOnClickListener(onClickListener);
        findViewById(R.id.btn_unbind_messenger).setOnClickListener(onClickListener);
        findViewById(R.id.btn_say).setOnClickListener(onClickListener);

        findViewById(R.id.btn_open_foreground_service).setOnClickListener(onClickListener);
        findViewById(R.id.btn_close_foreground_service).setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_start_service:
                    intent = new Intent(TestServiceActivity.this, HelloService.class);
                    startService(intent);
                    break;
                case R.id.btn_stop_service:
                    intent = new Intent(TestServiceActivity.this,HelloService.class);
                    stopService(intent);
                    break;
                case R.id.btn_bind_service:
                    intent = new Intent(TestServiceActivity.this,MyService.class);
                    bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                    break;
                case R.id.btn_unbind_service:
                    if(mBound){
                        unbindService(serviceConnection);
                    }
                    break;
                case R.id.btn_gen_number:
                    if (myService != null){
                        Log.i(TAG,"Gen number value =" + myService.getRandomNumber());
                    }
                    break;
                case R.id.btn_bind_messenger:
                    intent = new Intent(TestServiceActivity.this,MessengerService.class);
                    bindService(intent,messengerServiceConn,BIND_AUTO_CREATE);
                    break;
                case R.id.btn_unbind_messenger:
                    if (messengerBound){
                        unbindService(messengerServiceConn);
                    }
                    break;
                case R.id.btn_say:
                    sayHello();
                    break;
                case R.id.btn_open_foreground_service:
                    intent = new Intent(TestServiceActivity.this, ForegroundService.class);
                    startService(intent);
                    break;
                case R.id.btn_close_foreground_service:
                    intent = new Intent(TestServiceActivity.this, ForegroundService.class);
                    stopService(intent);
                    break;
            }
        }
    };

    //使用Binder
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder localBinder = (MyService.LocalBinder) service;
            myService = localBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    //使用Messenger进行进程间通信
    private ServiceConnection messengerServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            messengerBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            messengerBound = false;
        }
    };

    private void sayHello(){
        if (!messengerBound){
            return;
        }
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO,0,0);
        try{
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}