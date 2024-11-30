package com.shop.backend.dto;

public class ProductCartRequest {
    private int productId;
    private int quantity;
    private Integer userId; // Optionnel si pas lié à un utilisateur

    // Getters et Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
