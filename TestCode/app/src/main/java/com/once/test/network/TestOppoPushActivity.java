package com.once.test.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.once.test.R;




import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class TestOppoPushActivity extends AppCompatActivity {

    private final String TAG = TestOppoPushActivity.class.getSimpleName();

    private final String OPPO_APP_KEY = "123456";
    private final String OPPO_MASTER_SECRET = "abcdefg";
    private String oppoRegId = "789456123";
    private String oppoToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_oppo_push);
    }

    private void getToken() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "https://api.push.oppomobile.com/server/v1/auth";
                long timeStamp = System.currentTimeMillis();
                String sign = getSHA256(OPPO_APP_KEY + timeStamp + OPPO_MASTER_SECRET);
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(20000);
                    connection.setReadTimeout(20000);
                    connection.setRequestMethod("POST");
                    //至少要设置的两个请求头
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    //connection.setRequestProperty("Content-Length", data.length()+"");

                    String msgBody = MessageFormat.format(
                            "app_key={0}&sign={1}&timestamp={2}",
                            URLEncoder.encode(OPPO_APP_KEY, "UTF-8"),
                            URLEncoder.encode(sign, "UTF-8"),
                            URLEncoder.encode(Long.toString(timeStamp), "UTF-8"));

                    //post的方式提交实际上是留的方式提交给服务器
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(msgBody.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    //获得结果码
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        //请求成功
                        InputStream inputStream = connection.getInputStream();
                        String result = changeInputStream(inputStream, "utf-8");
                        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data");
                        if (jsonObject != null) {
                            oppoToken = jsonObject.getString("auth_token");
                        }
                        inputStream.close();
                    } else {
                        //请求失败
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void addTag(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "https://api-device.push.heytapmobi.com/server/v1/device/add_tags";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(20000);
                    connection.setReadTimeout(20000);
                    connection.setRequestMethod("POST");

                    //至少要设置的两个请求头
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    connection.setRequestProperty("auth_token", oppoToken);
                    //post的方式提交实际上是留的方式提交给服务器
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name","hello");
                    jsonObject.put("desc","ni hao ya");
                    String msgBody = jsonObject.toString();
                    Log.i(TAG,"[addTag]msgBody=" + msgBody);
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(msgBody.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    //获得结果码
                    int responseCode = connection.getResponseCode();

                    if(responseCode ==200){
                        //请求成功
                        InputStream inputStream = connection.getInputStream();
                        String result = changeInputStream(inputStream,"utf-8");
                        JSONObject jsonObject1 = JSONObject.parseObject(result);
                        Log.i(TAG,"[addTag]messaage=" + jsonObject1.getString("message"));
                        inputStream.close();
                    }else {
                        //请求失败
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void addTagGroup(final List<String> tagList){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "https://api-device.push.heytapmobi.com/server/v1/device/add_tag_group";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(30000);
                    connection.setReadTimeout(30000);
                    connection.setRequestMethod("POST");

                    //至少要设置的两个请求头
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    connection.setRequestProperty("auth_token", oppoToken);
                    //post的方式提交实际上是留的方式提交给服务器
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < tagList.size();i++){
                        stringBuffer.append(tagList.get(i));
                        if (i != tagList.size() -1) {
                            stringBuffer.append(";");
                        }
                    }
                    String tags = stringBuffer.toString();

                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("group","helloGroup");
                    jsonObject1.put("tags",tags);
                    jsonObject1.put("desc","ni zai na ge qun?");
                    String msgBody = jsonObject1.toString();
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(msgBody.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    //获得结果码
                    int responseCode = connection.getResponseCode();
                    if(responseCode ==200){
                        //请求成功
                        InputStream is = connection.getInputStream();
                        String result = changeInputStream(is,"utf-8");
                        JSONObject jsonObject = JSONObject.parseObject(result);
                        is.close();
                        //订阅话题和取消订阅话题
                        //dingyue
                    }else {
                        //请求失败
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void subscribeTags(final List<String>tagList){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "https://api-device.push.heytapmobi.com/server/v1/device/subscribe_tags";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(30000);
                    connection.setReadTimeout(30000);
                    connection.setRequestMethod("POST");

                    //至少要设置的两个请求头
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    connection.setRequestProperty("auth_token", oppoToken);
                    //post的方式提交实际上是留的方式提交给服务器
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    //传递json格式的参数给服务器
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < tagList.size();i++){
                        stringBuffer.append(tagList.get(i));
                        if (i != tagList.size() - 1){
                            stringBuffer.append(";");
                        }
                    }
                    String tags = stringBuffer.toString();
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("registration_id",oppoRegId);
                    jsonObject1.put("tags",tags);
                    String msgBody = jsonObject1.toString();
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(msgBody.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    //获得结果码
                    int responseCode = connection.getResponseCode();
                    if(responseCode ==200){
                        //请求成功
                        InputStream is = connection.getInputStream();
                        String result = changeInputStream(is,"utf-8");
                        JSONObject jsonObject = JSONObject.parseObject(result);
                        is.close();
                    }else {
                        //请求失败
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void unsubscribeTags(final List<String>tagList){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "https://api-device.push.heytapmobi.com/server/v1/device/unsubscribe_tags";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(30000);
                    connection.setReadTimeout(30000);
                    connection.setRequestMethod("POST");

                    //至少要设置的两个请求头
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    connection.setRequestProperty("auth_token", oppoToken);
                    //post的方式提交实际上是留的方式提交给服务器
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    //传递json格式的参数给服务器
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < tagList.size();i++){
                        stringBuffer.append(tagList.get(i));
                        if (i != tagList.size() - 1){
                            stringBuffer.append(";");
                        }
                    }
                    String tags = stringBuffer.toString();
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("registration_id",oppoRegId);
                    jsonObject1.put("tags",tags);
                    String msgBody = jsonObject1.toString();
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(msgBody.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    //获得结果码
                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200){
                        //请求成功
                        InputStream is = connection.getInputStream();
                        String result = changeInputStream(is,"utf-8");
                        JSONObject jsonObject = JSONObject.parseObject(result);
                        is.close();
                    }else {
                        //请求失败
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void getAllTags(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "https://api-device.push.heytapmobi.com/server/v1/device/get_all_tags";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(30000);
                    connection.setReadTimeout(30000);
                    connection.setRequestMethod("POST");

                    //至少要设置的两个请求头
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    connection.setRequestProperty("auth_token", oppoToken);
                    //post的方式提交实际上是留的方式提交给服务器
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("registration_id",oppoRegId);
                    String msgBody = jsonObject1.toString();
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(msgBody.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    //获得结果码
                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200){
                        //请求成功
                        InputStream is = connection.getInputStream();
                        String result = changeInputStream(is,"utf-8");
                        Log.i("lzjun","[getOppoAllTags]result=" + result);
                        //OppoAllTagsBean oppoAllTagsBean = JSONObject.parseObject(result, OppoAllTagsBean.class);
                    }else {
                        //请求失败
                        Log.i("lzjun","[getOppoAllTags]fail");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    /**
     * 将byte转为16进制
     */
    private String byte2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }

    private String changeInputStream(InputStream inputStream, String encode) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = "";
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, len);
                }
                result = new String(outputStream.toByteArray(), encode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}