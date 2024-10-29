package com.shop.backend.classe;

public class Payment {
    private int paymentID;
    private double paymentAmount;
    private String paymentMethod;
    private String paymentStatus;

    public Payment(int paymentID, double paymentAmount, String paymentMethod) {
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending";
    }

    public void processPayment() {
        this.paymentStatus = "Confirmed";
    }

    public boolean refund() {
        if (this.paymentStatus.equals("Confirmed")) {
            this.paymentStatus = "Refunded";
            return true;
        }
        return false;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    
}
