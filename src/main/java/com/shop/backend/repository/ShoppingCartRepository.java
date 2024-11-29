package com.shop.backend.repository;

import com.shop.backend.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    // Rechercher un panier associé à un utilisateur par son ID
    // TODO : Vérifier et adapter cette méthode une fois que le mappage entre User et ShoppingCart est validé
    @Query("SELECT cartID FROM ShoppingCart WHERE user = ?1")
    Optional<ShoppingCart> findByUserId(@Param("userId") int userId);
}