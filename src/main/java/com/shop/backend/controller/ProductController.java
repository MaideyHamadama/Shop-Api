package com.shop.backend.controller;

import com.shop.backend.entity.Product;
import com.shop.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository ProductRepository;

    /**
     * Retrieves the list of all products from the database.
     *
     * @return A {@link ResponseEntity} containing a list of all {@link Product} instances.
     *
     *         If the retrieval is successful, it returns an HTTP 200 (OK) status
     *         with the products in the response body.
     *         If no products are found, it returns an HTTP 204 (No Content) status.
     *
     * This method allows the client to retrieve all products from the database for display purposes.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = ProductRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(products);
    }

    /**
     * Retrieves the information of a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return A {@link ResponseEntity} containing the information of the {@link Product}.
     *
     *         If the retrieval is successful, it returns an HTTP 200 (OK) status
     *         with the product details in the response body.
     *         If the product does not exist, it returns an HTTP 404 (Not Found) status.
     *
     * This method allows the client to retrieve the details of a product for display purposes.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = ProductRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(product);
    }
}