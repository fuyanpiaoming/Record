package com.test.eventbussample;

public class MessageMain {
    private String type;
    private String content;

    MessageMain(String type,String content){
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString(){
        return "MessageMain:type=" + type+",content=" + content;
    }
}
