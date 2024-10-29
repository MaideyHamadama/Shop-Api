package com.shop.backend.classe;

public class Size {
    private int idSize;
    private String sizeName;
    private SizeType sizeType;

    public Size(int id, String name, SizeType sizeType) {
        this.idSize = id;
        this.sizeName = name;
        this.sizeType = sizeType;
    }

    public String getSizeName() {
        return sizeName;
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public int getIdSize() {
        return idSize;
    }
    
}
