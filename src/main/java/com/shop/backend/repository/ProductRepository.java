package com.shop.backend.repository;

import com.shop.backend.entity.Category;
import com.shop.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour gérer les entités {@link Product}.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Récupère tous les produits appartenant à une catégorie spécifique.
     *
     * @param category La catégorie des produits à récupérer.
     * @return Une liste d'instances de {@link Product} appartenant à la catégorie spécifiée.
     */
    List<Product> findByCategory(Category category);
}