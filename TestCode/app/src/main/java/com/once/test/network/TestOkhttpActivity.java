package com.once.test.network;

import android.app.Activity;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.once.test.R;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http2.Header;
import okio.BufferedSink;

//测试网址：//https://api.github.com/

/*
  get请求步骤
  1.获取okHttpClient对象
  2.构建Request对象
  3.构建Call对象
  4.通过Call.enqueue(callback)方法来提交异步请求；execute()方法实现同步请求

  post请求步骤
  1.获取okHttpClient对象
  2.创建RequestBody
  3.构建Request对象
  4.构建Call对象
  5.通过Call.enqueue(callback)方法来提交异步请求；execute()方法实现同步请求

 */
public class TestOkhttpActivity extends Activity {

    private final String TAG = "TestOkhttpTag";

    private OkHttpClient okHttpClient;
    private final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private final String testUrl = "//https://api.github.com/";

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


    //get 异步请求
    private void getAsync(){
        //创建okhttpclient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(15,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .cache(new Cache(getExternalCacheDir(),10 *1024 *1024))
                .build();
        //创建Request对象
        Request request = new Request.Builder()
                .url(testUrl)
                .get()
                .build();
        //创建call对象
        Call call = okHttpClient.newCall(request);

        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    String string = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(TestOkhttpActivity.this, "请求成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    //get同步请求
    private void getSync(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(getExternalCacheDir(), 10 *1024 *1024))
                .build();

        final Request request = new Request.Builder()
                .url(testUrl)
                .method("GET", null)
                .build();

        final Call call = okHttpClient.newCall(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    String str = response.body().string();
                    Log.i(TAG,"[getSync]str=" + str);
                }catch (IOException e){

                }
            }
        }).start();
    }


    //post 异步
    private void postAsync(){
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(getExternalCacheDir(), 10 *1024*1024))
                .build();
        //创建RequestBody(Form表单)
        RequestBody requestBody = new FormBody.Builder()
                .add("username","haha")
                .add("pwd","123")
                .build();

        //创建Request
        Request request = new Request.Builder()
                .url(testUrl)
                .post(requestBody)
                .build();
        //创建Call对象
        Call call = okHttpClient.newCall(request);

        //post异步执行
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                //切换到主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestOkhttpActivity.this, "请求成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //post 同步
    private void postSync(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(getExternalCacheDir(), 10 *1024*1024))
                .build();

        RequestBody requestBody = new FormBody.Builder()
                .add("username","haha")
                .add("pwd","123")
                .build();

        Request request = new Request.Builder()
                .url(testUrl)
                .post(requestBody)
                .build();

        final Call call = okHttpClient.newCall(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Response response = call.execute();
                    String string = response.body().string();
                }catch (IOException e){

                }
            }
        }).start();
    }

    //请求头处理（Header）以及超时和缓冲处理以及响应处理
    private void testHeader(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(getExternalCacheDir(), 10 * 1024 *1024))
                .build();

        RequestBody requestBody = new FormBody.Builder()
                .add("user","haha")
                .add("pwd","123")
                .build();

        Request request = new Request.Builder()
                .url(testUrl)
                .addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8")
                .header("User-Agent","Okhttp Example")
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //响应行
                Log.e(TAG,"protocol:" + response.protocol() + ",code =" + response.code() + ",message=" + response.message());
                //响应头
                Headers headers = response.headers();
                for (int i = 0; i < headers.size();i++){
                    Log.e(TAG,"name:" + headers.name(i) + ",value:" + headers.value(i));
                }
                //响应体
                String string = response.body().string();
                Log.d(TAG,"string:" + string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestOkhttpActivity.this, "请求成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //请求体处理
    private void testRequestBody() {
        //1.POST方式提交String/JSON
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        String content = "pno=1&ps=50&dtype=son&key=4a7cf244fd7efbd17ecbf0cb8e4d1c85";
        RequestBody requestBody = RequestBody.create(mediaType, content);

        //POST方式提交JSON：传递JSON同时设置son类型头
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/json;charset-utf-8"), "");
        //必须添加json类型头
        //request.addHeader("Content-Type", "application/json")

        //post方式提交无参
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset-utf-8"),"");

        //post方式提交流
        RequestBody requestBody3 = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return MediaType.parse("application/x-www-form-urlencoded;utf-8");
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8("pno=1&ps=50&dtype=son&key=4a7cf244fd7efbd17ecbf0cb8e4d1c85");
            }
        };

        //post方式提交表单
        RequestBody requestBody4 = new FormBody.Builder()
                .add("pno", "1")
                .add("ps","50")
                .add("dtype","son")
                .add("key","4a7cf244fd7efbd17ecbf0cb8e4d1c85")
                .build();


        //post方式提交文件
        MediaType mediaType1 = MediaType.parse("text/x-markdown;utf-8");
        File file = new File("/sdcard/haha.txt");
        RequestBody requestBody5 = RequestBody.create(mediaType,file);

        //分块提交
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition","form-data;name=\"title\""),RequestBody.create(null,"Ha"))
                .addPart(Headers.of("Content-Disposition","form-data;name=\"img\""), RequestBody.create(MediaType.parse("img/png"),new File("hah/haha.png")))
                .build();
    }

}
