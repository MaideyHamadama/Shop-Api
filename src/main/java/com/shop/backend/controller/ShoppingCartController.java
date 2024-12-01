package com.shop.backend.controller;

import com.shop.backend.dto.ProductCartRequest;
import com.shop.backend.dto.ProductQuantityRequest;
import com.shop.backend.dto.ShoppingCartDTO;
import com.shop.backend.dto.UserCartRequest;
import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour gérer les opérations relatives aux paniers d'achat.
 * Fournit des endpoints pour créer, récupérer, mettre à jour et supprimer des éléments des paniers.
 */
@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Crée un nouveau panier d'achat pour un utilisateur.
     *
     * @param userCartRequest Les informations de l'utilisateur (facultatif).
     * @return Un {@link ResponseEntity} contenant le DTO du panier créé.
     *
     * Cette méthode permet de créer un panier vide pour un utilisateur identifié
     * ou un utilisateur anonyme si aucune information n'est fournie.
     */
    @PostMapping
    public ResponseEntity<ShoppingCartDTO> newCart(@RequestBody(required = false) UserCartRequest userCartRequest) {
        Integer userId = (userCartRequest != null) ? userCartRequest.getUserId() : null;
        ShoppingCartDTO cartDTO = new ShoppingCartDTO(shoppingCartService.createNewCart(userId));
        return ResponseEntity.ok(cartDTO);
    }

    /**
     * Ajoute un produit dans un panier existant.
     *
     * @param cartId            L'ID du panier.
     * @param productCartRequest Les détails du produit et de la quantité à ajouter.
     * @return Un {@link ResponseEntity} contenant le panier mis à jour sous forme de DTO.
     */
    @PostMapping("/{cartId}/products")
    public ResponseEntity<ShoppingCartDTO> addToCart(
            @PathVariable(required = false) Integer cartId,
            @RequestBody ProductCartRequest productCartRequest) {
        ShoppingCartDTO updatedCart = shoppingCartService.addProductToCart(
                cartId,
                productCartRequest.getProductId(),
                productCartRequest.getQuantity(),
                productCartRequest.getUserId());
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Met à jour la quantité d'un produit dans le panier.
     *
     * @param cartId              L'ID du panier.
     * @param productId           L'ID du produit à mettre à jour.
     * @param productQuantityRequest La nouvelle quantité du produit.
     * @return Un {@link ResponseEntity} contenant le panier mis à jour sous forme de DTO.
     */
    @PutMapping("/{cartId}/products/{productId}")
    public ResponseEntity<ShoppingCartDTO> updateQuantity(
            @PathVariable int cartId,
            @PathVariable int productId,
            @RequestBody ProductQuantityRequest productQuantityRequest) {
        ShoppingCartDTO updatedCart = shoppingCartService.updateProductQuantity(
                cartId,
                productId,
                productQuantityRequest.getQuantity());
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Supprime un produit d'un panier existant.
     *
     * @param cartId    L'ID du panier.
     * @param productId L'ID du produit à retirer.
     * @return Un {@link ResponseEntity} contenant le panier mis à jour sous forme de DTO,
     * ou un statut HTTP 204 (No Content) si le panier est vide après la suppression.
     */
    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<ShoppingCartDTO> removeProductFromCart(@PathVariable int cartId, @PathVariable int productId) {
        shoppingCartService.removeProductFromCart(cartId, productId);
        ShoppingCart cart = shoppingCartService.getShoppingCartById(cartId);

        if (cart.getCartProducts().isEmpty()) {
            return ResponseEntity.noContent().build(); // Panier vide
        }

        ShoppingCartDTO updatedCart = new ShoppingCartDTO(cart);
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Récupère un panier d'achat par son ID.
     *
     * @param cartId L'ID du panier à récupérer.
     * @return Un {@link ResponseEntity} contenant le panier sous forme de DTO.
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCartDTO> getCart(@PathVariable int cartId) {
        ShoppingCartDTO cartDTO = new ShoppingCartDTO(shoppingCartService.getShoppingCartById(cartId));
        return ResponseEntity.ok(cartDTO);
    }
}
