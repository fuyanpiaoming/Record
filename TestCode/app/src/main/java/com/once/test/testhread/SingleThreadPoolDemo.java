package com.once.test.testhread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//线程池中只有一个核心线程， 所有任务在同一线程中按顺序执行
public class SingleThreadPoolDemo {

    private static SingleThreadPoolDemo instance;
    private static ExecutorService mExecutorService;

    private SingleThreadPoolDemo(){

    }

    public static synchronized  SingleThreadPoolDemo getInstance(){
        if (instance == null){
            synchronized (SingleThreadPoolDemo.class){
                if (instance == null){
                    instance = new SingleThreadPoolDemo();
                }
            }
        }
        return instance;
    }

    private void newSingleThreadPool(){
        if (mExecutorService == null){
            mExecutorService = new ThreadPoolExecutor(1,1,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        }
    }

    public void executeTask(Runnable runnable){
        if (mExecutorService == null){
            newSingleThreadPool();
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
