package com.shop.backend.repository;

import com.shop.backend.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    // Trouver un panier par l'ID de l'utilisateur
    ShoppingCart findByUserUserID(int userID);
}