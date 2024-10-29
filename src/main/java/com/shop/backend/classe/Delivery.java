package com.shop.backend.classe;

public class Delivery {
    private int deliveryID;
    private String deliveryAddress;
    private String deliveryType;
    private String deliveryStatus;
    private int paymentID;
    private int orderID;

    public Delivery(int deliveryID, String deliveryAddress, String deliveryType, int orderID) {
        this.deliveryID = deliveryID;
        this.deliveryAddress = deliveryAddress;
        this.deliveryType = deliveryType;
        this.deliveryStatus = "Pending";
        this.orderID = orderID;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void updateDeliveryStatus(String status) {
        this.deliveryStatus = status;
    }
}
