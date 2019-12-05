package com.once.test.testhread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//只有核心线程，没有非核心线程，核心线程不会被回收，只有当线程池关闭了，才回关闭
public class FixedThreadPoolDemo {

    private static FixedThreadPoolDemo instance;
    private ExecutorService mExecutorService;

    private FixedThreadPoolDemo(){

    }

    public static synchronized FixedThreadPoolDemo getInstance(){
        if (instance == null){
            synchronized (FixedThreadPoolDemo.class){
                if (instance == null){
                    instance = new FixedThreadPoolDemo();
                }
            }
        }
        return instance;
    }

    private void newFixedThreadPool(int nThread){
        if (mExecutorService == null){
            mExecutorService = new ThreadPoolExecutor(nThread, nThread, 0l, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        }
    }

    public void executeTask(Runnable runnable){
        if (mExecutorService == null){
            newFixedThreadPool(5);
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
