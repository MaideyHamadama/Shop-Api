package com.shop.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

/**
 * Represents a line in the shopping cart, linking a product variant to a quantity and total price.
 */
@Entity
@Table(name = "shopping_cart_line")
public class ShoppingCartLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false) // Foreign key to ProductVariant
    @JsonManagedReference // Gestion de la relation avec le produit pour JSON
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", nullable = false) // Foreign key to ShoppingCart
    @JsonBackReference // Ceci empêche la sérialisation du lien vers le parent
    private ShoppingCart shoppingCart;

    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    @Column(name = "total_price", nullable = false)
    private double totalPrice;
    

    // ===========================
    //      Constructors
    // ===========================
    public ShoppingCartLine() {
    }

    public ShoppingCartLine(Product product, int quantity, ShoppingCart shoppingCart) {
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
        this.totalPrice = product.getPrice() * quantity;
    }

    // ===========================
    //      Getters & Setters
    // ===========================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCartLine{" +
                "id=" + id +
                ", product=" + product +
                ", shoppingCart=" + shoppingCart +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
