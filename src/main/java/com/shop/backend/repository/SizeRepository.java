package com.shop.backend.repository;

import com.shop.backend.entity.Size;
import com.shop.backend.entity.SizeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pour gérer les entités {@link Size}.
 */
@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    // Rechercher une taille spécifique par son type (ADULT ou CHILD)
    Optional<Size> findByTypeAndAdultSizeIsNotNull(SizeType type);

    Optional<Size> findByTypeAndChildSizeIsNotNull(SizeType type);

    // Trouver toutes les tailles par type (Adult ou Child)
    List<Size> findByType(SizeType type);
}