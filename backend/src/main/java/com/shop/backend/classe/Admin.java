package com.shop.backend.classe;

public class Admin extends User {
    private int adminID;
    private String adminName;

    public Admin(int adminID, String adminName, String email, String password) {
        super(adminID, adminName, email, password);
        this.role = "Admin";
    }

    public void addProduct(Product product) {
        // Code to add product to inventory
    }

    public void deleteProduct(Product product) {
        // Code to delete product from inventory
    }

    public void manageUser(User user) {
        // Code to manage user details
    }

    public void manageOrder(Order order) {
        // Code to manage orders
    }

    public String getAdminName() {
        return adminName;
    }

    public int getAdminID() {
        return adminID;
    }
}
