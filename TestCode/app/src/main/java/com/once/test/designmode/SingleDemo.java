package com.once.test.designmode;

public class SingleDemo {
    //懒汉式，线程不安全
    private static SingleDemo instance;

    private SingleDemo(){

    }

    public static SingleDemo getInstance(){
        if (instance == null){
            instance = new SingleDemo();
        }
        return instance;
    }

}

