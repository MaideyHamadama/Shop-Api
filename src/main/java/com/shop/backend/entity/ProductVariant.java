package com.shop.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductVariant")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVariant;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleeveType", nullable = false)
    private SleeveType sleeveType;

     // Constructor,
    public ProductVariant(int idVariant, Color color, Size size, SleeveType sleeveType) {
        this.idVariant = idVariant;
        this.color = color;
        this.size = size;
        this.sleeveType = sleeveType;
    }

    public int getIdVariant() {
        return idVariant;
    }

    public void setIdVariant(int idVariant) {
        this.idVariant = idVariant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public SleeveType getSleeveType() {
        return sleeveType;
    }

    public void setSleeveType(SleeveType sleeveType) {
        this.sleeveType = sleeveType;
    }

    //  Getters, and Setters
    
    
}
