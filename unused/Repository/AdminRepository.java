package com.shop.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unused.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    
}
