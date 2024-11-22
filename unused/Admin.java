package com.shop.backend.entity;

import jakarta.persistence.*;

/**
 * Entité représentant un administrateur du système.
 * Cette classe permet à un administrateur de gérer les produits, les utilisateurs, et les commandes.
 */
@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminID")
    private int adminID;

    @Column(name = "adminName", nullable = false)
    private String adminName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    // ==========================
    //      Constructeurs
    // ==========================
    public Admin() {
        this.role = "Admin"; // Définit le rôle par défaut comme Admin
    }

    public Admin(int adminID, String adminName, String email, String password) {
        this.adminID = adminID;
        this.adminName = adminName;
        this.email = email;
        this.password = password;
        this.role = "Admin"; // Rôle spécifique pour un administrateur
    }

    // ==========================
    //      Méthodes de gestion
    // ==========================

    /**
     * Ajoute un produit à l'inventaire.
     *
     * @param product Le produit à ajouter.
     */
    public void addProduct(Product product) {
        // Code pour ajouter le produit à l'inventaire
    }

    /**
     * Supprime un produit de l'inventaire.
     *
     * @param product Le produit à supprimer.
     */
    public void deleteProduct(Product product) {
        // Code pour supprimer le produit de l'inventaire
    }

    /**
     * Gère les détails d'un utilisateur.
     *
     * @param user L'utilisateur à gérer.
     */
    public void manageUser(User user) {
        // Code pour gérer les détails de l'utilisateur
    }

    /**
     * Gère une commande spécifique.
     *
     * @param order La commande à gérer.
     */
    public void manageOrder(Order order) {
        // Code pour gérer la commande
    }

    // ==========================
    //      Getters & Setters
    // ==========================
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
