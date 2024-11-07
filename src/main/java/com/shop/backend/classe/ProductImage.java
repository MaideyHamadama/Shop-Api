package com.shop.backend.classe;

public class ProductImage {
    private int idProdImage;
    private String imageURL;
    private Color color;

    // Constructor
    public ProductImage(int id, String url, Color color) {
        this.idProdImage = id;
        this.imageURL = url;
        this.color= color;
    }

    // Method to get image URL
    public String getImageURL() {
        return imageURL;
    
    }

    public Color getColor() {
        return color;
    }

    public int getIdProdImage() {
        return idProdImage;
    }
    
}

