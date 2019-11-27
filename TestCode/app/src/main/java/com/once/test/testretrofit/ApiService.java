package com.once.test.testretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/haha/server")
    Call<UrlBean> getServerUrl();
}
