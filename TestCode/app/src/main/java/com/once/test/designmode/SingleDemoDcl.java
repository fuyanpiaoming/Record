package com.once.test.designmode;

public class SingleDemoDcl {
    //双检锁/双重校验锁
    private volatile static SingleDemoDcl instance;

    private SingleDemoDcl(){

    }

    public static synchronized SingleDemoDcl getInstance(){
        if (instance == null){
            synchronized (SingleDemoDcl.class){
                if (instance == null){
                    instance = new SingleDemoDcl();
                }
            }
        }
        return instance;
    }
}
