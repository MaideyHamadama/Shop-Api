package com.shop.backend.service;

import com.shop.backend.entity.Size;
import com.shop.backend.entity.SizeType;
import com.shop.backend.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    // Créer une taille
    public Size createSize(Size size) {
        return sizeRepository.save(size);
    }

    // Obtenir toutes les tailles par type (ADULT ou CHILD)
    public List<Size> getSizesByType(SizeType type) {
        return sizeRepository.findByType(type);
    }

    // Obtenir une taille par ID
    public Optional<Size> getSizeById(int id) {
        return sizeRepository.findById(id);
    }

    // Mettre à jour une taille
    public Size updateSize(int id, Size size) {
        if (sizeRepository.existsById(id)) {
            size.setId(id);
            return sizeRepository.save(size);
        }
        return null; // Ou vous pouvez lancer une exception si taille non trouvée
    }

    // Supprimer une taille
    public void deleteSize(int id) {
        sizeRepository.deleteById(id);
    }
}
