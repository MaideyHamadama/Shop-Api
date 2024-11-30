package com.shop.backend.entity;

import jakarta.persistence.*;

import java.util.Set;


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

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProductImage image;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<Size> sizes;

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
     * @param description The description of the product.
     */
    public Product(int idProduct, String productName, double price, String description ,Brand brand, Category category) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.description = description;
    }

    /**
     * Default constructor for JPA.
     */
    public Product() {
    }

    // ===========================
    //          Methods
    // ===========================

    //TODO Deplacer dans service

    /**
     * Adds the image for the product and sets the product reference in the image.
     *
     * @param image The {@link ProductImage} to add.
     */
    public void addImage(ProductImage image) {
        this.image = image;
        image.setProduct(this);
    }


    // ===========================
    //        Getters & Setters
    // ===========================

    public String getImageURL() {
        return image.getImageURL();
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

    public ProductImage getImage() {
        return image;
    }

    public void setImage(ProductImage image) {
        this.image = image;
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
    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public void addSize(Size size) {
        this.sizes.add(size);
    }

    public void removeSize(Size size) {
        this.sizes.remove(size);
    }
}