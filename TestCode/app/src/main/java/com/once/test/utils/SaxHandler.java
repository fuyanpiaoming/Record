package com.once.test.utils;

import android.util.Log;

import com.once.test.mode.Book;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaxHandler extends DefaultHandler {

    private final String TAG = SaxHandler.class.getSimpleName();

    private Book book;
    private List<Book> bookList;

    private String tag = null;

    @Override
    public void startDocument() throws SAXException {
        Log.i(TAG,"[startDocument]");
        bookList = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        Log.i(TAG,"[endDocument]");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Log.i(TAG,"[startElement]uri=" + uri +",localName=" + localName +",qName=" + qName);
        if(localName.equals("book")){
            book = new Book();
            //获取标签属性值
            book.setId(Integer.parseInt(attributes.getValue("id")));
        }
        tag = localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Log.i(TAG,"[endElement]uri=" + uri +",localName=" + localName +",qName=" + qName);
        if(localName.equals("book")){
            bookList.add(book);
            book = null;
        }
        tag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        Log.i(TAG, "[characters]ch=" + Arrays.toString(ch)+",start=" + start + ",length=" + length);
        if(tag != null){
            String data = new String(ch,start,length);
            //Log.i(TAG,"[characters]tag=" + tag +",data=" + data);
            if (tag.equals("name")){
                book.setName(data);
            }else if(tag.equals("page")){
                book.setPage(Integer.parseInt(data));
            }
        }
    }

    public List<Book> getBookList(){
        return bookList;
    }
}
