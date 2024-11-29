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

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> newCart(@RequestBody(required = false) UserCartRequest userCartRequest) {
        Integer userId = (userCartRequest != null) ? userCartRequest.getUserId() : null;
        ShoppingCartDTO cartDTO = new ShoppingCartDTO(shoppingCartService.createNewCart(userId));
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
     * Mettre à jour la quantité d'un produit dans le panier
     *
     * @param cartId    L'ID du panier
     * @param productId L'ID du produit à mettre à jour
     * @param quantity  La nouvelle quantité
     * @return Le panier mis à jour
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
     * Retirer un produit du panier
     *
     * @param cartId    L'ID du panier
     * @param productId L'ID du produit à retirer
     * @return Le panier mis à jour
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
