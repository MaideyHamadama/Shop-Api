package com.shop.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shop.backend.Repository.AdminRepository;
import com.shop.backend.classe.Category;
import com.shop.backend.classe.Order;
import com.shop.backend.classe.Product;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository
     adminRepository
    ;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return adminRepository
        .addProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id) {
        adminRepository
        .deleteProduct(id);
    }

    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category) {
        return adminRepository
        .addCategory(category);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable int id) {
        adminRepository
        .deleteCategory(id);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return adminRepository
        .getAllOrders();
    }
}
