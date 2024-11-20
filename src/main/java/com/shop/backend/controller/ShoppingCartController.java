/*package com.shop.backend.controller;

import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.entity.ShoppingCartLine;
import com.shop.backend.entity.Product;
import com.shop.backend.repository.ShoppingCartRepository;
import com.shop.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event.ID;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;
*/
    /**
     * Ajouter un produit au panier
     * @param cartId L'ID du panier
     * @param productId L'ID du produit à ajouter
     * @param quantity La quantité du produit à ajouter
     * @return Le panier mis à jour
     */
    /*@PostMapping("/{cartId}/add")
    public ResponseEntity<ShoppingCart> addToCart(@PathVariable int cartId, @RequestParam Long productId, @RequestParam int quantity) {
        // Récupérer le panier
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(cartId);
        if (!shoppingCartOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ShoppingCart cart = shoppingCartOptional.get();

        // Récupérer le produit
        Optional<Product> productOptional = productRepository.findById(productIdId) ;
        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Product product = productOptional.get();

        // Vérifier si le produit existe déjà dans le panier
        ShoppingCartLine existingLine = cart.getCartProducts().stream()
                .filter(line -> line.getProduct().getProductID().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingLine != null) {
            // Si le produit est déjà dans le panier, mettre à jour la quantité
            existingLine.setQuantity(existingLine.getQuantity() + quantity);
        } else {
            // Sinon, ajouter un nouvel article dans le panier
            ShoppingCartLine newLine = new ShoppingCartLine(product, quantity);
            newLine.setShoppingCart(cart); // Set the shopping cart for the new line
            cart.getCartProducts().add(newLine); // Add the line to the cart
        }

        // Calculer le montant total du panier
        double totalAmount = cart.getCartProducts().stream()
                .mapToDouble(line -> line.getTotalPrice())
                .sum();
        cart.setCartTotalPrice(totalAmount);

        // Sauvegarder les modifications dans la base de données
        shoppingCartRepository.save(cart);

        // Retourner le panier mis à jour
        return ResponseEntity.ok(cart);
    }
}
*/
