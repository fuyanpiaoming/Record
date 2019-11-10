package com.once.test.network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.once.test.R;

import org.json.JSONObject;

//Gson用法参照https://github.com/google/gson/blob/master/UserGuide.md

public class TestVolley extends Activity {

    private final String TAG = "VolleyTag";

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_volley);
        textView = (TextView)findViewById(R.id.test_volley_txt);
        imageView = (ImageView)findViewById(R.id.test_volley_img);
    }

    public void testStringRequest(View view) {
        Log.i(TAG,"testGet1");
        //StringRequest的用法
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.baidu.com/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,"reponse:" + response);
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });
        //将请求添加在请求队列中
        requestQueue.add(stringRequest);
    }

    public void testJsonRequest(View view){
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://www.baidu.com/", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG,"response:" + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"error:" + error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void testImageRequest(View view){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        ImageRequest imageRequest = new ImageRequest("http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                },0,0,Bitmap.Config.RGB_565, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.i(TAG,"testImageRequest error=" + error.getMessage());
                imageView.setImageResource(R.drawable.app_icon);
            }
        });

        requestQueue.add(imageRequest);
    }


    public void testImageLoad(View view){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        ImageLoader imageLoader = new ImageLoader(requestQueue,new BitmapCache());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.app_icon,R.drawable.app_icon);
        imageLoader.get("https://github.com/fuyanpiaoming/Test/blob/master/pic/test4.jpg",listener);
    }


    private class BitmapCache implements ImageLoader.ImageCache{

        private LruCache<String, Bitmap>mCache;

        public BitmapCache(){
            int maxsize = 8*1024*1024;
            mCache = new LruCache<String, Bitmap>(maxsize){
                @Override
                public int sizeOf(String key,Bitmap bitmap){
                    return bitmap.getRowBytes()*bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url){
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url,Bitmap bitmap){
            mCache.put(url,bitmap);
        }
    }
}
