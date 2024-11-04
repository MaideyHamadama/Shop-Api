package com.shop.backend.controller;

import com.shop.backend.classe.SleeveType;
import com.shop.backend.classe.SizeType;
import com.shop.backend.classe.Color;
import com.shop.backend.classe.Size;
import com.shop.backend.classe.ProductVariant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/variants")
public class ProductVariantController {

    // Initialisation de données de test pour les variants
    private List<ProductVariant> variants = new ArrayList<>(List.of(
            new ProductVariant(1, Color.RED, new Size(1, "M", SizeType.ADULT), SleeveType.SHORT),
            new ProductVariant(2, Color.BLUE, new Size(2, "L", SizeType.ADULT), SleeveType.LONG),
            new ProductVariant(3, Color.GREEN, new Size(3, "S", SizeType.CHILD), SleeveType.SHORT)
    ));


    // GET - Liste de tous les variants
    @GetMapping
    public ResponseEntity<List<ProductVariant>> getAllVariants() {
        return ResponseEntity.ok().body(variants);
    }

    // GET - Obtenir un variant par ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductVariant> getVariantById(@PathVariable int id) {
        ProductVariant variant = variants.stream()
                .filter(v -> v.getIdVariant() == id)
                .findFirst()
                .orElse(null);
        if (variant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(variant);
    }

    // GET - Filtrer les variants par couleur
    @GetMapping("/color/{color}")
    public ResponseEntity<List<ProductVariant>> getVariantsByColor(@PathVariable String color) {
        try {
            Color colorEnum = Color.valueOf(color.toUpperCase());
            List<ProductVariant> variantsByColor = variants.stream()
                    .filter(v -> v.getColor() == colorEnum)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(variantsByColor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Retourne 400 si la couleur n'est pas valide
        }
    }

    // GET - Filtrer les variants par taille
    @GetMapping("/size/{size}")
    public ResponseEntity<List<ProductVariant>> getVariantsBySize(@PathVariable String size) {
        List<ProductVariant> variantsBySize = variants.stream()
                .filter(v -> v.getSize().getSizeName().equalsIgnoreCase(size))
                .collect(Collectors.toList());
        if (variantsBySize.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(variantsBySize);
    }

    // GET - Filtrer les variants par type de manche
    @GetMapping("/sleeve-type/{sleeveType}")
    public ResponseEntity<List<ProductVariant>> getVariantsBySleeveType(@PathVariable String sleeveType) {
        try {
            SleeveType sleeveTypeEnum = SleeveType.valueOf(sleeveType.toUpperCase());
            List<ProductVariant> variantsBySleeveType = variants.stream()
                    .filter(v -> v.getSleeveType() == sleeveTypeEnum)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(variantsBySleeveType);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Retourne 400 si le type de manche n'est pas valide
        }
    }
}
