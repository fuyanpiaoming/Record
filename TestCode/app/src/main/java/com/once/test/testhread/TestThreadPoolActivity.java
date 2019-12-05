package com.once.test.testhread;

import android.app.Activity;
import android.os.SystemClock;
import android.os.Bundle;
import android.util.Log;

import com.once.test.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolActivity extends Activity {

    private ExecutorService fixedES;
    private ExecutorService cachedES;
    private ScheduledExecutorService scheduledES;
    private ExecutorService singleES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread_pool);

        createThreadPool();
        fixedES.execute(runnable);
        cachedES.execute(runnable);
        scheduledES.execute(runnable);
        singleES.execute(runnable);
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(2000);
            Log.i("ThreadTest",Thread.currentThread().getName());
        }
    };

    private void createThreadPool(){
        fixedES = newFixedThreadPool(4);
        cachedES = newCachedThreadPool();
        scheduledES = newScheduledThreadPool(4);
        singleES = newSingleThreadPool();
    }

    public ExecutorService newFixedThreadPool(int nThread){
        return new ThreadPoolExecutor(nThread,nThread,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    }

    public ExecutorService newCachedThreadPool(){
        return new ThreadPoolExecutor(0,Integer.MAX_VALUE, 60L,TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    public ScheduledExecutorService newScheduledThreadPool(int corePoolSize){
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    public ExecutorService newSingleThreadPool(){
        return new ThreadPoolExecutor(1,1,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    }

}
