package com.shop.backend.dto;

/**
 * Classe de transfert de données (DTO) pour représenter une requête liée à un utilisateur et son panier.
 */
public class UserCartRequest {

    private Integer userId;

    // ===========================
    //    Getters et Setters
    // ===========================

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}