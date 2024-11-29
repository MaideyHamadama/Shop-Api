package com.shop.backend.entity;

import jakarta.persistence.*;

/**
 * Represents a line in the shopping cart, linking a product variant to a quantity and total price.
 */
@Entity
@Table(name = "shopping_cart_line")
public class ShoppingCartLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false) // Foreign key to ProductVariant
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", nullable = false) // Foreign key to ShoppingCart
    private ShoppingCart shoppingCart;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    // ===========================
    //           Methods
    // ===========================

    // Méthode pour calculer le prix HT (hors TVA)
    public double getTotalPriceExcludingVAT() {
        double tvaRate = shoppingCart.getTvaRate(); // Récupérer le taux du panier
        return totalPrice / (1 + tvaRate); // Calcul HT
    }


    // ===========================
    //      Constructors
    // ===========================
    public ShoppingCartLine() {
    }

    public ShoppingCartLine(ShoppingCart cart, Product product, int quantity) {
        this.product = product;
        this.shoppingCart = cart;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    // ===========================
    //      Getters & Setters
    // ===========================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTvaRate() {
        return shoppingCart.getTvaRate();
    }

    @Override
    public String toString() {
        return "ShoppingCartLine{" +
                "id=" + id +
                ", product=" + product +
                ", shoppingCart=" + shoppingCart +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
