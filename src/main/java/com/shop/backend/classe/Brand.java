package com.shop.backend.classe;

public class Brand {
    private int idBrand;
    private String brandName;

    // Constructor
    public Brand(int id, String name) {
        this.idBrand = id;
        this.brandName = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getIdBrand() {
        return idBrand;
    }
}
