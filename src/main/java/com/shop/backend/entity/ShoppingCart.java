package com.shop.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un panier d'achat dans la base de données.
 * Cette classe est mappée à la table "ShoppingCart" et inclut des attributs pour
 * l'utilisateur associé, les produits dans le panier, les totaux avec ou sans TVA, et le taux de TVA.
 */
@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private int cartId;

    @OneToOne
    @JoinColumn(name = "userID", nullable = true)
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingCartLine> cartProducts = new ArrayList<>();

    @Column(name = "tva_rate", nullable = false)
    private double tvaRate = 0.21;

    @Column(name = "cartTotalPrice")
    private double cartTotalPrice;

    @Column(name = "cartTotalPriceExcludingVAT")
    private double cartTotalPriceExcludingVAT;

    // ==========================
    //      Constructeurs
    // ==========================

    /**
     * Constructeur par défaut pour initialiser un panier vide.
     */
    public ShoppingCart() {
        this.cartTotalPrice = 0.0;
    }

    /**
     * Constructeur pour initialiser un panier associé à un utilisateur.
     *
     * @param user L'utilisateur associé au panier.
     */
    public ShoppingCart(User user) {
        this.user = user;
        this.cartTotalPrice = 0.0;
        this.cartTotalPriceExcludingVAT = 0.0;
    }


    // ===========================
    //         Méthodes
    // ===========================

    /**
     * Calcule le nombre total de produits dans le panier.
     *
     * @return Le nombre total de produits.
     */
    public int getNumberOfProducts() {
        return cartProducts.stream().mapToInt(ShoppingCartLine::getQuantity).sum();
    }

    // ==========================
    //    Getters et Setters
    // ==========================

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingCartLine> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<ShoppingCartLine> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public double getTvaRate() {
        return tvaRate;
    }

    public void setTvaRate(double tvaRate) {
        this.tvaRate = tvaRate;
    }

    public double getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(double cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public double getCartTotalPriceExcludingVAT() {
        return cartTotalPriceExcludingVAT;
    }

    public void setCartTotalPriceExcludingVAT(double cartTotalPriceExcludingVAT) {
        this.cartTotalPriceExcludingVAT = cartTotalPriceExcludingVAT;
    }
}