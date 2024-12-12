package com.shop.backend.controller;

import com.shop.backend.dto.SizeDTO;
import com.shop.backend.entity.Size;
import com.shop.backend.entity.SizeType;
import com.shop.backend.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sizes")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    // Créer une nouvelle taille
    @PostMapping
    public ResponseEntity<SizeDTO> createSize(@RequestBody Size size) {
        Size createdSize = sizeService.createSize(size);
        return new ResponseEntity<>(new SizeDTO(createdSize), HttpStatus.CREATED);
    }

    // Récupérer toutes les tailles d'un type spécifique (ADULT ou CHILD)
    @GetMapping("/type/{type}")
    public ResponseEntity<List<SizeDTO>> getSizesByType(@PathVariable SizeType type) {
        List<Size> sizes = sizeService.getSizesByType(type);
        if (sizes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // Convertir les entités en DTO directement
        List<SizeDTO> sizeDTOs = sizes.stream()
                .map(SizeDTO::new) // Utilise le constructeur de SizeDTO
                .toList();
        return new ResponseEntity<>(sizeDTOs, HttpStatus.OK);
    }

    // Récupérer une taille par ID
    @GetMapping("/{sizeId}")
    public ResponseEntity<SizeDTO> getSizeById(@PathVariable int sizeId) {
        Optional<Size> size = sizeService.getSizeById(sizeId);
        return size.map(s -> new ResponseEntity<>(new SizeDTO(s), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mettre à jour une taille existante
    @PutMapping("/{sizeId}")
    public ResponseEntity<SizeDTO> updateSize(@PathVariable int sizeId, @RequestBody Size size) {
        Size updatedSize = sizeService.updateSize(sizeId, size);
        return updatedSize != null ?
                new ResponseEntity<>(new SizeDTO(updatedSize), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Supprimer une taille
    @DeleteMapping("/{sizeId}")
    public ResponseEntity<Void> deleteSize(@PathVariable int sizeId) {
        sizeService.deleteSize(sizeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}