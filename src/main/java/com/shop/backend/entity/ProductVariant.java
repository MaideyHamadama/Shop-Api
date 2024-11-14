package com.shop.backend.entity;

import jakarta.persistence.*;


/**
 * Entity representing a specific variant of a {@link Product}.
 * This class is mapped to the "ProductVariant" table and includes attributes for
 * variant ID, color, size, sleeve type, and the associated product.
 */
@Entity
@Table(name = "ProductVariant")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVariant;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleeveType", nullable = false)
    private SleeveType sleeveType;

    // ===========================
    //        Constructors
    // ===========================

    /**
     * Full constructor to create a ProductVariant instance with all attributes.
     *
     * @param idVariant  The unique identifier for the product variant.
     * @param color      The {@link Color} of the variant.
     * @param size       The {@link Size} of the variant.
     * @param sleeveType The {@link SleeveType} of the variant.
     */
    public ProductVariant(int idVariant, Color color, Size size, SleeveType sleeveType) {
        this.idVariant = idVariant;
        this.color = color;
        this.size = size;
        this.sleeveType = sleeveType;
    }

    /**
     * Default constructor for JPA.
     */
    public ProductVariant() {
    }

    // ===========================
    //        Getters & Setters
    // ===========================

    public int getIdVariant() {
        return idVariant;
    }

    public void setIdVariant(int idVariant) {
        this.idVariant = idVariant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public SleeveType getSleeveType() {
        return sleeveType;
    }

    public void setSleeveType(SleeveType sleeveType) {
        this.sleeveType = sleeveType;
    }
}