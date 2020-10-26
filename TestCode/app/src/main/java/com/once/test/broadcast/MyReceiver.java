package com.once.test.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private final String TAG = MyReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"[onReceive]intent=" + intent);
        Toast.makeText(context,"You are coming!!!",Toast.LENGTH_SHORT).show();

    }
}
