package com.shop.backend.controller;

import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.entity.ShoppingCartLine;
import com.shop.backend.entity.Product;
import com.shop.backend.entity.User;
import com.shop.backend.repository.ShoppingCartRepository;
import com.shop.backend.repository.ProductRepository;
import com.shop.backend.repository.UserRepository;
import com.shop.backend.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {


    private final ShoppingCartService shoppingCartService;

    private final ShoppingCartRepository shoppingCartRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ShoppingCartRepository shoppingCartRepository,
                                  ProductRepository productRepository, UserRepository userRepository) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/new")
    public ResponseEntity<ShoppingCart> newCart(@RequestParam(required = false) Integer userId) {
        User user = null;

        // Rechercher l'utilisateur si userId est fourni
        if (userId != null) {
            user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return ResponseEntity.badRequest().body(null); // Retourner une erreur si l'utilisateur n'existe pas
            }
        }

        // Créer un panier via le service
        ShoppingCart cart = shoppingCartService.createCart(user);

        return ResponseEntity.ok(cart); // Retourner le panier créé
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
    public ResponseEntity<ShoppingCart> addToCart(@PathVariable int cartId, @RequestParam int productId, @RequestParam int quantity) {
        // Récupérer le panier
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(cartId);
        if (!shoppingCartOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ShoppingCart cart = shoppingCartOptional.get();

        // Récupérer le produit
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Product product = productOptional.get();

        // Vérifier si le produit existe déjà dans le panier
        ShoppingCartLine existingLine = cart.getCartProducts().stream()
                .filter(line -> line.getProduct().getIdProduct() == productId)
                .findFirst()
                .orElse(null);

        if (existingLine != null) {
            // Si le produit est déjà dans le panier, mettre à jour la quantité
            existingLine.setQuantity(existingLine.getQuantity() + quantity);
        } else {
            // Sinon, ajouter un nouvel article dans le panier
            ShoppingCartLine newLine = new ShoppingCartLine(product, quantity, cart);
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
    @PutMapping("/{cartId}/update")
    public ResponseEntity<ShoppingCart> updateQuantity(@PathVariable int cartId, @RequestParam int productId, @RequestParam int quantity) {
        // Récupérer le panier
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé avec l'ID: " + cartId));

        // Récupérer le produit
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + productId));

        // Vérifier si le produit existe dans le panier
        ShoppingCartLine existingLine = cart.getCartProducts().stream()
                .filter(line -> line.getProduct().getIdProduct() == productId)
                .findFirst()
                .orElse(null);

        if (existingLine == null) {
            // Si le produit n'est pas dans le panier, renvoyer une erreur
            return ResponseEntity.status(404).body(null); // Le produit n'est pas dans le panier
        }

        // Mettre à jour la quantité du produit
        if (quantity > 0) {
            existingLine.setQuantity(quantity);
        } else {
            // Si la quantité est 0 ou négative, on retire le produit du panier
            cart.getCartProducts().remove(existingLine);
        }

        // Calculer le montant total du panier après mise à jour
        double totalAmount = cart.getCartProducts().stream()
                .mapToDouble(ShoppingCartLine::getTotalPrice)
                .sum();
        cart.setCartTotalPrice(totalAmount);

        // Sauvegarder les modifications dans la base de données
        shoppingCartRepository.save(cart);

        // Retourner le panier mis à jour
        return ResponseEntity.ok(cart);
    }

    /**
     * Retirer un produit du panier
     * @param cartId L'ID du panier
     * @param productId L'ID du produit à retirer
     * @return Le panier mis à jour
     */
    @DeleteMapping("/{cartId}/remove")
    public ResponseEntity<ShoppingCart> removeFromCart(@PathVariable int cartId, @RequestParam int productId) {
        // Récupérer le panier
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé avec l'ID: " + cartId));

        // Récupérer le produit
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + productId));

        // Vérifier si le produit existe dans le panier
        ShoppingCartLine lineToRemove = cart.getCartProducts().stream()
                .filter(line -> line.getProduct().getIdProduct() == productId)
                .findFirst()
                .orElse(null);

        if (lineToRemove == null) {
            // Si le produit n'est pas dans le panier, renvoyer une erreur
            return ResponseEntity.status(404).body(null); // Le produit n'est pas dans le panier
        }

        // Supprimer la ligne du panier
        cart.getCartProducts().remove(lineToRemove);

        // Calculer le montant total du panier après suppression
        double totalAmount = cart.getCartProducts().stream()
                .mapToDouble(ShoppingCartLine::getTotalPrice)
                .sum();
        cart.setCartTotalPrice(totalAmount);

        // Sauvegarder les modifications dans la base de données
        shoppingCartRepository.save(cart);

        // Retourner le panier mis à jour
        return ResponseEntity.ok(cart);

    }
    /**
     * Récupérer le panier d'un utilisateur
     * @param cartId L'ID du panier
     * @return Le panier avec ses lignes de produits
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable int cartId) {
        // Récupérer le panier
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé avec l'ID: " + cartId));

        // Retourner le panier avec ses lignes de produits et le prix total
        return ResponseEntity.ok(cart);
    }
}