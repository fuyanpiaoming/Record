package com.once.test.designmode;

public class SingleDemo2 {
    //懒汉式，线程安全
    private static SingleDemo2 instance;

    private SingleDemo2(){

    }

    public static synchronized SingleDemo2 getInstance(){
        if (instance == null){
            instance = new SingleDemo2();
        }
        return instance;
    }
}
