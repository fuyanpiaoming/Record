package com.once.test.network;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.once.test.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//测试网址：//https://api.github.com/
public class TestOkhttpActivity extends Activity {

    private final String TAG = "TestOkhttpTag";

    private OkHttpClient okHttpClient;
    private final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_okhttp);
        init();
    }

    private void init(){
        File sdcache = getExternalCacheDir();
        Log.i(TAG,"cache dir =" + sdcache.getAbsolutePath());
        int size = 10 * 1024 *1024;
        OkHttpClient.Builder builder =  new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.cache(new Cache(sdcache.getAbsoluteFile(), size));
        okHttpClient = builder.build();
    }

    //get异步请求
    public void testGet(View view){
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        requestBuilder.method("GET",null);
        final Request request = requestBuilder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()){
                    String str = response.cacheResponse().toString();
                    Log.i(TAG,"str1=" + str);
                }else{
                    String str = response.networkResponse().toString();
                    Log.i(TAG,"str2=" + str);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestOkhttpActivity.this, "请求成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //post异步请求
    public void testPost(View view){
        //创建请求体
        RequestBody requestBody = new FormBody.Builder().add("size", "10").build();
        //创建请求
        Request request = new Request.Builder().url("http://api.1-blog.com/biz/bizserver/article/list.do")
                .post(requestBody)
                .build();
        //创建call对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(TestOkhttpActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.i(TAG,"post str=" + str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestOkhttpActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //上传文件
    public void testPostFile(View view){
        File file = new File("/sdcard/haha.txt");
        //创建请求
        Request request = new Request.Builder().url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,file))
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestOkhttpActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestOkhttpActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                    }
                });
                Log.i(TAG,"Post file:" + response.body().toString());
            }
        });
    }

    //异步下载文件
    public void testDownFile(View view){
        //创建请求
        Request request = new Request.Builder().url("https://github.com/fuyanpiaoming/Test/blob/master/pic/test3.jpg")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestOkhttpActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try{
                    fileOutputStream = new FileOutputStream("/sdcard/haha.jpg");
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while((len = inputStream.read(buffer)) != -1){
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                    Log.i(TAG,"haha:" + fileOutputStream.getFD());
                }catch (IOException e){

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestOkhttpActivity.this,"下载成功", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


}
