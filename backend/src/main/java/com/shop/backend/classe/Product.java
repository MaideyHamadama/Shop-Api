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

    // Constructor
    public Product(int id, String name, double price, Brand brand) {
        this.idProduct = id;
        this.productName = name;
        this.price = price;
        this.brand = brand;
        this.images = new ArrayList<>();
        this.variants = new ArrayList<>();
    }

    public void addImage(ProductImage image) {
        images.add(image);
    }

    public void addVariant(ProductVariant variant) {
        variants.add(variant);
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
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
