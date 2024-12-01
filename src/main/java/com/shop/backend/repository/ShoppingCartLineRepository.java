package com.shop.backend.repository;

import com.shop.backend.entity.ShoppingCartLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour gérer les entités {@link ShoppingCartLine}.
 */
public interface ShoppingCartLineRepository extends JpaRepository<ShoppingCartLine, Integer> {
}