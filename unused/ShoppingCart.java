package unused;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int cartID;
    private int userID;
    private List<ShoppingCartLine> cartProducts;
    private double cartTotalPrice;

    public ShoppingCart(int cartID, int userID) {
        this.cartID = cartID;
        this.userID = userID;
        this.cartProducts = new ArrayList<>();
        this.cartTotalPrice = 0.0;
    }

    public void addProduct(ShoppingCartLine line) {
        cartProducts.add(line);
        cartTotalPrice += line.getTotalPrice();
    }

    public void removeProduct(ShoppingCartLine line) {
        cartProducts.remove(line);
        cartTotalPrice -= line.getTotalPrice();
    }

    public double getCartTotalPrice() {
        return cartTotalPrice;
    }

    public int getCartID() {
        return cartID;
    }

    public int getUserID() {
        return userID;
    }

    public List<ShoppingCartLine> getCartProducts() {
        return cartProducts;
    }
    
}
