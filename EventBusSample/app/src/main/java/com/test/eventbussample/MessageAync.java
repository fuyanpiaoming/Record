package com.test.eventbussample;

public class MessageAync {
    private String type;
    private String content;

    MessageAync(String type,String content){
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString(){
        return "MessageAync:type=" + type+",content=" + content;
    }
}
