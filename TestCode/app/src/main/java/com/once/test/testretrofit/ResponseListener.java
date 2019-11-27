package com.once.test.testretrofit;

public interface ResponseListener<T> {

    void success(T parm);
    void failed(T parm);
    void error(Throwable t);

}
