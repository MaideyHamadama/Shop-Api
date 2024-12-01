package com.shop.backend.dto;

import com.shop.backend.entity.ProductImage;

/**
 * Classe de transfert de données (DTO) pour représenter une image de produit.
 * Permet de mapper les entités {@link ProductImage} pour les besoins des échanges de données.
 */
public class ProductImageDTO {

    private int idProdImage;
    private String imageURL;
    private String color;
    private int productId;

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur par défaut.
     */
    public ProductImageDTO() {
    }

    /**
     * Constructeur qui initialise un {@link ProductImageDTO} à partir d'une entité {@link ProductImage}.
     *
     * @param productImage L'entité {@link ProductImage} à mapper.
     */
    public ProductImageDTO(ProductImage productImage) {
        this.idProdImage = productImage.getIdProdImage();
        this.imageURL = productImage.getImageURL();
        this.color = productImage.getColor().name();
        this.productId = productImage.getProduct() != null ? productImage.getProduct().getIdProduct() : 0;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}