package com.colombopizza.sasmitha.colombopizzachef.model;

public class Order {

    private String orderID;
    private String orderItems;
    private String notes ;

    public Order(String orderID, String orderItems, String notes) {
        this.orderID = orderID;
        this.orderItems = orderItems;
        this.notes = notes;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
