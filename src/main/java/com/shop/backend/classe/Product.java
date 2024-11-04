package com.shop.backend.classe;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int idProduct;
    private String productName;
    private double price;
    private List<ProductImage> images;
    private List<ProductVariant> variants;
    private Brand brand;
    private Category category;

    // Constructor
    public Product(int id, String name, double price, Brand brand, Category category) {
        this.idProduct = id;
        this.productName = name;
        this.price = price;
        this.brand = brand;
        this.images = new ArrayList<>();
        this.variants = new ArrayList<>();
        this.category = category;
    }

    public void addImage(ProductImage image) {
        images.add(image);
    }

    public void addVariant(ProductVariant variant) {
        variants.add(variant);
    }

    /* Getters */

    public int getIdProduct() {
        return idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public Brand getBrand() {
        return brand;
    }
}
