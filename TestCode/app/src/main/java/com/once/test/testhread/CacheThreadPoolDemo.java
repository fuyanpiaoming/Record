package com.once.test.testhread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//只有非核心线程，没有核心线程，非核心线程数量不定，默认Integer.MAX_VALUE，当有新任务时会创建新线程处理任务
public class CacheThreadPoolDemo {

    private static CacheThreadPoolDemo instance;
    private ExecutorService mExecutorService;

    private CacheThreadPoolDemo(){

    }

    public static synchronized CacheThreadPoolDemo getInstance(){
        if (instance == null){
            synchronized (CacheThreadPoolDemo.class){
                if (instance == null){
                    instance = new CacheThreadPoolDemo();
                }
            }
        }
        return instance;
    }

    private void newCacheThreadPool(){
        mExecutorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    public void executeTask(Runnable runnable){
        if (mExecutorService == null){
            newCacheThreadPool();
        }
        if (mExecutorService != null){
            mExecutorService.execute(runnable);
        }
    }

    public void closeThreadPool(){
        if (mExecutorService != null){
            mExecutorService.shutdown();
        }
    }


}
