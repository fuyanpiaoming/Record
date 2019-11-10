package com.once.test.network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.once.test.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class TestHttpUrlActivity extends Activity {

    private final String TAG = "TestHttpUrlActivity";

    private Button btnGetContent;
    private Button btnGetPicture;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http_url);
        btnGetContent = (Button)findViewById(R.id.btn_http_get_content);
        btnGetContent.setOnClickListener(listener);
        imageView = (ImageView)findViewById(R.id.iv_http_show_img);

        btnGetPicture = (Button)findViewById(R.id.btn_http_get_picture);
        btnGetPicture.setOnClickListener(listener);
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
                    //imageLoadTask.execute("http://img31.mtime.cn/mg/2012/10/30/201631.37192876.jpg");
                    imageLoadTask.execute("https://github.com/fuyanpiaoming/Test/blob/master/pic/test3.jpg");
                    break;
            }

        }
    };


    private void getContent(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getWebInfo();
            }
        }).start();
    }

    private void getWebInfo(){
        try{
            URL url = new URL("https://www.baidu.com/");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine() )!= null){
                stringBuffer.append(temp);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            Log.i(TAG, "getWebInfo:" + stringBuffer.toString());
        }catch (MalformedURLException e){

        }catch (IOException e){

        }
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
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                bm = BitmapFactory.decodeStream(inputStream);
            }catch (MalformedURLException e){

            }catch (IOException e){

            }
            return bm;
        }
    }


}
