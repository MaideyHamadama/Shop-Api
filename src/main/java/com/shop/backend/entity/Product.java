package com.shop.backend.entity;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Entité représentant un produit dans la base de données.
 * Cette classe est mappée à la table "Product" et inclut les attributs pour
 * l'ID, le nom, le prix, la marque, la catégorie, ainsi que les tailles et images associées.
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

    /**
     * Représente l'ensemble des tailles disponibles pour ce produit.
     *
     * - Utilise un `Set` pour garantir que chaque taille est unique et éviter les doublons.
     * - Les tailles peuvent inclure des tailles pour adultes ou pour enfants,
     *   différenciées par leur type (ADULT ou CHILD).
     * - L'utilisation d'un `Set` est logique car l'ordre des tailles n'est pas
     *   nécessairement important et les duplications doivent être évitées.
     */
    private Set<Size> sizes;

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur complet pour créer une instance de produit avec tous ses attributs.
     *
     * @param idProduct   L'identifiant unique du produit.
     * @param productName Le nom du produit.
     * @param price       Le prix du produit.
     * @param description La description du produit.
     * @param brand       La marque associée au produit.
     * @param category    La catégorie du produit.
     */
    public Product(int idProduct, String productName, double price, String description, Brand brand, Category category) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.category = category;
    }

    /**
     * Constructeur par défaut pour JPA.
     */
    public Product() {
    }

    // ===========================
    //        Méthodes
    // ===========================

    public void addImage(ProductImage image) {
        this.image = image;
        image.setProduct(this);
    }

    // ===========================
    //    Getters et Setters
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