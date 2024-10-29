package com.shop.backend.classe;

public class Visitor {
    protected String role;

    public Visitor() {
        this.role = "Visitor";
    }

    public void browseProducts() {
        // Code to display products
    }

    public void addToCart(Product product) {
        // Code to add product to cart
    }

    public void deleteFromCart(Product product) {
        // Code to delete product from cart
    }
}

