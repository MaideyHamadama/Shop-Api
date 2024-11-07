package com.shop.backend.controller;

import com.shop.backend.classe.Brand;
import com.shop.backend.classe.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.shop.backend.Repository.ProductRepository;
import com.shop.backend.classe.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    //Initialisation de données de test en attendant la base de données
    private List<Product> products = new ArrayList<>(List.of(
            new Product(1, "T-shirt1", 20.0, new Brand(1, "Nike"), Category.BOY),
            new Product(2, "T-shirt2", 25.0, new Brand(2, "Adidas"), Category.MEN),
            new Product(3, "T-shirt3", 30.0, new Brand(3, "Puma"), Category.MEN))
    );

    // GET - Liste de tous les produits
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(products);
    }

    // GET - Obtenir un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = products.stream()
                .filter(p -> p.getIdProduct() == id).findFirst()
                .orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build(); // Retourne 404 si le produit n'existe pas
        }
        return ResponseEntity.ok().body(product);
    }

    // GET - Filtrer les produits par catégorie
    @GetMapping("/categories/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        try {
            Category categoryEnum = Category.valueOf(category.toUpperCase());
            List<Product> productsByCategory = products.stream()
                    .filter(product -> product.getCategory() == categoryEnum)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(productsByCategory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Retourne 400 si la catégorie n'est pas valide
        }
    }

/*
    @GetMapping("/categories/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                productsByCategory.add(product);
            }
        }
        return ResponseEntity.ok().body(productsByCategory);
    }
    */

    /* Mac code
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productRepository.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productRepository.deleteProduct(id);
    }

     */
}
