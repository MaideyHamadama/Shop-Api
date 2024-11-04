package unused;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shop.backend.Repository.ShoppingCartRepository;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository
     shoppingCartRepository
    ;

    @GetMapping
    public ShoppingCart getShoppingCart() {
        return shoppingCartRepository
        .getShoppingCart();
    }

    @PostMapping("/add")
    public ShoppingCartLine addProductToCart(@RequestBody ShoppingCartLine cartLine) {
        return shoppingCartRepository
        .addProductToCart(cartLine);
    }

    @DeleteMapping("/remove/{productId}")
    public void removeProductFromCart(@PathVariable int productId) {
        shoppingCartRepository
        .removeProductFromCart(productId);
    }

    @PutMapping("/update/{productId}")
    public ShoppingCartLine updateProductQuantity(@PathVariable int productId, @RequestParam int quantity) {
        return shoppingCartRepository
        .updateProductQuantity(productId, quantity);
    }
}
