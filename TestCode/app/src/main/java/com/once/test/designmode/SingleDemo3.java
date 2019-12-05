package com.once.test.designmode;

public class SingleDemo3 {
    //饿汉式
    private static SingleDemo3 instance = new SingleDemo3();

    private SingleDemo3(){

    }

    public static SingleDemo3 getInstance(){
        return instance;
    }
}
