package com.shop.backend.dto;

import com.shop.backend.entity.ProductImage;

public class ProductImageDTO {

    private int idProdImage;
    private String imageURL;
    private String color;
    private int productId;

    public ProductImageDTO() {
    }

    /**
     * Constructor that initializes the ProductImageDTO from a ProductImage entity.
     *
     * @param productImage The ProductImage entity to map.
     */
    public ProductImageDTO(ProductImage productImage) {
        this.idProdImage = productImage.getIdProdImage();
        this.imageURL = productImage.getImageURL();
        this.color = productImage.getColor().name();
        this.productId = productImage.getProduct() != null ? productImage.getProduct().getIdProduct() : 0;
    }

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

