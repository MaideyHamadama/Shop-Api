package com.shop.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entité représentant un utilisateur dans la base de données.
 * Cette classe est mappée à la table "User" et inclut des attributs pour
 * les détails de l'utilisateur ainsi que son panier associé.
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "userName", nullable = false)
    @NotEmpty(message = "Le nom est requis")
    private String userName;

    @Column(name = "userSurname", nullable = false)
    @NotEmpty(message = "Le prénom est requis")
    private String userSurname;

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "L'email est requis")
    @Email(message = "L'email doit être valide")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Le mot de passe est requis")
    @Size(min = 6, message = "Le mot de passe doit comporter au moins 6 caractères")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "postalCode")
    @NotNull(message = "Le code postal est requis")
    private int postalCode;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private ShoppingCart shoppingCart;

    // Constructeurs
    public User(String userName, String userSurname, String email, String password, String address, String city, int postalCode, ShoppingCart shoppingCart) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;  // Initialisation du code postal
        this.shoppingCart = shoppingCart;
    }

    public User() {}

    public User(String userName, String userSurname, String email, String password, String address, String city, int postalCode) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;  // Initialisation du code postal
    }

    // Getters et Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;  // Getter pour le code postal
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;  // Setter pour le code postal
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
