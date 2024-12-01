package com.shop.backend.entity;

import jakarta.persistence.*;

/**
 * Entité représentant une image associée à un produit.
 * Cette classe est mappée à la table "ProductImage" et inclut des attributs pour
 * l'ID de l'image, l'URL de l'image, la couleur et le produit associé.
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
    private Product product;

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur complet pour créer une instance de ProductImage avec tous ses attributs.
     *
     * @param idProdImage L'identifiant unique de l'image.
     * @param imageURL    L'URL de l'image.
     * @param color       La couleur associée à l'image.
     */
    public ProductImage(int idProdImage, String imageURL, Color color) {
        this.idProdImage = idProdImage;
        this.imageURL = imageURL;
        this.color = color;
    }

    /**
     * Constructeur par défaut pour JPA.
     */
    public ProductImage() {
    }

    // ===========================
    //    Getters et Setters
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