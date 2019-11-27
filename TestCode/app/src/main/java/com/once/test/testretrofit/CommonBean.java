package com.once.test.testretrofit;

import com.google.gson.annotations.SerializedName;

public class CommonBean<T> {
    @SerializedName("data")
    private T data;
    @SerializedName("errorCode")
    private String errorCode;
    @SerializedName("msg")
    private String msg;
    @SerializedName("successed")
    private boolean successed;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccessed() {
        return successed;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "data='" + data + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                ", successed=" + successed +
                '}';
    }
}
