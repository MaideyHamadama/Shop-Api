package com.shop.backend.service;

import com.shop.backend.entity.Product;
import com.shop.backend.entity.ShoppingCart;
import com.shop.backend.entity.ShoppingCartLine;
import com.shop.backend.entity.User;
import com.shop.backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart createCart(User user) {
        ShoppingCart cart;
        if (user == null) {
            cart = new ShoppingCart();
        }
        else {
            cart = new ShoppingCart(user);
        }
        return shoppingCartRepository.save(cart);
    }

    public void addProductToCart(Product product, int quantity) {
    }

    public void removeProductFromCart(Product product) {
    }
}
