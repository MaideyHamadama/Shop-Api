package com.shop.backend.dto;

import com.shop.backend.entity.ShoppingCartLine;

public class ShoppingCartLineDTO {

    private Long id;
    private int productId;
    private int quantity;
    private double totalPrice; // TTC
    private double totalPriceExcludingVAT; // HT


    public ShoppingCartLineDTO() {
    }

    /**
     * Constructor that initializes the ShoppingCartLineDTO from a ShoppingCartLine entity.
     *
     * @param line The ShoppingCartLine entity to map.
     */
    public ShoppingCartLineDTO(ShoppingCartLine line) {
        this.quantity = line.getQuantity();
        this.totalPrice = line.getTotalPrice(); // TTC
        this.totalPriceExcludingVAT = line.getTotalPriceExcludingVAT(); // HT
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPriceExcludingVAT() {
        return totalPriceExcludingVAT;
    }

    public void setTotalPriceExcludingVAT(double totalPriceExcludingVAT) {
        this.totalPriceExcludingVAT = totalPriceExcludingVAT;
    }
}

