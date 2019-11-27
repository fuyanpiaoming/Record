package com.once.test.testretrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static ApiService apiService;
    private final String url = "http://www.baidu.com";

    private RetrofitClient(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }


    public static ApiService getApiService(){
        if (apiService == null){
            synchronized (RetrofitClient.class){
                if (apiService == null){
                    new RetrofitClient();
                }
            }

        }
        return apiService;
    }

}
