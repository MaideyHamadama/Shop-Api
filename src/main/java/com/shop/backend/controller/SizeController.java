package com.shop.backend.controller;

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
    public ResponseEntity<Size> createSize(@RequestBody Size size) {
        Size createdSize = sizeService.createSize(size);
        return new ResponseEntity<>(createdSize, HttpStatus.CREATED);
    }

    // Récupérer toutes les tailles d'un type spécifique (ADULT ou CHILD)
    @GetMapping("/{type}")
    public ResponseEntity<List<Size>> getSizesByType(@PathVariable SizeType type) {
        List<Size> sizes = sizeService.getSizesByType(type);
        if (sizes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sizes, HttpStatus.OK);
    }

    // Récupérer une taille par ID
    @GetMapping("/{sizeId}")
    public ResponseEntity<Size> getSizeById(@PathVariable int id) {
        Optional<Size> size = sizeService.getSizeById(id);
        return size.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mettre à jour une taille existante
    @PutMapping("/{sizeId}")
    public ResponseEntity<Size> updateSize(@PathVariable int id, @RequestBody Size size) {
        Size updatedSize = sizeService.updateSize(id, size);
        return updatedSize != null ? new ResponseEntity<>(updatedSize, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Supprimer une taille
    @DeleteMapping("/{sizeId}")
    public ResponseEntity<Void> deleteSize(@PathVariable int id) {
        sizeService.deleteSize(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
