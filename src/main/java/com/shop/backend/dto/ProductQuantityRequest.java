package com.shop.backend.dto;

/**
 * Classe de transfert de données (DTO) pour représenter une requête de mise à jour de quantité de produit.
 */
public class ProductQuantityRequest {

    private int quantity;

    // ===========================
    //    Getters et Setters
    // ===========================

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}