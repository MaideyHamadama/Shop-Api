package com.shop.backend.repository;

import com.shop.backend.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



/**
 * Repository pour gérer les entités {@link ShoppingCart}.
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}