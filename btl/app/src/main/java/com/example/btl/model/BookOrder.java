package com.example.btl.model;

import java.io.Serializable;

public class BookOrder implements Serializable {
    private String _id;
    private User user;
    private Book book;
    private int quantity;
    private int status;

    public BookOrder() {
    }

    public BookOrder(User user, Book book, int quantity, int status) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
        this.status = status;
    }

    public BookOrder(String _id, User user, Book book, int quantity, int status) {
        this._id = _id;
        this.user = user;
        this.book = book;
        this.quantity = quantity;
        this.status = status;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
