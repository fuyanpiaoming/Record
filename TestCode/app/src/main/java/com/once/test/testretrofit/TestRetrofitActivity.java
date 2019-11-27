package com.once.test.testretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.once.test.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRetrofitActivity extends AppCompatActivity {

    private final String TAG = "TestRetrofitActivity";

    private Button getSyncBtn;
    private Button getAsynBtn;
    private Button getWithApiBtn;
    private TextView showTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        getSyncBtn = findViewById(R.id.retrfit_get_sync_btn);
        showTxt = findViewById(R.id.retrfit_show_txt);
        getAsynBtn = findViewById(R.id.retrfit_get_async_btn);
        getWithApiBtn = findViewById(R.id.retrfit_get_withapi_btn);

        getSyncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddressSync();
            }
        });

        getAsynBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddressAsync();
            }
        });

        getWithApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddressWithApi();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getAddressSync(){
        RetrofitApi.getServerUrlSync(new ResponseListener<UrlBean>() {
            @Override
            public void success(final UrlBean parm) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestRetrofitActivity.this,"success",Toast.LENGTH_SHORT).show();
                        showTxt.setText(parm.toString());
                    }
                });
            }

            @Override
            public void failed(UrlBean parm) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestRetrofitActivity.this,"failed",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void error(Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestRetrofitActivity.this,"error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void getAddressAsync() {
        Log.i("lzj", "getAddress");

        Call<UrlBean> call = RetrofitClient.getApiService().getServerUrl();
        call.enqueue(new Callback<UrlBean>() {
            @Override
            public void onResponse(Call<UrlBean> call, Response<UrlBean> response) {
                Log.i(TAG, "onResponse code:" + response.code());
                if (response.code() == 200) {
                    final UrlBean urlBean = response.body();
                    if (urlBean != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showTxt.setText(urlBean.toString());
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<UrlBean> call, Throwable t) {
                Log.i(TAG, "failed");
            }
        });
    }

    private void getAddressWithApi() {
        RetrofitApi.getServerUrl(new Callback<UrlBean>() {
            @Override
            public void onResponse(Call<UrlBean> call, Response<UrlBean> response) {
                Toast.makeText(TestRetrofitActivity.this, "response code:" + response.code(), Toast.LENGTH_LONG).show();
                if (response.code() == 200) {
                    final UrlBean urlBean = response.body();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (urlBean != null) {
                                showTxt.setText(urlBean.toString());
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<UrlBean> call, Throwable t) {
                Toast.makeText(TestRetrofitActivity.this, "fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
