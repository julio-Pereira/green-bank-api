package com.br.greenbank.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "tb_transaction")
public class Transaction implements ITransaction {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    @Column(name = "transaction_value")
    private double value;

    @ManyToOne
    @JoinColumn(name = "user_payer")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "user_payee")
    private User payee;

    @ManyToOne
    @JoinColumn(name = "merchant_payee")
    private Merchant merchant;

    public Transaction(double value, User payer, User payee) {
        this.value = value;
        this.payer = payer;
        this.payee = payee;
    }

    public Transaction(double value, User payer, Merchant payee) {
        this.value = value;
        this.payer = payer;
        this.merchant = payee;
    }

    public Transaction() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public void transfer(double value, User payer, User payee) {
        if (payer.getBankAccount().getBalance() < value) {
            throw new IllegalArgumentException("Insufficient funds");

        }

        try {
            payer.getBankAccount().withdraw(value);
            payee.getBankAccount().deposit(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while transferring money:", e);
        }

    }

    @Override
    public void transfer(double value, User payer, Merchant payee) {

        if (payer.getBankAccount().getBalance() < value) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        try {
            payer.getBankAccount().withdraw(value);
            payee.getBankAccount().deposit(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while transferring money:", e);
        }
    }

}
