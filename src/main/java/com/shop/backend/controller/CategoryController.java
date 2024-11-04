package com.shop.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.shop.backend.Repository.CategoryRepository;
import com.shop.backend.classe.Category;
import com.shop.backend.classe.Product;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    // Un tableau suffit pour afficher la liste des categories car c'est un enum
    // et donc les categories sont fixes
    @GetMapping
    public ResponseEntity<Category[]> getAllCategories() {
        return ResponseEntity.ok().body(Category.values());
    }



/* Mac Code
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @GetMapping("/{id}/products")
    public List<Product> getProductsByCategory(@PathVariable int id) {
        return categoryRepository.getProductsByCategory(id);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.addCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryRepository.deleteCategory(id);
    }

 */
}
