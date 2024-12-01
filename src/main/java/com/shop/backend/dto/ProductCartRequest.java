package com.shop.backend.dto;

/**
 * Classe de transfert de données (DTO) pour représenter une requête d'ajout de produit dans un panier.
 * Permet de transmettre les informations nécessaires pour ajouter un produit au panier,
 * notamment l'ID du produit, la quantité et, éventuellement, l'ID de l'utilisateur.
 */
public class ProductCartRequest {

    private int productId;
    private int quantity;
    private Integer userId; // Optionnel si pas lié à un utilisateur

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur par défaut.
     */
    public ProductCartRequest() {
    }

    /**
     * Constructeur avec paramètres pour initialiser les attributs.
     *
     * @param productId L'ID du produit.
     * @param quantity  La quantité du produit.
     * @param userId    L'ID de l'utilisateur (facultatif).
     */
    public ProductCartRequest(int productId, int quantity, Integer userId) {
        this.productId = productId;
        this.quantity = quantity;
        this.userId = userId;
    }

    // ===========================
    //    Getters et Setters
    // ===========================

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}