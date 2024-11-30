package com.shop.backend.dto;

import com.shop.backend.entity.Product;

public class ProductDTO {

    private int idProduct;
    private String productName;
    private double price;
    private String description;
    private int brandId;
    private String category;
    private String imageURL;


    public ProductDTO() {
    }

    /**
     * Constructor that initializes the com.shop.backend.dto.ProductDTO from a Product entity.
     *
     * @param product The Product entity to map.
     */
    public ProductDTO(Product product) {
        this.idProduct = product.getIdProduct();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.brandId = product.getBrand().getIdBrand();
        this.category = product.getCategory().name();
        this.imageURL = product.getImage() != null ? product.getImage().getImageURL() : null;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}