package com.shop.backend.entity;

import jakarta.persistence.*;

/**
 * Entité représentant un visiteur du site.
 * Cette classe est une super-classe utilisée pour définir des comportements généraux des utilisateurs,
 * comme la possibilité de parcourir des produits ou d'ajouter des articles au panier.
 */
@MappedSuperclass
public class Visitor {

    /**
     * Le rôle de l'utilisateur. Dans ce cas, "Visitor" par défaut.
     */
    @Column(name = "role", nullable = false)
    protected String role;

    /**
     * Constructeur par défaut.
     * Le rôle est initialisé à "Visitor".
     */
    public Visitor() {
        this.role = "Visitor";
    }

    /**
     * Permet au visiteur de parcourir les produits disponibles dans la boutique.
     */
    public void browseProducts() {
        // Code pour afficher les produits
    }

    /**
     * Permet au visiteur d'ajouter un produit à son panier.
     *
     * @param product Le produit à ajouter au panier.
     */
    public void addToCart(Product product) {
        // Code pour ajouter le produit au panier
    }

    /**
     * Permet au visiteur de supprimer un produit de son panier.
     *
     * @param product Le produit à supprimer du panier.
     */
    public void deleteFromCart(Product product) {
        // Code pour supprimer le produit du panier
    }

    // ==========================
    //      Getters & Setters
    // ==========================
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
