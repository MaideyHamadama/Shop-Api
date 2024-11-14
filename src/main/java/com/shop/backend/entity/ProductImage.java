package com.shop.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity representing an image associated with a {@link Product}.
 * This class is mapped to the "ProductImage" table and includes attributes for
 * image ID, image URL, color, and the associated product.
 */
@Entity
@Table(name = "ProductImage")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProdImage;

    @Column(name = "image_URL", nullable = false)
    private String imageURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = true)
    @JsonBackReference // Avoids an infinite loop during JSON serialization
    private Product product;

    // ===========================
    //        Constructors
    // ===========================

    /**
     * Full constructor to create a ProductImage instance with all attributes.
     *
     * @param idProdImage The unique identifier for the product image.
     * @param imageURL    The URL of the image.
     * @param color       The {@link Color} associated with the image.
     */
    public ProductImage(int idProdImage, String imageURL, Color color) {
        this.idProdImage = idProdImage;
        this.imageURL = imageURL;
        this.color = color;
    }

    /**
     * Default constructor for JPA.
     */
    public ProductImage() {
    }

    // ===========================
    //        Getters & Setters
    // ===========================

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