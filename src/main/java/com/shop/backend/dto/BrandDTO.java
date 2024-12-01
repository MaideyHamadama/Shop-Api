package com.shop.backend.dto;

import com.shop.backend.entity.Brand;

/**
 * Classe de transfert de données (DTO) pour représenter une marque.
 * Permet de mapper les entités {@link Brand} pour les besoins des échanges de données.
 */
public class BrandDTO {

    private int idBrand;
    private String brandName;

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur par défaut.
     * Nécessaire pour la sérialisation et la désérialisation.
     */
    public BrandDTO() {
    }

    /**
     * Constructeur qui initialise un {@link BrandDTO} à partir d'une entité {@link Brand}.
     *
     * @param brand L'entité {@link Brand} à mapper.
     */
    public BrandDTO(Brand brand) {
        this.idBrand = brand.getIdBrand();
        this.brandName = brand.getBrandName();
    }

    // ===========================
    //    Getters et Setters
    // ===========================

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