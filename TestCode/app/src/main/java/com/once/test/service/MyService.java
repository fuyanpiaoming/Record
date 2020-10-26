package com.once.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {

    private final String TAG = MyService.class.getSimpleName();

    private final IBinder binder = new LocalBinder();
    private final Random random = new Random();

    public class LocalBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }


    @Override
    public void onCreate() {
        Log.i(TAG,"[onCreate]");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"[onBind]");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"[onUnbind]");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG,"[onRebind]");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"[onDestroy]");
        super.onDestroy();
    }

    public int getRandomNumber(){
        return random.nextInt(50);
    }


}
