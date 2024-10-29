package com.shop.backend.classe;

import java.util.ArrayList;
import java.util.List;

public class User extends Visitor {
    private int userID;
    private String userName;
    private String userSurname;
    private String address;
    private String city;
    private String email;
    private String password;
    protected String role;
    private List<Order> orderHistory;

    public User(int userID, String userName, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = "User";
        this.orderHistory = new ArrayList<>();
    }

    public void updateProfile(String newAddress, String newCity) {
        this.address = newAddress;
        this.city = newCity;
    }

    public void placeOrder(Order order) {
        orderHistory.add(order);
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
    
}
