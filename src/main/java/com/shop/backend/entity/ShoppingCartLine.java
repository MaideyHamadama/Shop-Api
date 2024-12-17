package com.shop.backend.entity;

import jakarta.persistence.*;

/**
 * Représente une ligne dans le panier d'achat, associant un produit à une quantité et un prix total.
 */
@Entity
@Table(name = "shopping_cart_line")
public class ShoppingCartLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false) // Clé étrangère vers Product
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", nullable = false) // Clé étrangère vers ShoppingCart
    private ShoppingCart shoppingCart;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "size")
    private String size;

    // ===========================
    //         Méthodes
    // ===========================

    /**
     * Calcule le prix hors TVA (HT) pour cette ligne.
     *
     * @return Le prix total hors TVA.
     */
    public double getTotalPriceExcludingVAT() {
        double tvaRate = shoppingCart.getTvaRate(); // Récupère le taux de TVA du panier
        return totalPrice / (1 + tvaRate); // Calcul HT
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

    // ===========================
    //       Constructeurs
    // ===========================

    /**
     * Constructeur par défaut.
     */
    public ShoppingCartLine() {
    }

    /**
     * Constructeur pour initialiser une ligne de panier avec ses attributs.
     *
     * @param cart     Le panier associé.
     * @param product  Le produit de la ligne.
     * @param quantity La quantité du produit.
     */
    public ShoppingCartLine(ShoppingCart cart, Product product, int quantity, String size) {
        this.product = product;
        this.shoppingCart = cart;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
        this.size = size;
    }

    // ===========================
    //     Getters et Setters
    // ===========================

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

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
        this.totalPrice = product.getPrice() * quantity; // Recalculer le total
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
}