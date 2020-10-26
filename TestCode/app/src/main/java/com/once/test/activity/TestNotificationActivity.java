package com.once.test.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

import com.once.test.R;
import com.once.test.broadcast.MyReceiver;

public class TestNotificationActivity extends Activity implements View.OnClickListener{

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);

        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();

        findViewById(R.id.btn_show1).setOnClickListener(this);
        findViewById(R.id.btn_show2).setOnClickListener(this);
        findViewById(R.id.btn_show3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show1:
                createNotification();
                break;
            case R.id.btn_show2:
                createNotification2();
                break;
            case R.id.btn_show3:
                createNotification3();
                break;
        }

    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "test_code";
            String describe = "this is a channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("test",name,importance);
            notificationChannel.setDescription(describe);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(){
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Ni zai na ne?")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.app_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.book))
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1,notification);
    }

    private void createNotification2(){
        Intent intent = new Intent(this,TestCustomProviderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"test");
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText("Ruo ji ruo li");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.book));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(2,builder.build());
    }

    private void createNotification3(){

        Intent intent1 = new Intent(this,TestCustomProviderActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this,0,intent1,0);

        Intent intent2 = new Intent(this, MyReceiver.class);
        intent2.putExtra("my_action","SayHello");
        intent2.setAction("say");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this,0,intent2,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test");
        builder.setContentTitle("QinShi");
        builder.setContentText("Gui gu zhong heng");
        builder.setSmallIcon(R.drawable.app_icon);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.book));
        builder.setContentIntent(pendingIntent1);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.addAction(R.drawable.book, "moon", pendingIntent2);
        builder.setAutoCancel(true);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.book)));
        //builder.setStyle(new NotificationCompat.BigTextStyle().bigText("zhu zi bai jia"));
        Notification notification = builder.build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(3,notification);
    }


}