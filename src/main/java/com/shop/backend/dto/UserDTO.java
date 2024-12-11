package com.shop.backend.dto;

import com.shop.backend.entity.User;

public class UserDTO {
    private String email;
    private String userName;
    private String userSurname;
    private String address;
    private String city;
    private int postalCode;
    private Integer cartId;
    private int numberOfProductsInCart;

    // ============================
    // Constructeurs
    // ============================

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.userSurname = user.getUserSurname();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.postalCode = user.getPostalCode();
        this.cartId = user.getShoppingCart() != null ? user.getShoppingCart().getCartId() : null;
        this.numberOfProductsInCart = user.getShoppingCart() != null ? user.getShoppingCart().getCartProducts().size() : 0;
    }

    // ============================
    // Getters & Setters
    // ============================

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getNumberOfProductsInCart() {
        return numberOfProductsInCart;
    }

    public void setNumberOfProductsInCart(int numberOfProductsInCart) {
        this.numberOfProductsInCart = numberOfProductsInCart;
    }
}
