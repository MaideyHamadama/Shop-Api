package com.shop.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBrand;

    @Column(name = "brandName", nullable = false, unique = true)
    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @JsonBackReference avoids an infinite loop during JSON serialization
    @JsonBackReference
    private List<Product> products = new ArrayList<>();

    // Constructor,
    public Brand(int idBrand, String brandName) {
        this.idBrand = idBrand;
        this.brandName = brandName;
    }

    public Brand() {

    }

    // Getters, and Setters
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
