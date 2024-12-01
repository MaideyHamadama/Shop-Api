package com.shop.backend.entity;

/**
 * Enumération représentant les tailles enfants disponibles.
 */
public enum ChildSize {
    SIZE_2,
    SIZE_4,
    SIZE_6,
    SIZE_8,
    SIZE_10,
    SIZE_12,
    SIZE_14;

    /**
     * Méthode pour extraire la valeur numérique de la taille enfant.
     *
     * @return La valeur numérique sous forme de chaîne.
     */
    public String getNumericValue() {
        return this.name().replace("SIZE_", "");
    }
}
