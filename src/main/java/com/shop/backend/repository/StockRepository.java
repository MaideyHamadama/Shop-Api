package com.shop.backend.repository;

import com.shop.backend.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    // Méthode pour trouver le stock par l'ID du produit
    Optional<Stock> findByProduct_IdProduct(int idProduct);
}

