package com.once.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.once.test.R;
import com.once.test.fastjson.ModeC;
import com.once.test.mode.Book;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFastJsonActivity extends AppCompatActivity {

    private final String TAG = TestFastJsonActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fast_json);

        ModeC modeC = new ModeC();
        modeC.test();

        testBeanToArray();
        testArray();
    }

    private void testJson() {
        //序列化方法: String jsonString = JSON.toJSONString(obj);
        //反序列化方法: Book book = JSON.parseObject(jsonString,Book.class);
        //泛型反序列化: List<Book> list = JSON.parseObject("...", new TypeReference<List<Book>>() {});
        //关闭循环引用检测:JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect)
        //Book book = JSON.parseObject(jsonString, Book.class, Feature.DisableCircularReferenceDetect)

        String bookStr = "{\"name\":\"Good\", \"id\":1, \"page\":100}";
        Book book = JSON.parseObject(bookStr, Book.class);
        Log.i(TAG, "[testJson]book=" + book.toString());
    }

    //FastJson处理日期
    private void testJsonDate() {
        //方法1
        String str1 = JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
        //方法2
        String str2 = JSON.toJSONString(new Date(), SerializerFeature.UseISO8601DateFormat);
        //方法3，全局修改时间格式
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
        String str3 = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
        Log.i(TAG, "[testJsonDate]str1=" + str1);
        Log.i(TAG, "[testJsonDate]str2=" + str2);
        Log.i(TAG, "[testJsonDate]str3=" + str3);
    }

    //主要序列化方法toJSONString()
    //将Java对象序列化为JSON字符串，支持各种各种Java基本类型和JavaBean
    private void testSerializer() {
        String bookStr = "{\"name\":\"Good\", \"id\":1, \"page\":100}";
        //public static String toJSONString(Object object, SerializerFeature... features);
        Book book = new Book("ABC", 1, 100);
        String jsonStr = JSON.toJSONString(book, SerializerFeature.EMPTY);
        String jsonStr2 = JSON.toJSONString(book);

        //将Java对象序列化为JSON字符串，返回JSON字符串的utf-8 bytes
        //public static byte[] toJSONBytes(Object object, SerializerFeature... features);
        byte[] bytes = JSON.toJSONBytes(book, SerializerFeature.NotWriteDefaultValue);

        //将Java对象序列化为JSON字符串，写入到Writer中
        //public static void writeJSONString(Writer writer,Object object,SerializerFeature... features);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.openFileOutput("test", MODE_PRIVATE)));
            JSON.writeJSONString(bufferedWriter, book, SerializerFeature.WriteNullStringAsEmpty);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //将Java对象序列化为JSON字符串，按UTF-8编码写入到OutputStream中
        //public static final int writeJSONString(OutputStream os, Object object, SerializerFeature... features);
        try {
            OutputStream outputStream = openFileOutput("data", MODE_PRIVATE);
            JSON.writeJSONString(outputStream, book, SerializerFeature.EMPTY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //主要反序列化方法：parseObject()
    //反序列化方法:将JSON字符串反序列化为JavaBean
    private void testDeserialize() {
        String bookStr = "{\"name\":\"A\", \"id\":1, \"page\":20}";
        String bookStrArray = "[{\"name\":\"A\", \"id\":1, \"page\":100},{\"name\":\"B\", \"id\":2, \"page\":200},{\"name\":\"C\", \"id\":3, \"page\":300}]";
        JSONObject jsonObject = JSON.parseObject(bookStr);

        //parse POJO
        Book book = JSON.parseObject(bookStr, Book.class);

        Type type = new TypeReference<List<Book>>() {
        }.getType();
        List<Book> bookList = JSON.parseObject(bookStrArray, type);

    }

    //超大JSON数组序列化
    private void testWriterArray() {
        try {
            JSONWriter jsonWriter = new JSONWriter(new FileWriter("/temp/data.json"));
            jsonWriter.startArray();
            for (int i = 0; i < 1000; i++) {
                jsonWriter.writeValue(new Book());
            }
            jsonWriter.endArray();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //超大json对象序列化
    private void testWriterObject() {
        try {
            JSONWriter jsonWriter = new JSONWriter(new FileWriter("/temp/data.json"));
            jsonWriter.startObject();
            for (int i = 0; i < 1000 * 1000; i++) {
                jsonWriter.writeKey("val" + i);
                jsonWriter.writeValue(new Book());
            }
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取数据
    private void testReaderArray() {
        try {
            JSONReader jsonReader = new JSONReader(new FileReader("/temp/data.json"));
            jsonReader.startArray();
            while (jsonReader.hasNext()) {
                Book book = jsonReader.readObject(Book.class);
            }
            jsonReader.endArray();
            jsonReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //读取对象
    private void testReaderObject() {
        try {
            JSONReader jsonReader = new JSONReader(new FileReader("/temp/data.json"));
            jsonReader.startObject();
            while (jsonReader.hasNext()) {
                String key = jsonReader.readString();
                Book book = jsonReader.readObject(Book.class);
            }
            jsonReader.endObject();
            jsonReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //BeanToArray 模式 只输出value值，不输出key值
    private void testBeanToArray() {
        Book book = new Book("Code", 1, 100);
        String jsonStr = JSON.toJSONString(book, SerializerFeature.BeanToArray);
        Log.i(TAG, "[testBeanToArray]jsonStr=" + jsonStr);
        //jsonStr=[1,"Code",100]
        Book book1 = JSON.parseObject(jsonStr, Book.class, Feature.SupportArrayToBean);
        Log.i(TAG, "[testBeanToArray]book1=" + book1.toString());
    }

    //数组，列表与json的转换
    private void testArray() {
        Book book = new Book("A", 1, 100);
        Book book1 = new Book("B", 2, 200);
        Book book2 = new Book("C", 3, 300);

        Book[] books = new Book[3];
        books[0] = book;
        books[1] = book1;
        books[2] = book2;

        List<Book> books1 = new ArrayList<>();
        books1.add(book);
        books1.add(book1);
        books1.add(book2);
        //数组
        String jsonStr1 = JSON.toJSONString(books);
        Log.i(TAG, "[testArray]jsonStr1=" + jsonStr1);
        //[{"id":1,"name":"A","page":100},{"id":2,"name":"B","page":200},{"id":3,"name":"C","page":300}]
        List<Book> bookList = JSON.parseArray(jsonStr1, Book.class);
        for (Book b : bookList) {
            Log.i(TAG, "[testArray]b=" + b.toString());
        }
        //列表
        String jsonStr2 = JSON.toJSONString(books1);
        Log.i(TAG, "[testArray]jsonStr2=" + jsonStr2);
        List<Book> bookList1 = JSON.parseArray(jsonStr2, Book.class);
        for (Book b1 : bookList1) {
            Log.i(TAG, "[testArray]b1=" + b1.toString());
        }

        //map
        Map<String, Book> map = new HashMap<>();
        map.put("1",book);
        map.put("2",book1);
        map.put("3",book2);
        String jsonStr3 = JSON.toJSONString(map);
        Log.i(TAG,"[testArray]jsonStr3=" + jsonStr3);
        Map<String,Book>map1 = (Map<String,Book>)JSON.parse(jsonStr3);
        for(String key: map1.keySet()){
            Log.i(TAG,"[testArray]val=" + map1.get(key));
        }
    }

}