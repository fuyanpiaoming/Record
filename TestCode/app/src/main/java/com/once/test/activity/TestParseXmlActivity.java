package com.once.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.once.test.R;
import com.once.test.mode.Book;
import com.once.test.utils.SaxHandler;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class TestParseXmlActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvContent;
    private List<Book>bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_parse_xml);
        findViewById(R.id.btn_sax).setOnClickListener(this);
        findViewById(R.id.btn_pull).setOnClickListener(this);
        findViewById(R.id.btn_dom).setOnClickListener(this);

        tvContent = findViewById(R.id.tv_xml_content);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sax:
                try {
                    bookList = testSaxParse();
                    showResult(bookList);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_pull:
                break;
            case R.id.btn_dom:
                break;
        }
    }

    private void showResult(List<Book>books){
        StringBuilder stringBuilder = new StringBuilder();
        for(Book book: books){
            stringBuilder.append(book.getId()).append(".").append(book.getName()).append(".").append(book.getPage()).append("\n");
        }
        tvContent.setText(stringBuilder.toString());
    }

    private List<Book> testSaxParse() throws IOException, ParserConfigurationException, SAXException {
        InputStream inputStream = getResources().openRawResource(R.raw.book);
        SaxHandler saxHandler = new SaxHandler();
        //获取SAX解析器工厂实例
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //创建SAX解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();
        //解析
        saxParser.parse(inputStream,saxHandler);
        inputStream.close();
        return saxHandler.getBookList();
    }
}