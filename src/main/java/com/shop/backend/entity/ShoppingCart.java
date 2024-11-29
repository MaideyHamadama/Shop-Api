package com.shop.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private int cartID;

    // UserId peut etre null si l'utilisateur est un visiteur
    @OneToOne
    @JoinColumn(name = "userID", nullable = true)
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingCartLine> cartProducts = new ArrayList<>();

    @Column(name = "cartTotalPrice")
    private double cartTotalPrice;
    

    // ==========================
    //      Constructors
    // ==========================

    public ShoppingCart() {
        this.cartTotalPrice = 0.0;
    }

    public ShoppingCart(User user) {
        this.user = user;
        this.cartTotalPrice = 0.0;
    }

    // ==========================
    //      Getters & Setters
    // ==========================

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
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

    public double getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(double cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }
}
