package com.shop.backend.controller;

import com.shop.backend.entity.Category;
import com.shop.backend.entity.Product;
import com.shop.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing {@link Category} entities.
 * Provides endpoints to retrieve and interact with product categories.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves all available categories.
     *
     * @return A {@link ResponseEntity} containing an array of all values in the {@link Category} enumeration.
     *         If the retrieval is successful, it returns an HTTP 200 (OK) status
     *         with the categories in the response body.
     *
     * This method allows the client to retrieve all categories for display purposes.
     */
    @GetMapping
    public ResponseEntity<Category[]> getAllCategories() {
        return ResponseEntity.ok().body(Category.values());
    }

    /**
     * Retrieves the list of all products in a specific category.
     *
     * @param categoryName The name of the category containing the products to retrieve.
     * @return A {@link ResponseEntity} containing a list of all {@link Product} objects in the specified category.
     *
     *         If the retrieval is successful, it returns an HTTP 200 (OK) status
     *         with the products in the response body.
     *         If the category does not exist, it returns an HTTP 400 (Bad Request) status.
     *         If no products are found, it returns an HTTP 204 (No Content) status.
     *
     * This method allows the client to retrieve all products within a category for display purposes.
     */
    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        Category category;
        try {
            category = Category.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(products);
    }
}