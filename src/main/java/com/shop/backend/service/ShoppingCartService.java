package com.shop.backend.service;

import com.shop.backend.dto.ShoppingCartDTO;
import com.shop.backend.entity.Product;
import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.entity.ShoppingCartLine;
import com.shop.backend.entity.User;
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

    // ===========================
    //        Méthodes
    // ===========================

    public ShoppingCart getShoppingCartByIdOrCreate(int cartId, Integer userId) {
        return shoppingCartRepository.findById(cartId).orElseGet(() -> {
            return createNewCart(userId);
        });
    }

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

    private ShoppingCartLine findProductInCart(ShoppingCart cart, int productId) {
        return cart.getCartProducts().stream()
                .filter(line -> line.getProduct().getIdProduct() == productId)
                .findFirst()
                .orElse(null);
    }

    private ShoppingCartLine findOrCreateCartLine(ShoppingCart cart, Product product, int quantity) {
        ShoppingCartLine line = findProductInCart(cart, product.getIdProduct());
        double productPrice = product.getPrice();
        if (line == null) {
            double totalPrice = productPrice * quantity;
            line = new ShoppingCartLine(cart, product, quantity);
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

    public ShoppingCartDTO addProductToCart(Integer cartId, int productId, int quantity, Integer userId) {
        ShoppingCart cart = (cartId != null && cartId != 0) ?
                getShoppingCartByIdOrCreate(cartId, userId) :
                createNewCart(userId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + productId));
        findOrCreateCartLine(cart, product, quantity);
        reCalculateTotalPrice(cart);
        shoppingCartRepository.save(cart);
        return new ShoppingCartDTO(cart);
    }

    public ShoppingCartDTO updateProductQuantity(int cartId, int productId, int quantity) {
        ShoppingCart cart = getShoppingCartById(cartId);
        ShoppingCartLine existingLine = findProductInCart(cart, productId);
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

    public ShoppingCartDTO removeProductFromCart(int cartId, int productId) {
        ShoppingCart cart = getShoppingCartById(cartId);
        ShoppingCartLine lineToRemove = findProductInCart(cart, productId);
        if (lineToRemove == null) {
            throw new RuntimeException("Produit non trouvé dans le panier avec l'ID : " + productId);
        }
        cart.getCartProducts().remove(lineToRemove);
        shoppingCartLineRepository.delete(lineToRemove);
        reCalculateTotalPrice(cart);
        shoppingCartRepository.save(cart);
        return new ShoppingCartDTO(cart);
    }
}