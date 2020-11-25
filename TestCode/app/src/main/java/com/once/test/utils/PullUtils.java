package com.once.test.utils;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.once.test.R;
import com.once.test.mode.Book;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PullUtils {

    private static final String TAG = PullUtils.class.getSimpleName();

    /**
     * 解析xml文件
     */
    public static List<Book> parseXml(Context context) {
        List<Book> bookList = null;
        Book book = null;
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            //pull解析器
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(context.getResources().openRawResource(R.raw.book), "UTF-8");
            int eventType = xmlPullParser.getEventType();
            Log.i(TAG, "[parseXml]eventType=" + eventType);
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG,"[parseXml]document=" + xmlPullParser.getName());
                        bookList = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        Log.i(TAG,"[parseXml]start tag=" + xmlPullParser.getName());
                        if ("book".equals(xmlPullParser.getName())) {
                            book = new Book();
                            int id = Integer.parseInt(xmlPullParser.getAttributeValue(0));
                            book.setId(id);
                        } else if ("name".equals(xmlPullParser.getName())) {
                            String name = xmlPullParser.nextText();
                            assert book != null;
                            book.setName(name);
                        } else if ("page".equals(xmlPullParser.getName())) {
                            int page = Integer.parseInt(xmlPullParser.nextText());
                            assert book != null;
                            book.setPage(page);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        Log.i(TAG,"[parseXml]end tag=" + xmlPullParser.getName());
                        if ("book".equals(xmlPullParser.getName())) {
                            assert bookList != null;
                            bookList.add(book);
                            book = null;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        Log.i(TAG,"[parseXml]text =" + xmlPullParser.getText());
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    /**
     * 生成xml文件
     */
    public static void createXml(List<Book>books, OutputStream outputStream){
        try{
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(outputStream,"UTF-8");
            serializer.startDocument("UTF-8",true);
            serializer.startTag(null,"books");
            for(Book book:books){
                serializer.startTag(null,"book");
                serializer.attribute(null,"id",String.valueOf(book.getId()));

                serializer.startTag(null,"name");
                serializer.text(book.getName());
                serializer.endTag(null,"name");

                serializer.startTag(null,"page");
                serializer.text(String.valueOf(book.getPage()));
                serializer.endTag(null,"page");

                serializer.endTag(null,"book");
            }
            serializer.endTag(null,"books");
            serializer.endDocument();
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
