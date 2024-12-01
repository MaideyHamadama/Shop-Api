package com.shop.backend.entity;

import jakarta.persistence.*;

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
    private String userName;

    @Column(name = "userSurname", nullable = false)
    private String userSurname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private ShoppingCart shoppingCart;

    // ===========================
    //        Constructeurs
    // ===========================

    /**
     * Constructeur par défaut.
     */
    public User() {
    }

    /**
     * Constructeur complet pour créer une instance de User avec tous ses attributs.
     *
     * @param userName    Le prénom de l'utilisateur.
     * @param userSurname Le nom de l'utilisateur.
     * @param email       L'email de l'utilisateur.
     * @param password    Le mot de passe de l'utilisateur.
     * @param address     L'adresse de l'utilisateur.
     * @param city        La ville de l'utilisateur.
     */
    public User(String userName, String userSurname, String email, String password, String address, String city) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
    }

    // ===========================
    //    Getters et Setters
    // ===========================

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
}