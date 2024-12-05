package com.shop.backend.controller;

import com.shop.backend.dto.*;
import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.service.ShoppingCartService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour gérer les opérations relatives aux paniers d'achat.
 *
 * Fournit des points d'accès pour :
 * - Créer un nouveau panier.
 * - Ajouter un produit dans un panier.
 * - Mettre à jour la quantité d'un produit dans un panier.
 * - Supprimer un produit d'un panier.
 * - Récupérer un panier existant.
 */
@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Crée un nouveau panier d'achat.
     *
     * Si un utilisateur est identifié dans la requête, le panier sera associé à cet utilisateur.
     * Sinon, un panier anonyme sera créé.
     *
     * @param userCartRequest Les informations de l'utilisateur pour la création du panier (facultatif).
     * @return Un {@link ResponseEntity} contenant le DTO du panier créé.
     */
    @PostMapping
    public ResponseEntity<ShoppingCartDTO> newCart(@RequestBody(required = false) AddToCartRequest userCartRequest) {
        Integer userId = (userCartRequest != null) ? userCartRequest.getUserId() : null;
        ShoppingCartDTO cartDTO = new ShoppingCartDTO(shoppingCartService.createNewCart(userId));
        return ResponseEntity.ok(cartDTO);
    }

    /**
     * Ajoute un produit à un panier existant ou en crée un nouveau si nécessaire.
     *
     * Si le panier n'existe pas, un nouveau panier est créé pour l'utilisateur spécifié
     * (ou pour un utilisateur anonyme si aucun utilisateur n'est fourni).
     *
     * @param cartIdCookie L'identifiant du panier transmis via un cookie (facultatif).
     * @param response     La réponse HTTP pour définir les cookies.
     * @param request      Les détails du produit à ajouter (ID produit, quantité, utilisateur).
     * @return Un {@link ResponseEntity} contenant le panier mis à jour sous forme de DTO.
     */
    @PostMapping("/products")
    public ResponseEntity<ShoppingCartDTO> addToCart(
            @CookieValue(value = "cartId", required = false) String cartIdCookie,
            HttpServletResponse response,
            @RequestBody AddToCartRequest request) {

        // Récupère l'identifiant du panier depuis la requête
        Integer cartId = request.getCartId();

        // Vérifie si l'ID du panier est invalide ou si le panier n'existe pas
        if (cartId == null || shoppingCartService.getShoppingCartById(cartId) == null) {
            // Crée un nouveau panier (anonyme ou associé à l'utilisateur)
            ShoppingCart newCart = shoppingCartService.createNewCart(request.getUserId());
            cartId = newCart.getCartID();

            // Met à jour le cookie avec le nouvel ID de panier
            Cookie cookie = new Cookie("cartId", String.valueOf(cartId));
            cookie.setHttpOnly(false); // Permet l'accès au cookie côté client
            cookie.setPath("/"); // Définit le chemin pour lequel le cookie est valide
            cookie.setMaxAge(604800); // Durée de vie : 7 jours (en secondes)
            response.addCookie(cookie); // Ajoute le cookie à la réponse
        }

        // Ajoute le produit spécifié au panier identifié
        ShoppingCartDTO updatedCart = shoppingCartService.addProductToCart(
                cartId,
                request.getProductId(),
                request.getQuantity(),
                request.getUserId()
        );

        // Retourne le panier mis à jour au client
        return ResponseEntity.ok(updatedCart);
    }



    /**
     * Met à jour la quantité d'un produit dans un panier.
     *
     * Permet d'ajuster la quantité d'un produit déjà présent dans le panier.
     *
     * @param cartId                L'ID du panier.
     * @param productId             L'ID du produit à mettre à jour.
     * @param productQuantityRequest Un objet contenant la nouvelle quantité du produit.
     * @return Un {@link ResponseEntity} contenant le panier mis à jour sous forme de DTO.
     */
    @PutMapping("/{cartId}/products/{productId}")
    public ResponseEntity<ShoppingCartDTO> updateQuantity(
            @PathVariable int cartId,
            @PathVariable int productId,
            @RequestBody AddToCartRequest productQuantityRequest) {
        ShoppingCartDTO updatedCart = shoppingCartService.updateProductQuantity(
                cartId,
                productId,
                productQuantityRequest.getQuantity());
        return ResponseEntity.ok(updatedCart);
    }

    /**
     * Supprime un produit d'un panier.
     *
     * Retire le produit spécifié du panier. Si le panier devient vide après cette opération,
     * une réponse avec un statut HTTP 204 (No Content) est renvoyée.
     *
     * @param cartId    L'ID du panier.
     * @param productId L'ID du produit à supprimer.
     * @return Un {@link ResponseEntity} contenant le panier mis à jour sous forme de DTO
     *         ou un statut HTTP 204 si le panier est vide.
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
     * Récupère un panier par son ID.
     *
     * Permet d'obtenir les détails d'un panier existant, y compris les produits qu'il contient.
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