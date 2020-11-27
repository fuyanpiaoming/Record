package com.once.test.mode;

public class ChatMessage {
    public static final int TYPE_RECEIVED = 1;
    public static final int TYPE_SEND = 2;
    public int type;
    public String content;

    public ChatMessage(int type,String content){
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
