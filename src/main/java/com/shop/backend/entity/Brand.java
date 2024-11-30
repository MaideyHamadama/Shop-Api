package com.shop.backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a Brand in the database.
 * This class is mapped to the "Brand" table and includes attributes for
 * brand ID, brand name, and a list of products associated with the brand.
 */
@Entity
@Table(name = "Brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBrand;

    @Column(name = "brandName", nullable = false, unique = true)
    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    // ===========================
    //        Constructors
    // ===========================

    /**
     * Full constructor to create a Brand instance with all attributes.
     *
     * @param idBrand   The unique identifier for the brand.
     * @param brandName The name of the brand.
     */
    public Brand(int idBrand, String brandName) {
        this.idBrand = idBrand;
        this.brandName = brandName;
    }

    /**
     * Default constructor for JPA.
     */
    public Brand() {
    }

    // ===========================
    //        Getters & Setters
    // ===========================

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}