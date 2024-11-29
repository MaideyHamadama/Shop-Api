package com.shop.backend.controller;

import com.shop.backend.dto.ShoppingCartDTO;
import com.shop.backend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/new")
    public ResponseEntity<ShoppingCartDTO> newCart(@RequestParam(required = false) Integer userId) {
        // Créer un nouveau panier pour un utilisateur (ou un visiteur)
        ShoppingCartDTO cartDTO = userId != null
                ? shoppingCartService.getOrCreateCartForUser(userId)
                : shoppingCartService.createCartForVisitor();
        return ResponseEntity.ok(cartDTO);
    }

    /**
     * Ajouter un produit au panier
     *
     * @param cartId    L'ID du panier
     * @param productId L'ID du produit à ajouter
     * @param quantity  La quantité du produit à ajouter
     * @return Le panier mis à jour
     */
    @PostMapping("/{cartId}/add")
    public ResponseEntity<ShoppingCartDTO> addToCart(@PathVariable int cartId, @RequestParam int productId, @RequestParam int quantity) {
        ShoppingCartDTO updatedCart = shoppingCartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Mettre à jour la quantité d'un produit dans le panier
     *
     * @param cartId    L'ID du panier
     * @param productId L'ID du produit à mettre à jour
     * @param quantity  La nouvelle quantité
     * @return Le panier mis à jour
     */
    @PutMapping("/{cartId}/update")
    public ResponseEntity<ShoppingCartDTO> updateQuantity(@PathVariable int cartId, @RequestParam int productId, @RequestParam int quantity) {
        ShoppingCartDTO updatedCart = shoppingCartService.updateProductQuantity(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Retirer un produit du panier
     *
     * @param cartId    L'ID du panier
     * @param productId L'ID du produit à retirer
     * @return Le panier mis à jour
     */
    @DeleteMapping("/{cartId}/remove")
    public ResponseEntity<ShoppingCartDTO> removeFromCart(@PathVariable int cartId, @RequestParam int productId) {
        ShoppingCartDTO updatedCart = shoppingCartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Récupérer un panier par son ID
     *
     * @param cartId L'ID du panier
     * @return Le panier sous forme de DTO
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCartDTO> getCart(@PathVariable int cartId) {
        ShoppingCartDTO cartDTO = new ShoppingCartDTO(shoppingCartService.getShoppingCartById(cartId));
        return ResponseEntity.ok(cartDTO);
    }
}
