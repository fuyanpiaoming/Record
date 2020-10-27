package com.once.test.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.once.test.R;
import com.once.test.activity.NotificationNormalAckActivity;

public class ForegroundService extends Service {

    private final String TAG = ForegroundService.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"[onCreate]");
        createChannel();
        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"[onStartCommand]");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"[onDestroy]");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("test","Hello",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("This is a channel");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void showNotification(){
        Intent intent = new Intent(this, NotificationNormalAckActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        Notification notification = new NotificationCompat.Builder(this,"test")
                .setSmallIcon(R.drawable.ic_data)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.book))
                .setContentTitle("Foreground Service")
                .setContentText("Foreground service doing something")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setTicker("service run")
                .build();

        startForeground(1,notification);
    }
}
