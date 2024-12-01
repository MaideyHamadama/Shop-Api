package com.shop.backend.dto;

import com.shop.backend.entity.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de transfert de données (DTO) pour représenter un panier d'achat.
 * Permet de mapper les entités {@link ShoppingCart} pour les besoins des échanges de données.
 */
public class ShoppingCartDTO {

    private int cartID;
    private Integer userId;
    private List<ShoppingCartLineDTO> cartProducts;
    private double cartTotalPrice;
    private double cartTotalPriceExcludingVAT;
    private double tvaRate;

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur par défaut.
     */
    public ShoppingCartDTO() {
    }

    /**
     * Constructeur qui initialise un {@link ShoppingCartDTO} à partir d'une entité {@link ShoppingCart}.
     *
     * @param cart L'entité {@link ShoppingCart} à mapper.
     */
    public ShoppingCartDTO(ShoppingCart cart) {
        this.cartID = cart.getCartID();
        this.cartTotalPrice = cart.getCartTotalPrice();
        this.cartTotalPriceExcludingVAT = cart.getCartTotalPriceExcludingVAT();
        this.cartProducts = cart.getCartProducts().stream()
                .map(ShoppingCartLineDTO::new)
                .collect(Collectors.toList());
        this.tvaRate = cart.getTvaRate();
    }

    // ===========================
    //    Getters et Setters
    // ===========================

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ShoppingCartLineDTO> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<ShoppingCartLineDTO> cartProducts) {
        this.cartProducts = cartProducts;
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

    public double getTvaRate() {
        return tvaRate;
    }
}