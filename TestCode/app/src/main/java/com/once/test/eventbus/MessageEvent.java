package com.once.test.eventbus;

public class MessageEvent {

    private String message;

    public MessageEvent(){

    }

    public MessageEvent(String message){
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
