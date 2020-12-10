package com.once.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.once.test.R;
import com.once.test.mode.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestJsonActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = TestJsonActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);
        findViewById(R.id.btn_gson).setOnClickListener(this);
        findViewById(R.id.btn_jsonObject).setOnClickListener(this);
        findViewById(R.id.btn_fast_json).setOnClickListener(this);
        findViewById(R.id.btn_jsonArray).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gson:
                useGson();
                break;
            case R.id.btn_jsonObject:
                useJsonObject();
                break;
            case R.id.btn_jsonArray:
                useJsonArray1();
                break;
            case R.id.btn_fast_json:
                break;
        }
    }

    private void useGson() {
        //解析单个对象
        //{"id":1,"name":"Good","page":100}
        Gson gson = new Gson();
        Book book = new Book("Good", 1, 100);
        String s1 = gson.toJson(book);
        Log.i(TAG, "[useGson]s1 =" + s1);
        Book book1 = gson.fromJson(s1, Book.class);
        Log.i(TAG, "[useGson]book1 =" + book1.toString());
        //解析列表
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("A", 1, 100));
        bookList.add(new Book("B", 2, 200));
        bookList.add(new Book("C", 3, 300));

        Type type = new TypeToken<List<Book>>() {
        }.getType();
        String s2 = gson.toJson(bookList, type);
        //[{"id":1,"name":"A","page":1},{"id":2,"name":"B","page":2},{"id":3,"name":"C","page":3}]
        Log.i(TAG, "[useGson]s2 =" + s2);
        List<Book> bookList1 = gson.fromJson(s2, type);
        for (Book book2 : bookList1) {
            Log.i(TAG, "[useGson]book2 =" + book2.toString());
        }
    }

    private void useJsonObject() {
        String str = "{\"name\":\"A\", \"id\":1, \"page\":20}";
        try {
            JSONObject jsonObject = new JSONObject(str);
            String jsonStr = jsonObject.toString();
            String jsonStr1 = jsonObject.toString(4);
            Toast.makeText(this,jsonStr1,Toast.LENGTH_SHORT).show();

            Book book = new Book();
            book.setName(jsonObject.getString("name"));
            book.setId(jsonObject.getInt("id"));
            book.setPage(jsonObject.getInt("page"));
            Toast.makeText(this,book.toString(),Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void useJsonArray(){
        String str = "[{\"name\":\"A\", \"id\":1, \"page\":100},{\"name\":\"B\", \"id\":2, \"page\":200},{\"name\":\"C\", \"id\":3, \"page\":300}]";
        try {
            JSONArray jsonArray = new JSONArray(str);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Book book = new Book();
                book.setName(jsonObject.getString("name"));
                book.setId(jsonObject.getInt("id"));
                book.setPage(jsonObject.getInt("page"));
                Log.i(TAG,"[useJsonArray]BOOK:" + book.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void useJsonArray1(){

        try {
            JSONArray jsonArray = new JSONArray();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("name","A");
//            jsonObject.put("id",1);
//            jsonObject.put("page",100);
//            jsonArray.put(jsonObject);
            for(int i = 0; i < 5; i++){
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("name","Jia" + i);
                jsonObject1.put("id",i);
                jsonObject1.put("page",(i+1)*100);
                jsonArray.put(jsonObject1);
            }
            Toast.makeText(this,jsonArray.toString(4),Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}