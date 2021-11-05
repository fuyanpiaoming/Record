package com.once.image.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.once.image.MainActivity;
import com.once.image.R;

public class TestNotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = TestNotificationActivity.class.getSimpleName();

    private NotificationManager mNotificationManager;
    private final String CHANNEL_ID = "test_image";
    private final String CHANNEL_NAME = "image";
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);

        button1 = findViewById(R.id.test_notification_btn1);
        button2 = findViewById(R.id.test_notification_btn2);
        button3 = findViewById(R.id.test_notification_btn3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            notificationChannel.setBypassDnd(true);
            ;
            Log.i(TAG,"createNotificationChannel canBypassDnd=" + notificationChannel.canBypassDnd());
            //notificationChannel.setAllowBubbles(true);
            //notificationChannel.setShowBadge(false);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void sendNotification() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setAutoCancel(false)
                .setContentTitle("Hello")
                .setContentInfo("Nice!!!")
                .setContentText("Welcome!!!")
                .setSmallIcon(R.drawable.leave1)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.leave5))
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setColor(Color.BLUE)
                .setOngoing(true)
                .setWhen(System.currentTimeMillis())
                .setSubText("Good!!!")
                .setNumber(5)
                .build();
        mNotificationManager.notify(1, notification);
    }


    private void sendNotification2() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setAutoCancel(true)
                .setContentTitle("Image")
                .setContentInfo("Amazing!!!")
                .setContentText("Welcome!!!")
                .setTicker("Message come!!!")
                .setSmallIcon(R.drawable.leave10)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.leave11))
                .setColor(Color.RED)
                .setWhen(System.currentTimeMillis())
                .setSubText("Great!!!")
                .setNumber(5)
                .setContentIntent(pendingIntent)
                .build();
        mNotificationManager.notify(2, notification);
    }


    private void sendNotification3() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setStyle(new NotificationCompat.MessagingStyle("Me")
                        .setConversationTitle("Team lunch")
                        .addMessage("Hi", System.currentTimeMillis(), "Good") // Pass in null for user.
                        .addMessage("What's up?", System.currentTimeMillis(), "Coworker")
                        .addMessage("Not much",System.currentTimeMillis(),new Person.Builder().setName("Goo").build()))
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.leave26)
                .build();
        mNotificationManager.notify(3, notification);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.test_notification_btn1:
                sendNotification();
                break;
            case R.id.test_notification_btn2:
                sendNotification2();
                break;
            case R.id.test_notification_btn3:
                sendNotification3();
                break;
        }
    }
}
