package com.shop.backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant une marque dans la base de données.
 * Cette classe est mappée à la table "Brand" et inclut des attributs pour
 * l'ID de la marque, son nom, et une liste de produits associés.
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
    //        Constructeurs
    // ===========================

    /**
     * Constructeur complet pour créer une instance de Brand avec tous ses attributs.
     *
     * @param idBrand   L'identifiant unique de la marque.
     * @param brandName Le nom de la marque.
     */
    public Brand(int idBrand, String brandName) {
        this.idBrand = idBrand;
        this.brandName = brandName;
    }

    /**
     * Constructeur par défaut pour JPA.
     */
    public Brand() {
    }

    // ===========================
    //    Getters et Setters
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