package com.once.test.mode;

import android.support.annotation.NonNull;



public class Book{
    private String name;
    private int id;
    private int page;

    public Book(){

    }

    public Book(String name,int id, int page){
        this.name = name;
        this.id = id;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @NonNull
    @Override
    public String toString() {
        return "Book:id=" + id +",name=" + name +",page=" + page;
    }
}
