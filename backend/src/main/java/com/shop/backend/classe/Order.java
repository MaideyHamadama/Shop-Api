package com.shop.backend.classe;

import java.time.LocalDate;

public class Order {
    private int orderID;
    private int userID;
    private LocalDate orderDate;
    private double orderTotal;
    private String status;
    private String deliveryAddress;
    private ShoppingCart cart;

    public Order(int orderID, int userID, double orderTotal, String deliveryAddress, ShoppingCart cart) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = LocalDate.now();
        this.orderTotal = orderTotal;
        this.status = "Pending";
        this.deliveryAddress = deliveryAddress;
        this.cart = cart;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getUserID() {
        return userID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public String getStatus() {
        return status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public ShoppingCart getCart() {
        return cart;
    }
    
}
