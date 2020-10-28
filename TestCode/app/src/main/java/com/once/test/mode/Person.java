package com.once.test.mode;

public class Person {

    private String name;
    private int imageId;

    public Person(String name,int id){
        this.name = name;
        this.imageId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
