package com.shop.backend.repository;

import com.shop.backend.entity.Category;
import com.shop.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing {@link Product} entities.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Retrieves all products belonging to a specific category.
     *
     * @param category The category of products to retrieve.
     * @return A list of {@link Product} instances that belong to the specified category.
     *
     * This method enables filtering products by category, returning only those associated
     * with the specified category in the database.
     */
    List<Product> findByCategory(Category category);
}