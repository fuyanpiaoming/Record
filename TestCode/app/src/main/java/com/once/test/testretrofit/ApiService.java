package com.once.test.testretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/skyee_robot/settings/server/get_all_url")
    Call<UrlBean> getServerUrl();
}
