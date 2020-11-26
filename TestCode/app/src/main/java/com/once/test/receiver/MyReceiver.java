package com.once.test.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"MyReceiver receive ok",Toast.LENGTH_SHORT).show();
        //abortBroadcast();发送有序广播时调用该方法截断广播
    }
}
