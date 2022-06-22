package com.example.btl.model;

import java.io.Serializable;

public class Author implements Serializable {
    private String _id;
    private String name;
    private String year;

    public Author() {
    }

    public Author(String name, String year) {
        this.name = name;
        this.year = year;
    }

    public Author(String _id, String name, String year) {
        this._id = _id;
        this.name = name;
        this.year = year;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
