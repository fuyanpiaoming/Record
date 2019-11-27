package com.once.test.testretrofit;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitApi {


    public static void execSync(ResponseListener listener, Call call){
        try{
            Response<UrlBean>response = call.execute();
            if (response.code() != 200){
                UrlBean urlBean = response.body();
                if (urlBean != null && listener != null){
                    listener.failed(response.body());
                }
            }else{
                if (listener != null){
                    listener.success(response.body());
                }
            }
        }catch (IOException e){
            if (listener != null){
                listener.error(e);
            }
        }
    }


    public static void getServerUrl(Callback<UrlBean> listener) {
        Call call = RetrofitClient.getApiService().getServerUrl();
        call.enqueue(listener);
    }

    public static void getServerUrlSync(final ResponseListener<UrlBean> responseListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = RetrofitClient.getApiService().getServerUrl();
                execSync(responseListener, call);
            }
        }).start();
    }

}
