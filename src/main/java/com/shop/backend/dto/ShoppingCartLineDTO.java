package com.shop.backend.dto;

import com.shop.backend.entity.ShoppingCartLine;

/**
 * Classe de transfert de données (DTO) pour représenter une ligne de panier d'achat.
 */
public class ShoppingCartLineDTO {

    private int id;
    private int productId;
    private int quantity;
    private double totalPrice; // TTC
    private double totalPriceExcludingVAT; // HT
    private double productUnitPrice; // TTC
    private String productName;

    /**
     * Constructeur par défaut.
     */
    public ShoppingCartLineDTO() {
    }

    /**
     * Constructeur qui initialise un {@link ShoppingCartLineDTO} à partir d'une entité {@link ShoppingCartLine}.
     *
     * @param line L'entité {@link ShoppingCartLine} à mapper.
     */
    public ShoppingCartLineDTO(ShoppingCartLine line) {
        this.id = line.getId();
        this.productId = line.getProduct().getIdProduct();
        this.productName = line.getProduct().getProductName();
        this.quantity = line.getQuantity();
        this.totalPrice = line.getTotalPrice(); // TTC
        this.totalPriceExcludingVAT = line.getTotalPriceExcludingVAT(); // HT
        this.productUnitPrice = line.getProduct().getPrice(); // TTC
    }

    // ============================
    // Getters & Setters
    // ============================

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
