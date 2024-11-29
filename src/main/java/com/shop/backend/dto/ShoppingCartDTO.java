package com.shop.backend.dto;

import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.entity.ShoppingCartLine;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTO {

    private int cartID;
    private Integer userId;
    private List<ShoppingCartLineDTO> cartProducts;
    private double cartTotalPrice;

    public ShoppingCartDTO() {
    }

    /**
     * Constructor that initializes the ShoppingCartDTO from a ShoppingCart entity.
     *
     * @param shoppingCart The ShoppingCart entity to map.
     */
    public ShoppingCartDTO(ShoppingCart shoppingCart) {
        this.cartID = shoppingCart.getCartID();
        this.userId = shoppingCart.getUser() != null ? shoppingCart.getUser().getUserID() : null;

        this.cartProducts = new ArrayList<>();
        for (ShoppingCartLine line : shoppingCart.getCartProducts()) {
            this.cartProducts.add(new ShoppingCartLineDTO(line));
        }

        this.cartTotalPrice = shoppingCart.getCartTotalPrice();
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
}

