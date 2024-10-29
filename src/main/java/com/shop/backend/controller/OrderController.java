package com.shop.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shop.backend.Repository.OrderRepository;
import com.shop.backend.classe.Order;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository
     orderRepository
    ;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository
        .getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderRepository
        .getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository
        .createOrder(order);
    }

    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        return orderRepository
        .updateOrderStatus(id, status);
    }
}

