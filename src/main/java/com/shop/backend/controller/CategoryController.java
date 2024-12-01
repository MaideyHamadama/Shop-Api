package com.shop.backend.controller;

import com.shop.backend.dto.ProductDTO;
import com.shop.backend.entity.Category;
import com.shop.backend.entity.Product;
import com.shop.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contrôleur pour gérer les entités {@link Category}.
 * Fournit des endpoints pour récupérer et interagir avec les catégories de produits.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Récupère toutes les catégories disponibles.
     *
     * @return Un {@link ResponseEntity} contenant un tableau de toutes les valeurs de l'énumération {@link Category}.
     *         Si la récupération est réussie, elle retourne un statut HTTP 200 (OK)
     *         avec les catégories dans le corps de la réponse.
     *
     * Cette méthode permet au client de récupérer toutes les catégories pour des fins d'affichage.
     */
    @GetMapping
    public ResponseEntity<Category[]> getAllCategories() {
        return ResponseEntity.ok().body(Category.values());
    }

    /**
     * Récupère la liste de tous les produits d'une catégorie spécifique.
     *
     * @param categoryName Le nom de la catégorie contenant les produits à récupérer.
     * @return Un {@link ResponseEntity} contenant une liste de tous les objets {@link ProductDTO}
     *         dans la catégorie spécifiée.
     *
     *         Si la récupération est réussie, elle retourne un statut HTTP 200 (OK)
     *         avec les produits dans le corps de la réponse.
     *         Si la catégorie n'existe pas, elle retourne un statut HTTP 400 (Bad Request).
     *         Si aucun produit n'est trouvé, elle retourne un statut HTTP 204 (No Content).
     *
     * Cette méthode permet au client de récupérer tous les produits dans une catégorie pour des fins d'affichage.
     */
    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String categoryName) {
        Category category;
        try {
            category = Category.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.ok().body(Collections.emptyList());
        }

        // Convertir les entités Product en DTO
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(new ProductDTO(product));
        }

        return ResponseEntity.ok().body(productDTOs);
    }
}