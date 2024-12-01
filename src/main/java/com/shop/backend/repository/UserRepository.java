package com.shop.backend.repository;

import com.shop.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository pour gérer les entités {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Récupère un utilisateur par son email.
     *
     * @param email L'email de l'utilisateur.
     * @return Un {@link Optional} contenant l'utilisateur s'il existe.
     */
    Optional<User> findByEmail(String email);
}