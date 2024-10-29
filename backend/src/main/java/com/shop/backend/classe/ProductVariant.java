package com.shop.backend.classe;

public class ProductVariant {
    private int idVariant;
    private double price;
    private Color color;
    private Size size;
    private SleeveType sleeveType;
    private SizeType sizeType;

    // Constructor
    public ProductVariant(int id, Color color, Size size, SleeveType sleeveType,double price, SizeType sizeType) {
        this.idVariant = id;
        this.color = color;
        this.size = size;
        this.sleeveType = sleeveType;
        this.sizeType = sizeType;
        this.price = price;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    public int getIdVariant() {
        return idVariant;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public SleeveType getSleeveType() {
        return sleeveType;
    }

    public SizeType getSizeType() {
        return sizeType;
    }
}
