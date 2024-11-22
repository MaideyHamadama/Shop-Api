package com.shop.backend.entity;

import jakarta.persistence.*;

/**
 * Entité représentant une livraison associée à une commande dans le système.
 * Cette classe est utilisée pour gérer les détails de la livraison.
 */
@Entity
@Table(name = "Delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deliveryID")
    private int deliveryID;

    @Column(name = "deliveryAddress", nullable = false)
    private String deliveryAddress;

    @Column(name = "deliveryType", nullable = false)
    private String deliveryType;

    @Column(name = "deliveryStatus", nullable = false)
    private String deliveryStatus;

    @OneToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Order order;  // Relation avec la classe Order (clé étrangère)

    // ==========================
    //      Constructeurs
    // ==========================
    public Delivery() {
        this.deliveryStatus = "Pending";  // Statut par défaut
    }

    public Delivery(String deliveryAddress, String deliveryType, Order order) {
        this.deliveryAddress = deliveryAddress;
        this.deliveryType = deliveryType;
        this.deliveryStatus = "Pending";
        this.order = order;
    }
    // ==========================
    //        Getters & Setters
    // ==========================
    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
