package com.once.test.designmode;

public class SingleDemo4 {
    //登记式/静态内部类 效果同双检锁机制

    private SingleDemo4(){

    }

    private static class SingleHolder{
        private static final SingleDemo4 INSTANCE = new SingleDemo4();
    }

    public static final SingleDemo4 getInstance(){
        return SingleHolder.INSTANCE;
    }

}
