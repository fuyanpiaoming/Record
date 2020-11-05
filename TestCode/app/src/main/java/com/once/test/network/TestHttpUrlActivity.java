package com.once.test.network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.once.test.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class TestHttpUrlActivity extends Activity {

    private final String TAG = "TestHttpUrlActivity";

    private Button btnGetContent;
    private Button btnGetPicture;
    private ImageView imageView;
    private TextView contentTxt;
    private Button btnTestGet;
    private Button btnTestPost;

    private final String urlAdd = "https://www.baidu.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http_url);
        btnGetContent = (Button)findViewById(R.id.btn_http_get_content);
        btnGetContent.setOnClickListener(listener);
        imageView = (ImageView)findViewById(R.id.iv_http_show_img);

        contentTxt = (TextView)findViewById(R.id.txt_http_show_result);

        btnGetPicture = (Button)findViewById(R.id.btn_http_get_picture);
        btnGetPicture.setOnClickListener(listener);

        btnTestGet = (Button)findViewById(R.id.btn_http_test_get);
        btnTestGet.setOnClickListener(listener);

        btnTestPost = (Button)findViewById(R.id.btn_http_test_post);
        btnTestPost.setOnClickListener(listener);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.btn_http_get_content:
                    getContent();
                    break;
                case R.id.btn_http_get_picture:
                    ImageLoadTask imageLoadTask = new ImageLoadTask();
                    imageLoadTask.execute("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604568835919&di=ea8eb8fad84822cd999710755e327665&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F56%2F12%2F01300000164151121576126282411.jpg");
                    break;

                case R.id.btn_http_test_get:
                    testHttpGet();
                    break;
                case R.id.btn_http_test_post:
                    testHttpPost();
                    break;
            }

        }
    };

    public void testPostParam(View view){
        testPostWithParam();
    }

    public void testPostJson(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(urlAdd);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    httpURLConnection.connect();

                    //设置参数
                    String body = "{userName:haha, password:123456}";
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8"));
                    bufferedWriter.write(body);
                    bufferedWriter.close();

                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        final String result = is2string(inputStream);
                        inputStream.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                contentTxt.setText(result);
                            }
                        });
                    }
                    httpURLConnection.disconnect();
                }catch (IOException e){

                }

            }
        }).start();

    }


    private void getContent(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getWebInfo();
            }
        }).start();
    }

    private void getWebInfo(){
        HttpURLConnection httpURLConnection = null;

        try{
            URL url = new URL("https://www.baidu.com/");
            httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            final StringBuffer stringBuffer = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine() )!= null){
                stringBuffer.append(temp);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            Log.i(TAG, "getWebInfo:" + stringBuffer.toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    contentTxt.setText(stringBuffer.toString());
                }
            });
        }catch (MalformedURLException e){

        }catch (IOException e){

        }finally {
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }
        }
    }

    //get 请求方法的使用
    private void testHttpGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        final String result = is2string(inputStream);
                        inputStream.close();
                        Log.i(TAG,"[testHttpGet]result="+ result);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                contentTxt.setText(result);
                            }
                        });
                    }

                }catch (Exception e){

                }finally {
                    if (httpURLConnection != null){
                        httpURLConnection.disconnect();
                    }
                }

            }
        }).start();
    }

    //post方法
    private void testHttpPost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL("https://www.baidu.com/");
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setConnectTimeout(10);
                    httpURLConnection.setReadTimeout(10);
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        final String result = is2string(inputStream);
                        Log.i(TAG,"[testHttpPost]result=" + result);
                        inputStream.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                contentTxt.setText(result);
                            }
                        });
                    }
                    httpURLConnection.disconnect();
                }catch (IOException e){

                }
            }
        }).start();
    }

    //post()包含参数
    private void testPostWithParam(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAdd);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();

                    //设置参数
                    String  body = "userName=hohoo&password=123456";
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8"));
                    bufferedWriter.write(body);
                    bufferedWriter.close();

                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        final String result = is2string(inputStream);
                        inputStream.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                contentTxt.setText(result);
                            }
                        });
                    }
                    httpURLConnection.disconnect();
                }catch (IOException e){

                }


            }
        }).start();
    }

    private String is2string(InputStream inputStream)throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line + "\n");
        }
        return stringBuffer.toString();
    }


    private class ImageLoadTask extends AsyncTask<String, Integer, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG,"onPreExecute");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.i(TAG,"onPostExecute");
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG,"onProgressUpdate");
        }

        @Override
        protected void onCancelled(Bitmap bitmap) {
            super.onCancelled(bitmap);
            Log.i(TAG,"onCancelled");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(TAG,"onCancelled2");
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Log.i(TAG,"doInBackground string[0]=" + strings[0]);
            Bitmap bm = null;
            HttpURLConnection httpURLConnection = null;
            try{
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(5000);
                Log.i(TAG,"[doInBackground]set param");
                InputStream inputStream = httpURLConnection.getInputStream();
                Log.i(TAG,"[doInBackground]inputString=" + inputStream.toString());
                bm = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            }catch (MalformedURLException e){

            }catch (IOException e){

            }finally {
                if (httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            }
            return bm;
        }
    }


}
