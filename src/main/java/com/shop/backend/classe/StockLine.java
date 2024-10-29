package com.shop.backend.classe;
public class StockLine {
    private ProductVariant variant;
    private int quantity;
    private int alertThreshold;

    public StockLine(ProductVariant variant, int quantity, int alertThreshold) {
        this.variant = variant;
        this.quantity = quantity;
        this.alertThreshold = alertThreshold;
    }

    public void updateStock(int newQuantity) {
        this.quantity = newQuantity;
    }

    public boolean checkStockAlert() {
        return quantity <= alertThreshold;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAlertThreshold() {
        return alertThreshold;
    }
    
}
