package com.once.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.once.test.mode.Book;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class TestFastJsonActivity extends AppCompatActivity {

    private final String TAG = TestFastJsonActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fast_json);
    }

    private void testJson(){
        //序列化方法: String jsonString = JSON.toJSONString(obj);
        //反序列化方法: Book book = JSON.parseObject(jsonString,Book.class);
        //泛型反序列化: List<Book> list = JSON.parseObject("...", new TypeReference<List<Book>>() {});
        //关闭循环引用检测:JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect)
        //Book book = JSON.parseObject(jsonString, Book.class, Feature.DisableCircularReferenceDetect)

        String bookStr = "{\"name\":\"Good\", \"id\":1, \"page\":100}";
        Book book = JSON.parseObject(bookStr,Book.class);
        Log.i(TAG,"[testJson]book=" + book.toString());
    }

    //FastJson处理日期
    private void testJsonDate(){
        //方法1
        String str1 = JSON.toJSONStringWithDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss");
        //方法2
        String str2 = JSON.toJSONString(new Date(), SerializerFeature.UseISO8601DateFormat);
        //方法3，全局修改时间格式
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
        String str3 = JSON.toJSONString(new Date(),SerializerFeature.WriteDateUseDateFormat);
        Log.i(TAG,"[testJsonDate]str1=" + str1);
        Log.i(TAG,"[testJsonDate]str2=" + str2);
        Log.i(TAG,"[testJsonDate]str3=" + str3);
    }

    //主要序列化方法toJSONString()
    //将Java对象序列化为JSON字符串，支持各种各种Java基本类型和JavaBean
    private void testSerializer(){
        String bookStr = "{\"name\":\"Good\", \"id\":1, \"page\":100}";
        //public static String toJSONString(Object object, SerializerFeature... features);
        Book book = new Book("ABC",1,100);
        String jsonStr = JSON.toJSONString(book,SerializerFeature.EMPTY);
        String jsonStr2 = JSON.toJSONString(book);

        //将Java对象序列化为JSON字符串，返回JSON字符串的utf-8 bytes
        //public static byte[] toJSONBytes(Object object, SerializerFeature... features);
        byte[] bytes = JSON.toJSONBytes(book,SerializerFeature.NotWriteDefaultValue);

        //将Java对象序列化为JSON字符串，写入到Writer中
        //public static void writeJSONString(Writer writer,Object object,SerializerFeature... features);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.openFileOutput("test",MODE_PRIVATE)));
            JSON.writeJSONString(bufferedWriter,book,SerializerFeature.WriteNullStringAsEmpty);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //将Java对象序列化为JSON字符串，按UTF-8编码写入到OutputStream中
        //public static final int writeJSONString(OutputStream os, Object object, SerializerFeature... features);
        try{
            OutputStream outputStream = openFileOutput("data",MODE_PRIVATE);
            JSON.writeJSONString(outputStream,book,SerializerFeature.EMPTY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //主要反序列化方法：parseObject()
    //反序列化方法:将JSON字符串反序列化为JavaBean
    private void testDeserialize(){
        String bookStr = "{\"name\":\"A\", \"id\":1, \"page\":20}";
        String bookStrArray = "[{\"name\":\"A\", \"id\":1, \"page\":100},{\"name\":\"B\", \"id\":2, \"page\":200},{\"name\":\"C\", \"id\":3, \"page\":300}]";
        JSONObject jsonObject = JSON.parseObject(bookStr);

        //parse POJO
        Book book = JSON.parseObject(bookStr,Book.class);

        Type type = new TypeReference<List<Book>>(){}.getType();
        List<Book>bookList = JSON.parseObject(bookStrArray, type);

    }

}