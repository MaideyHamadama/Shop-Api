package com.shop.backend.entity;

import jakarta.persistence.*;

/**
 * Represents a line in the stock management system for a specific product variant.
 */
@Entity
@Table(name = "stock_line")
public class StockLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_line_id")
    private Long id; // Ajout d'un ID pour identifier chaque ligne de stock de manière unique

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false) // Clé étrangère pour le variant de produit
    private ProductVariant variant;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "alert_threshold", nullable = false)
    private int alertThreshold;

    // Constructeurs
    public StockLine() {
    }

    public StockLine(ProductVariant variant, int quantity, int alertThreshold) {
        this.variant = variant;
        this.quantity = quantity;
        this.alertThreshold = alertThreshold;
    }

    // ===========================
    //        Méthodes JPA
    // ===========================

    public void updateStock(int newQuantity) {
        this.quantity = newQuantity;
    }

    public boolean checkStockAlert() {
        return quantity <= alertThreshold;
    }

    // ===========================
    //      Getters & Setters
    // ===========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAlertThreshold() {
        return alertThreshold;
    }

    public void setAlertThreshold(int alertThreshold) {
        this.alertThreshold = alertThreshold;
    }

    @Override
    public String toString() {
        return "StockLine{" +
                "id=" + id +
                ", variant=" + variant +
                ", quantity=" + quantity +
                ", alertThreshold=" + alertThreshold +
                '}';
    }
}
