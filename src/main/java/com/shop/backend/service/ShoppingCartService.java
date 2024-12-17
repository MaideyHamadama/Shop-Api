package com.shop.backend.service;

import com.shop.backend.dto.ShoppingCartDTO;
import com.shop.backend.entity.*;
import com.shop.backend.repository.ProductRepository;
import com.shop.backend.repository.ShoppingCartLineRepository;
import com.shop.backend.repository.ShoppingCartRepository;
import com.shop.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service pour gérer les opérations liées aux paniers d'achat.
 */
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartLineRepository shoppingCartLineRepository;

    @Autowired
    private UserRepository userRepository;

    boolean productFound = false;

    // ===========================
    //        Méthodes
    // ===========================

    public ShoppingCart getShoppingCartById(int cartId) {
        return shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé avec l'ID: " + cartId));
    }

    private void reCalculateTotalPrice(ShoppingCart cart) {
        double totalWithVAT = cart.getCartProducts().stream()
                .mapToDouble(ShoppingCartLine::getTotalPrice) // Total TTC
                .sum();

        double totalExcludingVAT = cart.getCartProducts().stream()
                .mapToDouble(ShoppingCartLine::getTotalPriceExcludingVAT) // Total HT
                .sum();

        cart.setCartTotalPrice(totalWithVAT);
        cart.setCartTotalPriceExcludingVAT(totalExcludingVAT);
    }

    private ShoppingCartLine findProductInCart(ShoppingCart cart, int productId, String size) {
        return cart.getCartProducts().stream()
                .filter(line -> line.getProduct().getIdProduct() == productId &&
                        line.getSize().equals(size)) // Comparaison directe des tailles
                .findFirst()
                .orElse(null);
    }

   public void mergeCarts(ShoppingCart existingCart, ShoppingCart cookieCart) {

    // Parcourir les produits du panier des cookies
    for (ShoppingCartLine cookieLine : cookieCart.getCartProducts()) {
        productFound = false;

        // Parcourir les produits du panier existant en db
        for (ShoppingCartLine existingLine : existingCart.getCartProducts()) {
            // Vérifier si le produit ET la taille correspondent
            if (existingLine.getProduct().getIdProduct() == cookieLine.getProduct().getIdProduct() &&
                sizesMatch(existingLine.getSize(), cookieLine.getSize())) {

                // Produit et taille déjà dans le panier : mise à jour de la quantité et du total
                existingLine.setQuantity(existingLine.getQuantity() + cookieLine.getQuantity());
                existingLine.setTotalPrice(existingLine.getProduct().getPrice() * existingLine.getQuantity());
                productFound = true;
                break;
            }
        }

        // Produit ou taille non trouvé(e) : ajouter une nouvelle ligne au panier existant
        if (!productFound) {
            ShoppingCartLine newLine = new ShoppingCartLine(
                existingCart,
                cookieLine.getProduct(),
                cookieLine.getQuantity(),
                cookieLine.getSize() // Taille incluse
            );
            existingCart.getCartProducts().add(newLine);
        }
    }

    // Recalculer le total du panier après fusion
    reCalculateTotalPrice(existingCart);

    // Sauvegarder les changements
    shoppingCartRepository.save(existingCart);
}
private boolean sizesMatch(String size1, String size2) {
    if (size1 == null && size2 == null) {
        return true; // Les deux tailles sont nulles (pas de taille spécifiée)
    }
    if (size1 == null || size2 == null) {
        return false; // Une seule taille est null
    }
    return size1.trim().equalsIgnoreCase(size2.trim()); // Comparaison insensible à la casse et aux espaces
}


    private ShoppingCartLine findOrCreateCartLine(ShoppingCart cart, Product product, int quantity, String size) {
        ShoppingCartLine line = findProductInCart(cart, product.getIdProduct(), size);
        double productPrice = product.getPrice();
        if (line == null) {
            double totalPrice = productPrice * quantity;
            line = new ShoppingCartLine(cart, product, quantity, size);
            line.setSize(size); // Attribuer la taille
            line.setTotalPrice(totalPrice);
            cart.getCartProducts().add(line);
        } else {
            line.setQuantity(line.getQuantity() + quantity);
            double totalPrice = productPrice * line.getQuantity();
            line.setTotalPrice(totalPrice);
        }
        return line;
    }

    public ShoppingCart createNewCart(Integer userId) {
        ShoppingCart cart;
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + userId));
            cart = new ShoppingCart(user);
        } else {
            cart = new ShoppingCart();
        }
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCartDTO addProductToCart(Integer cartId, int productId, int quantity, Integer userId, String size) {
        if (cartId == null || cartId == 0) {
            throw new RuntimeException("Le panier spécifié n'existe pas. Veuillez créer un panier avant d'ajouter un produit.");
        }

        ShoppingCart cart = getShoppingCartById(cartId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + productId));

        findOrCreateCartLine(cart, product, quantity, size);
        reCalculateTotalPrice(cart);
        shoppingCartRepository.save(cart);
        return new ShoppingCartDTO(cart);
    }


    public ShoppingCartDTO updateProductQuantity(int cartId, int productId, int quantity, String size) {
        ShoppingCart cart = getShoppingCartById(cartId);
        ShoppingCartLine existingLine = findProductInCart(cart, productId, size);
        if (existingLine == null) {
            throw new RuntimeException("Produit non trouvé dans le panier avec l'ID : " + productId);
        }
        if (quantity == 0) {
            cart.getCartProducts().remove(existingLine);
            shoppingCartLineRepository.delete(existingLine);
        } else {
            existingLine.setQuantity(quantity);
        }
        reCalculateTotalPrice(cart);
        shoppingCartRepository.save(cart);
        return new ShoppingCartDTO(cart);
    }

    public void removeProductFromCart(int cartId, int productId, Size size) {
        if (size == null || size.getType() == null || (size.getAdultSize() == null && size.getChildSize() == null)) {
    throw new IllegalArgumentException("L'objet Size est invalide ou incomplet.");
}

        ShoppingCart cart = getShoppingCartById(cartId);
        

        System.out.println("TailleType: " + size.getType() + " - Taille: " + size.getAdultSize() + " - Taille enfant: " + size.getChildSize());

        ShoppingCartLine lineToRemove = cart.getCartProducts().stream()
                .filter(line -> line.getProduct().getIdProduct() == productId &&
                        ((size.getType() == SizeType.ADULT && size.getAdultSize() != null &&
                                line.getSize().trim().equalsIgnoreCase(size.getAdultSize().name())) ||
                                (size.getType() == SizeType.CHILD && size.getChildSize() != null &&
                                        line.getSize().trim().equalsIgnoreCase(size.getChildSize().name()))))

                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produit non trouvé dans le panier avec l'ID : " + productId));

        // Supprimer la ligne et recalculer le panier
        cart.getCartProducts().remove(lineToRemove);
        shoppingCartLineRepository.delete(lineToRemove);
        reCalculateTotalPrice(cart);
        shoppingCartRepository.save(cart);
    }
}