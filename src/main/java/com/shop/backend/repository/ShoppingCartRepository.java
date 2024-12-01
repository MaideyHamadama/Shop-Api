package com.shop.backend.repository;

import com.shop.backend.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository pour gérer les entités {@link ShoppingCart}.
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    /**
     * Rechercher un panier associé à un utilisateur par son ID.
     *
     * @param userId L'ID de l'utilisateur.
     * @return Un {@link Optional} contenant le panier s'il existe.
     */
    @Query("SELECT cart FROM ShoppingCart cart WHERE cart.user.id = :userId")
    Optional<ShoppingCart> findByUserId(@Param("userId") int userId);
}