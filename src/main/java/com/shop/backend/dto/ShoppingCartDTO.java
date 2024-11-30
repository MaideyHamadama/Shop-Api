package com.shop.backend.dto;

import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.entity.ShoppingCartLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartDTO {

    private int cartID;
    private Integer userId;
    private List<ShoppingCartLineDTO> cartProducts;
    private double cartTotalPrice;
    private double cartTotalPriceExcludingVAT;
    private double tvaRate;

    public ShoppingCartDTO() {
    }

    /**
     * Constructor that initializes the ShoppingCartDTO from a ShoppingCart entity.
     *
     * @param cart The ShoppingCart entity to map.
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

