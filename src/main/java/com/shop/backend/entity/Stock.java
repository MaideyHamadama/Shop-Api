package com.shop.backend.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private int stockID;

    @Column(name = "available_quantity")
    private int availableQuantity;

    public Stock() {
        // Initialisation des variables si nécessaire
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_stock")
    private Date lastUpdateStock;

    // Relation One-to-One avec Product
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "idProduct")
    private Product product;

    // Constructeur
    public Stock(int availableQuantity, Date lastUpdateStock, Product product) {
        this.availableQuantity = availableQuantity;
        this.lastUpdateStock = lastUpdateStock;
        this.product = product;
    }

    // Getters et Setters
    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Date getLastUpdateStock() {
        return lastUpdateStock;
    }

    public void setLastUpdateStock(Date lastUpdateStock) {
        this.lastUpdateStock = lastUpdateStock;
    }

}