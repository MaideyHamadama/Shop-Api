package com.shop.backend.controller;

import com.shop.backend.entity.Product;
import com.shop.backend.dto.ProductDTO;
import com.shop.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour gérer les entités {@link Product}.
 * Fournit des endpoints pour récupérer et interagir avec les produits.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SizeService sizeService;

    /**
     * Récupère la liste de tous les produits depuis la base de données.
     *
     * @return Un {@link ResponseEntity} contenant une liste de toutes les instances de {@link Product}.
     *
     *         Si la récupération est réussie, retourne un statut HTTP 200 (OK)
     *         avec les produits dans le corps de la réponse.
     *         Si aucun produit n'est trouvé, retourne un statut HTTP 204 (No Content).
     *
     * Cette méthode permet au client de récupérer tous les produits de la base de données
     * à des fins d'affichage.
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.getAllProducts();
        if (productDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(productDTOs);
    }

    /**
     * Récupère les informations d'un produit par son ID.
     *
     * @param id L'ID du produit à récupérer.
     * @return Un {@link ResponseEntity} contenant les informations du {@link Product}.
     *
     *         Si la récupération est réussie, retourne un statut HTTP 200 (OK)
     *         avec les détails du produit dans le corps de la réponse.
     *         Si le produit n'existe pas, retourne un statut HTTP 404 (Not Found).
     *
     * Cette méthode permet au client de récupérer les détails d'un produit
     * à des fins d'affichage.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        try {
            ProductDTO productDTO = productService.getProductById(id);
            return ResponseEntity.ok().body(productDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Ajoute une taille spécifique à un produit.
     *
     * @param productId L'ID du produit auquel ajouter la taille.
     * @param sizeId    L'ID de la taille à ajouter.
     * @return Un {@link ResponseEntity} contenant le produit mis à jour en tant que {@link ProductDTO}.
     *
     *         Si l'opération est réussie, retourne un statut HTTP 200 (OK)
     *         avec le produit mis à jour dans le corps de la réponse.
     */
    @PutMapping("/{productId}/sizes/{sizeId}")
    public ResponseEntity<ProductDTO> addSizeToProduct(@PathVariable int productId, @PathVariable int sizeId) {
        Optional<Product> product = productService.getProductById(productId);
        Optional<Size> size = sizeService.getSizeById(sizeId);

        if (product.isPresent() && size.isPresent()) {
            product.get().addSize(size.get());
            productService.saveProduct(product.get()); // Sauvegarder le produit avec la taille ajoutée
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Supprime une taille spécifique d'un produit.
     *
     * @param productId L'ID du produit auquel retirer la taille.
     * @param sizeId    L'ID de la taille à retirer.
     * @return Un {@link ResponseEntity} contenant le produit mis à jour en tant que {@link ProductDTO}.
     *
     *         Si l'opération est réussie, retourne un statut HTTP 200 (OK)
     *         avec le produit mis à jour dans le corps de la réponse.
     */
    @DeleteMapping("/{productId}/sizes/{sizeId}")
    public ResponseEntity<ProductDTO> removeSizeFromProduct(@PathVariable int productId, @PathVariable int sizeId) {
        Product updatedProduct = productService.removeSizeFromProduct(productId, sizeId);
        return ResponseEntity.ok(new ProductDTO(updatedProduct));
    }
}