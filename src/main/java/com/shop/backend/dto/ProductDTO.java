package com.shop.backend.dto;

import com.shop.backend.entity.Category;
import com.shop.backend.entity.Product;
import com.shop.backend.entity.Size;
import com.shop.backend.entity.SizeType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDTO {

    private int idProduct;
    private String productName;
    private double price;
    private String description;
    private int brandId;
    private String category;
    private String imageURL;

    // Liste des tailles adultes
    private List<String> adultSizes;

    // Liste des tailles enfants
    private List<String> childSizes;


    public ProductDTO() {
    }

    /**
     * Constructor that initializes the com.shop.backend.dto.ProductDTO from a Product entity.
     *
     * @param product The Product entity to map.
     */
    public ProductDTO(Product product) {
        this.idProduct = product.getIdProduct();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.brandId = product.getBrand().getIdBrand();
        this.category = product.getCategory().name();
        this.imageURL = product.getImage() != null ? product.getImage().getImageURL() : null;

        // Charger les tailles adultes
        this.adultSizes = product.getSizes().stream()
                .filter(size -> SizeType.ADULT.equals(size.getType())) // Filtrer les tailles adultes
                .map(size -> size.getAdultSize().name()) // Convertir l'énumération en chaîne
                .collect(Collectors.toList());

        // Charger les tailles enfants et extraire uniquement le chiffre
        this.childSizes = product.getSizes().stream()
                .filter(size -> SizeType.CHILD.equals(size.getType())) // Filtrer les tailles enfants
                .map(size -> size.getChildSize() != null ? size.getChildSize().getNumericValue() : null) // Utiliser la méthode dédiée
                .filter(size -> size != null) // Éviter les tailles nulles
                .collect(Collectors.toList());
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