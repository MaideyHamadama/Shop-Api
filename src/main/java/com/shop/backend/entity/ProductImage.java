package com.shop.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ProductImage")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProdImage;

    @Column(name = "imageURL", nullable = false)
    private String imageURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Constructor,

    public ProductImage(int idProdImage, String imageURL, Color color) {
        this.idProdImage = idProdImage;
        this.imageURL = imageURL;
        this.color = color;
    }

    public ProductImage() {

    }

    // Getters, and Setters
    public int getIdProdImage() {
        return idProdImage;
    }

    public void setIdProdImage(int idProdImage) {
        this.idProdImage = idProdImage;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
