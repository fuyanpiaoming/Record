package com.test.eventbussample;

public class MessagePost {
    private String type;
    private String content;

    MessagePost(String type,String content){
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString(){
        return "MessagePost:type=" + type+",content=" + content;
    }
}
