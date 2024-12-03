package com.shop.backend.service;

import com.shop.backend.entity.Stock;
import com.shop.backend.entity.Product; // Assurez-vous d'avoir la classe Product
import com.shop.backend.repository.StockRepository;
import com.shop.backend.repository.ProductRepository; // Repositoire pour accéder aux produits
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository; // Pour rechercher un produit

    // Méthode pour obtenir la quantité disponible d'un produit par son ID
    public int getAvailableQuantityByProductId(int productId) {
        Optional<Stock> stockOptional = stockRepository.findByProduct_IdProduct(productId);

        if (stockOptional.isPresent()) {
            return stockOptional.get().getAvailableQuantity();
        } else {
            throw new RuntimeException("Aucun stock trouvé pour le produit avec l'ID " + productId);
        }
    }

    // Méthode pour retirer une quantité du stock d'un produit par son ID
    public int removeProductFromStock(int productId, int quantityToRemove) {
        Optional<Stock> stockOptional = stockRepository.findByProduct_IdProduct(productId);

        if (!stockOptional.isPresent()) {
            throw new RuntimeException("Le produit avec l'ID " + productId + " n'existe pas dans le stock.");
        }

        Stock stock = stockOptional.get();

        if (quantityToRemove > stock.getAvailableQuantity()) {
            throw new RuntimeException("La quantité à retirer est supérieure à la quantité disponible dans le stock.");
        }

        stock.setAvailableQuantity(stock.getAvailableQuantity() - quantityToRemove);

        stockRepository.save(stock);

        return stock.getAvailableQuantity();
    }

    // Méthode pour ajouter une quantité au stock d'un produit
    public int addProductToStock(int productId, int quantityToAdd) {
        // Vérifier si la quantité à ajouter est valide
        if (quantityToAdd <= 0) {
            throw new IllegalArgumentException("La quantité à ajouter doit être supérieure à zéro.");
        }

        // Chercher le produit par son ID pour obtenir l'entité Product
        Optional<Product> productOptional = productRepository.findById(productId);
        
        if (!productOptional.isPresent()) {
            throw new RuntimeException("Aucun produit trouvé pour l'ID " + productId);
        }

        Product product = productOptional.get(); // Récupérer l'entité Product

        // Chercher le stock du produit
        Optional<Stock> stockOptional = stockRepository.findByProduct_IdProduct(productId);

        Stock stock;

        if (stockOptional.isPresent()) {
            stock = stockOptional.get();
            stock.setAvailableQuantity(stock.getAvailableQuantity() + quantityToAdd); // Ajouter au stock existant
        } else {
            // Si aucun stock existant, on crée un nouveau stock pour ce produit
            stock = new Stock();
            stock.setAvailableQuantity(quantityToAdd);
            stock.setProduct(product); // Associer le stock avec le produit
        }

        stockRepository.save(stock); // Sauvegarder ou mettre à jour le stock

        return stock.getAvailableQuantity(); // Retourner la nouvelle quantité disponible
    }
}
