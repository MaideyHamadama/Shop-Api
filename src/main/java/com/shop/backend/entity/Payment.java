package com.shop.backend.entity;

import jakarta.persistence.*;

/**
 * Entité représentant un paiement dans la base de données.
 * Cette classe est mappée à la table "Payment" et contient des informations liées au paiement,
 * y compris l'ID du paiement, le montant, la méthode de paiement et le statut.
 */
@Entity
@Table(name = "Payment")
public class Payment {

    /**
     * L'ID unique du paiement. C'est la clé primaire de l'entité.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentID")
    private int paymentID;

    /**
     * Le montant du paiement. Cette colonne est obligatoire.
     */
    @Column(name = "paymentAmount", nullable = false)
    private double paymentAmount;

    /**
     * La méthode de paiement (par exemple, carte de crédit, PayPal, etc.). Cette colonne est obligatoire.
     */
    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    /**
     * Le statut du paiement. Par défaut, le statut est "Pending" (en attente).
     */
    @Column(name = "paymentStatus", nullable = false)
    private String paymentStatus;

    @OneToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Order order;  // Relation OneToOne avec la classe Order

    // ==========================
    //      Constructeurs
    // ==========================

    /**
     * Constructeur par défaut requis pour JPA.
     */
    public Payment() {
    }

    /**
     * Constructeur permettant de créer un paiement avec un montant et une méthode de paiement.
     * Le statut est par défaut "Pending".
     *
     * @param paymentAmount Le montant du paiement.
     * @param paymentMethod La méthode de paiement.
     */
    public Payment(double paymentAmount, String paymentMethod, Order order) {
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending";
        this.order=order;
    }

    // ==========================
    //      Getters & Setters
    // ==========================

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // ==========================
    //      Méthodes
    // ==========================

    /**
     * Confirme le paiement, change le statut en "Confirmed".
     */
    public void processPayment() {
        this.paymentStatus = "Confirmed";
    }

    /**
     * Effectue un remboursement si le paiement est confirmé.
     * Le statut est mis à jour en "Refunded" et la méthode retourne true si le remboursement est effectué.
     *
     * @return true si le remboursement a réussi, false sinon.
     */
    public boolean refund() {
        if (this.paymentStatus.equals("Confirmed")) {
            this.paymentStatus = "Refunded";
            return true;
        }
        return false;
    }
}
