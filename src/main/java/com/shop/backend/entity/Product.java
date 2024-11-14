package com.shop.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a Product in the database.
 * This class is mapped to the "Product" table and includes attributes for
 * product ID, name, price, brand, category, and associated images and variants.
 */
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductVariant> variants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonManagedReference // Avoids an infinite loop during JSON serialization
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    // ===========================
    //        Constructors
    // ===========================

    /**
     * Full constructor to create a Product instance with all attributes.
     *
     * @param idProduct   The unique identifier for the product.
     * @param productName The name of the product.
     * @param price       The price of the product.
     * @param brand       The {@link Brand} associated with this product.
     * @param category    The {@link Category} of the product.
     */
    public Product(int idProduct, String productName, double price, Brand brand, Category category) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.category = category;
    }

    /**
     * Default constructor for JPA.
     */
    public Product() {
    }

    // ===========================
    //          Methods
    // ===========================

    /**
     * Adds an image to the product and sets the product reference in the image.
     *
     * @param image The {@link ProductImage} to add.
     */
    public void addImage(ProductImage image) {
        images.add(image);
        image.setProduct(this);
    }

    /**
     * Adds a variant to the product and sets the product reference in the variant.
     *
     * @param variant The {@link ProductVariant} to add.
     */
    public void addVariant(ProductVariant variant) {
        variants.add(variant);
        variant.setProduct(this);
    }

    // ===========================
    //        Getters & Setters
    // ===========================

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

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
