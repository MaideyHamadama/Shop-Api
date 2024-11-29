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

import java.util.Optional;


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

    public ShoppingCart getShoppingCartById(int cartId) {
        return shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé avec l'ID: " + cartId));
    }

    private Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + productId));
    }

    private void reCalculateTotalPrice(ShoppingCart cart) {
        cart.setCartTotalPrice(cart.getCartProducts().stream()
                .mapToDouble(ShoppingCartLine::getTotalPrice)
                .sum());
    }

    private ShoppingCartLine findProductInCart(ShoppingCart cart, int productId) {
        return cart.getCartProducts().stream()
                .filter(line -> line.getProduct().getIdProduct() == productId)
                .findFirst()
                .orElse(null);
    }

    private ShoppingCartLine findOrCreateCartLine(ShoppingCart cart, Product product, int quantity) {
        ShoppingCartLine line = findProductInCart(cart, product.getIdProduct());
        if (line == null) {
            line = new ShoppingCartLine(product, quantity, cart);
            cart.getCartProducts().add(line);
        } else {
            line.setQuantity(line.getQuantity() + quantity);
        }
        return line;
    }

    private ShoppingCartDTO createCartForUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + userId));
        ShoppingCart cart = new ShoppingCart(user);
        return new ShoppingCartDTO(shoppingCartRepository.save(cart));
    }

    public ShoppingCartDTO getOrCreateCartForUser(int userId) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            ShoppingCart cart = optionalCart.get();
            return new ShoppingCartDTO(cart); // Convertir en DTO
        } else {
            return createCartForUser(userId); // Sinon, créer un panier
        }
    }

    // Création d'un panier pour un visiteur
    public ShoppingCartDTO createCartForVisitor() {
        ShoppingCart cart = new ShoppingCart();
        shoppingCartRepository.save(cart);
        return new ShoppingCartDTO(cart);
    }

    public ShoppingCartDTO addProductToCart(int cartId, int productId, int quantity) {
        ShoppingCart cart = getShoppingCartById(cartId);
        Product product = getProductById(productId);
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