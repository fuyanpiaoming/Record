package com.once.test.testhread;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {

    private static ScheduledThreadPoolDemo instance;
    private ScheduledThreadPoolExecutor  mScheduledThreadPoolExecutor;

    private ScheduledThreadPoolDemo(){

    }

    public static synchronized ScheduledThreadPoolDemo getInstance(){
        if (instance == null ){
            synchronized (ScheduledThreadPoolDemo.class){
                if (instance == null){
                    instance = new ScheduledThreadPoolDemo();
                }
            }
        }
        return instance;
    }

    public ScheduledFuture executeTask(Runnable runnable,int delay){
        if (mScheduledThreadPoolExecutor == null){
            mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4);
        }
        ScheduledFuture scheduledFuture = mScheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, 50, delay, TimeUnit.MILLISECONDS);
        return scheduledFuture;
    }


    public void closeThreadPool(){
        if (mScheduledThreadPoolExecutor != null){
            mScheduledThreadPoolExecutor.shutdown();
        }
    }

}
