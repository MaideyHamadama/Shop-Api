package com.shop.backend.dto;

import com.shop.backend.entity.Product;
import com.shop.backend.entity.SizeType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de transfert de données (DTO) pour représenter un produit.
 * Permet de mapper les entités {@link Product} pour les besoins des échanges de données.
 */
public class ProductDTO {

    private int idProduct;
    private String productName;
    private double price;
    private String description;
    private int brandId;
    private String category;
    private String imageURL;
    private List<String> adultSizes;
    private List<String> childSizes;

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur par défaut.
     */
    public ProductDTO() {
    }

    /**
     * Constructeur qui initialise un {@link ProductDTO} à partir d'une entité {@link Product}.
     *
     * @param product L'entité {@link Product} à mapper.
     */
    public ProductDTO(Product product) {
        this.idProduct = product.getIdProduct();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.brandId = product.getBrand().getIdBrand();
        this.category = product.getCategory().name();
        this.imageURL = product.getImage() != null ? product.getImage().getImageURL() : null;
        this.adultSizes = product.getSizes().stream()
                .filter(size -> SizeType.ADULT.equals(size.getType()))
                .map(size -> size.getAdultSize().name())
                .collect(Collectors.toList());
        this.childSizes = product.getSizes().stream()
                .filter(size -> SizeType.CHILD.equals(size.getType()))
                .map(size -> size.getChildSize() != null ? size.getChildSize().getNumericValue() : null)
                .filter(size -> size != null)
                .collect(Collectors.toList());
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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<String> getAdultSizes() {
        return adultSizes;
    }

    public void setAdultSizes(List<String> adultSizes) {
        this.adultSizes = adultSizes;
    }

    public List<String> getChildSizes() {
        return childSizes;
    }

    public void setChildSizes(List<String> childSizes) {
        this.childSizes = childSizes;
    }
}