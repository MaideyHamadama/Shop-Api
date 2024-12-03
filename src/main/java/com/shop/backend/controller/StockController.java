package com.shop.backend.controller;

import com.shop.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * Récupère la quantité disponible d'un produit par son ID.
     * 
     * @param productId ID du produit
     * @return La quantité disponible dans le stock
     */
    @GetMapping("/quantity/{productId}")
    public int getAvailableQuantity(@PathVariable int productId) {
        return stockService.getAvailableQuantityByProductId(productId);
    }

    /**
     * Retire une certaine quantité d'un produit dans le stock.
     * 
     * @param productId ID du produit
     * @param quantityToRemove Quantité à retirer du stock
     * @return La quantité restante du produit dans le stock
     */
    @PostMapping("/remove/{productId}")
    public int removeProductFromStock(@PathVariable int productId, @RequestParam int quantityToRemove) {
        // Appeler le service pour retirer la quantité du produit dans le stock
        return stockService.removeProductFromStock(productId, quantityToRemove);
    }
       

     // Ajouter une quantité au stock d'un produit
     @PostMapping("/add/{productId}")
     public int addProductToStock(@PathVariable int productId, @RequestParam int quantityToAdd) {
         return stockService.addProductToStock(productId, quantityToAdd);

    }
}

