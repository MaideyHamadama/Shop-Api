package com.shop.backend.dto;

import com.shop.backend.entity.Size;

public class AddToCartRequest {
    private Integer cartId;
    private int productId;
    private int quantity;
    private Integer userId;
    private Size size;

    public String getSizeName() {
        if (size == null) {
            throw new IllegalArgumentException("La taille (size) ne peut pas être null.");
        }

        if (size.getAdultSize() != null) {
            return size.getAdultSize().name();
        } else if (size.getChildSize() != null) {
            return size.getChildSize().name();
        } else {
            throw new IllegalStateException("Ni AdultSize ni ChildSize n'est défini dans Size.");
        }
    }

    // Getters et Setters
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
