package com.once.test.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;

import com.once.test.R;
import com.once.test.broadcast.MyReceiver;

public class TestNotificationActivity extends Activity implements View.OnClickListener {

    private final String TAG = TestNotificationActivity.class.getSimpleName();

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();

        findViewById(R.id.btn_show1).setOnClickListener(this);
        findViewById(R.id.btn_show2).setOnClickListener(this);
        findViewById(R.id.btn_show3).setOnClickListener(this);
        findViewById(R.id.btn_show4).setOnClickListener(this);
        findViewById(R.id.btn_show5).setOnClickListener(this);
        findViewById(R.id.btn_show6).setOnClickListener(this);
        findViewById(R.id.btn_show7).setOnClickListener(this);
        findViewById(R.id.btn_show8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show1:
                createNotification();
                break;
            case R.id.btn_show2:
                createNotification2();
                break;
            case R.id.btn_show3:
                createNotification3();
                break;
            case R.id.btn_show4:
                createNotificationGroup();
                break;
            case R.id.btn_show5:
                createFullScreenNotification();
                break;
            case R.id.btn_show6:
                createNotification4();
                break;
            case R.id.btn_show7:
                createNotification5();
                break;
            case R.id.btn_show8:
                openChannelSetting();
                break;
        }

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "test_code";
            String describe = "this is a channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("test", name, importance);
            notificationChannel.setDescription(describe);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Ni zai na ne?")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.app_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.book))
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1, notification);
    }

    private void createNotification2() {

        Intent intent = new Intent(this, NotificationNormalAckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test");
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText("Ruo ji ruo li");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.book));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(2, builder.build());
    }

    //展开通知通过设置setStyle()
    private void createNotification3() {

        Intent intent1 = new Intent(this, TestCustomProviderActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, intent1, 0);

        Intent intent2 = new Intent(this, MyReceiver.class);
        intent2.putExtra("my_action", "SayHello");
        intent2.setAction("say");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test");
        builder.setContentTitle("QinShi");
        builder.setContentText("Gui gu zhong heng");
        builder.setSmallIcon(R.drawable.menu);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.book));
        builder.setContentIntent(pendingIntent1);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.addAction(R.drawable.menu, "moon", pendingIntent2);
        builder.setAutoCancel(true);
        //builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.book)));
        //builder.setStyle(new NotificationCompat.BigTextStyle().bigText("zhu zi bai jia"));
        builder.setStyle(new NotificationCompat.InboxStyle().addLine("line1").addLine("line2"));
        Notification notification = builder.build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(3, notification);
    }


    private void createNotificationGroup() {
        final int SUMMARY_ID = 0;
        String NOTIFICATION_GROUP_KEY = "com.once.test.group";

        Notification notification = new NotificationCompat.Builder(this, "test")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("LiuSha")
                .setContentText("wei zhuang")
                .setGroup(NOTIFICATION_GROUP_KEY)
                .build();

        Notification notification1 = new NotificationCompat.Builder(this, "test")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("LiuSha")
                .setContentText("Zi nv")
                .setGroup(NOTIFICATION_GROUP_KEY)
                .build();

        Notification notification2 = new NotificationCompat.Builder(this, "test")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.InboxStyle().addLine("line1").addLine("line2").setBigContentTitle("New message").setSummaryText("ju san liu sha"))
                .setGroup(NOTIFICATION_GROUP_KEY)
                .setGroupSummary(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,notification);
        notificationManagerCompat.notify(2,notification1);
        notificationManagerCompat.notify(SUMMARY_ID,notification2);
    }

    private void createFullScreenNotification(){
        Intent intent = new Intent(this,NotificationAckActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"test");
        builder.setContentTitle("Nong Jia");
        builder.setContentText("Nong jia liu tang");
        builder.setSmallIcon(R.drawable.book,1000);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.book));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setFullScreenIntent(pendingIntent,true);
        Notification notification = builder.build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,notification);
    }


    @SuppressLint("NewApi")
    private void createNotification4(){
        Intent intent = new Intent(this, NotificationNormalAckActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this,"test")
                .setSmallIcon(R.drawable.menu)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.book))
                .setContentTitle("Qin shi")
                .setContentText("he zong lian heng")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setColor(Color.GREEN)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,notification);
    }

    private void createNotification5(){

        Intent intent = new Intent(this, NotificationAckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this,"test")
                .setSmallIcon(R.drawable.menu)
                .setContentTitle("Chan hong")
                .setContentText("Bai bu fei jian")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,notification);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void openChannelSetting(){
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel("test");
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE,getPackageName());
        Log.i(TAG,"[openChannelSetting]channel id=" + notificationChannel.getId());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID,notificationChannel.getId());
        startActivity(intent);
    }
    
}