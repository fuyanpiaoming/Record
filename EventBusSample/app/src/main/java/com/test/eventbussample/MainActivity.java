package com.test.eventbussample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * https://github.com/greenrobot/EventBus
 * 1.
 * subscriber（订阅者）:EventBus注册时传入的对象
 * event（事件）：订阅事件的类型EventMessage，可以作为订阅方法的参数和EventBust.post方法的参数
 * publisher（发布者）：EventBus的post方法,会发布EventMessage类型的事件
 * 2.
 * EventBus 的register（）和unregister（）必须成对出现，不能重复注册和解注册
 * 3.@Subscribe注解的方法
 * 参数只能有一个，是订阅的事件类型，比如EventMessage类型
 * 必须用public修饰，不能被static 和abstract修饰
 * 4.
 * 注解@Subscribe(threadMode = ThreadMode.MAIN,sticky = false,priority = 1)
 * threadMode，sticky,priority三个参数不是必须的，可以根据需要加上
 * 5.
 * 订阅者（Subscriber）需要调用register（），unregister（）
 * 发布者（publisher） 需要调用post（），postSticky（）方法发送事件
 * 通过@Subscribe注解定义接收事件的方法
 * 6.
 * priority值越大优先级越高，优先级高的先接收到事件，优先级是针对订阅者处理订阅事件的方法是在同一线程中
 * sticky表示是否是粘性事件。
 * sticky=true,表示发布者可以调用postSticky()先发布事件，订阅者之后注册，依旧可以收到之前发布者发布的事件
 * sticky=false,表示需要订阅者需要先注册，才能收到发布者发布的事件
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //private final String TAG = MainActivity.class.getSimpleName();
    private final String TAG = "PostTest";

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

    /**
     *ThreadMode.MAIN_ORDERED表示订阅者订阅事件方法是在主线程上执行，且是非阻塞的
     */
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED,sticky = false,priority = 0)
    public void onMessageEventMainOrder(EventbusMessage eventbusMessage){
        String threadName = Thread.currentThread().getName();
        String message = "[onMessageEventMainOrder]ThreadName:" + threadName + "--" + eventbusMessage.toString();
        tvShowPostMessage.setText(message);
        Log.i(TAG,message);
    }

    /**
     * ThreadMode.MAIN表示
     * 1.onMessageEventMainSticky在主线程上执行
     * 2.是阻塞的方式，即发布一个事件，处理一个事件，然后再发布事件，处理事件
     *
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false,priority = 0)
    public void onMessageEventMain(MessageMain messageMain){
        String threadName = Thread.currentThread().getName();
        String message = "[onMessageEventMain]ThreadName:" + threadName + "--" + messageMain.toString();
        tvShowPostMessage.setText(message);
        Log.i(TAG,message);
    }

    /**
     *ThreadMode.POSTING表示发布事件是在哪个线程，则onMessagePosting在哪个线程上执行，是默认线程模式
     */
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = false,priority = 0)
    public void onMessagePosting(MessagePost messagePost){
        String threadName = Thread.currentThread().getName();
        final String message = "[onMessagePosting]ThreadName:" + threadName + "--" + messagePost.toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvShowPostMessage.setText(message);
            }
        });
        Log.i(TAG,message);
    }


    /**
     *ThreadMode.ASYNC表示无论发布者无论在哪个线程发布事件，订阅者都会重新起一个线程执行订阅事件
     */
    @Subscribe(threadMode = ThreadMode.ASYNC,sticky = false, priority = 0)
    public void onMessageAsync(MessageAync messageAync){
        String threadName = Thread.currentThread().getName();
        final String message = "[onMessageAsync]ThreadName:" + threadName + "--" + messageAync.toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvShowPostMessage.setText(message);
            }
        });
        Log.i(TAG,message);
    }

    /**
     *ThreadMode.BACKGROUND表示
     * 1.发布者发布事件是在主线程，则订阅者会起子线程处理订阅事件的方法
     * 2.发布者发布事件是在子线程，则订阅者会在该子线程上处理订阅事件
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND,sticky = false,priority = 0)
    public void onMessageEventBackground(MessageBackground messageBackground){
        String threadName = Thread.currentThread().getName();
        final String message = "[onMessageEventBackground]ThreadName:" + threadName + "--" + messageBackground.toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvShowPostMessage.setText(message);
            }
        });
        Log.i(TAG,message);
    }

    /**
     * 粘性事件
     *
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority = 0)
    public void onMessageMainSticky(EventbusMessage eventbusMessage){
        String threadName = Thread.currentThread().getName();
        String message = "[onMessageMainSticky]ThreadName:" + threadName + "--" + eventbusMessage.toString();
        tvShowPostMessage.setText(message);
        Log.i(TAG,message);
    }

    /**
     * 优先级
     *
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false,priority = 1)
    public void onMessageMainPriority(MessageMain messageMain){
        String threadName = Thread.currentThread().getName();
        String message = "[onMessageMainPriority]ThreadName:" + threadName + "--" + messageMain.toString();
        tvShowPostMessage.setText(message);
        Log.i(TAG,message);
    }

}
