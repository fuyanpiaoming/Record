package com.once.test.utils;

import android.content.Context;
import android.util.Log;

import com.once.test.R;
import com.once.test.mode.Book;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DomUtils {

    private static final String TAG = DomUtils.class.getSimpleName();

    public static List<Book> parseXml(Context context){
        List<Book>bookList = new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //document解析器
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //document文档
            Document document = documentBuilder.parse(context.getResources().openRawResource(R.raw.book));
            //节点列表
            NodeList nodeList = document.getElementsByTagName("book");
            Log.i(TAG,"[parseXml]nodeList len=" + nodeList.getLength());
            for(int i =0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                Log.i(TAG,"[parseXml]element=" + element.getAttribute("id"));
                Book book = new Book();
                book.setId(Integer.parseInt(element.getAttribute("id")));
                NodeList nodeListChild = element.getChildNodes();
                Log.i(TAG,"[parseXml]nodeListChild len=" + nodeListChild.getLength());
                for (int j =0; j < nodeListChild.getLength(); j++){
                    Node node = nodeListChild.item(j);
                    Log.i(TAG,"[parseXml]node=" + node.toString());
                    if (node.getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) node;
                        if ("name".equals(childElement.getNodeName())){
                            book.setName(childElement.getFirstChild().getNodeValue());
                        }else if("page".equals(childElement.getNodeName())){
                            book.setPage(Integer.parseInt(childElement.getFirstChild().getNodeValue()));
                        }
                    }
                }
                bookList.add(book);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
