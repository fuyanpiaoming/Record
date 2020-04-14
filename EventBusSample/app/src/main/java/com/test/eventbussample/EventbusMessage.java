package com.test.eventbussample;

public class EventbusMessage {
    private int id;
    private String name;

    public EventbusMessage(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "EventbusMessage:id=" + id + "/name=" + name;
    }
}
