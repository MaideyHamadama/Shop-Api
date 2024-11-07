package unused;

import com.shop.backend.classe.ProductVariant;

public class ShoppingCartLine {
    private ProductVariant variant;
    private int quantity;
    private double totalPrice;

    public ShoppingCartLine(ProductVariant variant, int quantity) {
        this.variant = variant;
        this.quantity = quantity;
        this.totalPrice = variant.getPrice() * quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
