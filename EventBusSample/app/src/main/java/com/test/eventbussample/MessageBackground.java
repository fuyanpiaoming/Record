package com.test.eventbussample;

public class MessageBackground {
    private String type;
    private String content;

    MessageBackground(String type,String content){
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString(){
        return "MessageBackground:type=" + type+",content=" + content;
    }
}
