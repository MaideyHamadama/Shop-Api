package com.shop.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.backend.classe.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    
}
