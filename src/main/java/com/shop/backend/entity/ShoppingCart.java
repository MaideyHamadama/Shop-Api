package com.shop.backend.entity;

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

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingCartLine> cartProducts = new ArrayList<>();

    @Column(name = "cartTotalPrice")
    private double cartTotalPrice;
    

    // ==========================
    //      Constructors
    // ==========================

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
        this.cartTotalPrice = 0.0;
    }

    // ==========================
    //      Methods
    // ==========================

    public void addProduct(ShoppingCartLine line) {
        line.setShoppingCart(this); // Set the reference to the current ShoppingCart
        cartProducts.add(line);
        cartTotalPrice += line.getTotalPrice();
    }
    
    public void removeProduct(ShoppingCartLine line) {
        line.setShoppingCart(null); // Clear the reference
        cartProducts.remove(line);
        cartTotalPrice -= line.getTotalPrice();
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
