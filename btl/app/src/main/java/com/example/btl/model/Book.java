package com.example.btl.model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private String _id;
    private String name;
    private String publisher;
    private Date publishedDate;
    private String generes;
    private Author author;
    private float price;
    private String image;

    public Book() {
    }

    public Book(String name, String publisher, Date publishedDate, String generes, Author author, float price, String imgage) {
        this.name = name;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.generes = generes;
        this.author = author;
        this.price = price;
        this.image = imgage;
    }

    public Book(String _id, String name, String publisher, Date publishedDate, String generes, Author author, float price, String image) {
        this._id = _id;
        this.name = name;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.generes = generes;
        this.author = author;
        this.price = price;
        this.image = image;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getGeneres() {
        return generes;
    }

    public void setGeneres(String generes) {
        this.generes = generes;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImgage(String image) {
        this.image = image;
    }

    public String toString(){
        return _id + ", " + name + ", " + image + ", " + price + ", " + author.getId() + ", " + author.getName() + ", " + author.getYear();
    }
}
