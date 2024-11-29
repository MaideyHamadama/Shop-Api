package com.shop.backend.dto;

import com.shop.backend.entity.Brand;

public class BrandDTO {

    private int idBrand;
    private String brandName;

    public BrandDTO() {
    }

    /**
     * Constructor that initializes the com.shop.backend.dto.BrandDTO from a Brand entity.
     *
     * @param brand The Brand entity to map.
     */
    public BrandDTO(Brand brand) {
        this.idBrand = brand.getIdBrand();
        this.brandName = brand.getBrandName();
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
