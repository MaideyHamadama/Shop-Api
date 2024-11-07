package com.shop.backend.classe;

public class ProductVariant {
    private int idVariant;
    private Color color;
    private Size size;
    private SleeveType sleeveType;

    // Suppression de la variable sizeType car liée avec size pas ProductVariant
    // Suppression de la variable price car dans Product et pas dans ProductVariant

    // Constructor
    public ProductVariant(int id, Color color, Size size, SleeveType sleeveType) {
        this.idVariant = id;
        this.color = color;
        this.size = size;
        this.sleeveType = sleeveType;
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

}

