package com.example.btl.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String _id;
    private User user;
    private ArrayList<BookOrder> bookOrders;
    private String address;
    private int status;
    private int shipment;

    public Order() {
    }

    public Order(User user, ArrayList<BookOrder> bookOrders, String address, int status, int shipment) {
        this.user = user;
        this.bookOrders = bookOrders;
        this.address = address;
        this.status = status;
        this.shipment = shipment;
    }

    public Order(String _id, User user, ArrayList<BookOrder> bookOrders, String address, int status, int shipment) {
        this._id = _id;
        this.user = user;
        this.bookOrders = bookOrders;
        this.address = address;
        this.status = status;
        this.shipment = shipment;
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

    public ArrayList<BookOrder> getBookOrders() {
        return bookOrders;
    }

    public void setBookOrders(ArrayList<BookOrder> bookOrders) {
        this.bookOrders = bookOrders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getShipment() {
        return shipment;
    }

    public void setShipment(int shipment) {
        this.shipment = shipment;
    }
}
