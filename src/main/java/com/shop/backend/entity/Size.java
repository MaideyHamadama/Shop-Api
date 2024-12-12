package com.shop.backend.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entité représentant une taille dans la base de données.
 * Cette classe est mappée à la table "sizes" et inclut les attributs pour
 * le type de taille, les tailles adultes et enfants.
 */
@Entity
@Table(name = "sizes")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SizeType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "adult_size")
    private AdultSize adultSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "child_size")
    private ChildSize childSize;

    @ManyToMany(mappedBy = "sizes")
    private Set<Product> products = new HashSet<>();

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur par défaut pour JPA.
     */
    public Size() {
    }

    /**
     * Constructeur complet pour initialiser une taille avec ses attributs.
     *
     * @param type      Le type de la taille (ADULT ou CHILD).
     * @param adultSize La taille adulte associée (facultatif).
     * @param childSize La taille enfant associée (facultatif).
     */
    public Size(SizeType type, AdultSize adultSize, ChildSize childSize) {
        this.type = type;
        this.adultSize = adultSize;
        this.childSize = childSize;
    }


    // ===========================
    //    Getters et Setters
    // ===========================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SizeType getType() {
        return type;
    }

    public void setType(SizeType type) {
        this.type = type;
    }

    public AdultSize getAdultSize() {
        return adultSize;
    }

    public void setAdultSize(AdultSize adultSize) {
        this.adultSize = adultSize;
    }

    public ChildSize getChildSize() {
        return childSize;
    }

    public void setChildSize(ChildSize childSize) {
        this.childSize = childSize;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}