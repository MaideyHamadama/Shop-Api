package com.shop.backend.entity;

public enum ChildSize {
    SIZE_2,
    SIZE_4,
    SIZE_6,
    SIZE_8,
    SIZE_10,
    SIZE_12,
    SIZE_14;

    // Méthode pour extraire uniquement le chiffre
    public String getNumericValue() {
        return this.name().replace("SIZE_", "");
    }
}