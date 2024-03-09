package com.br.greenbank.domain.transaction;

import com.br.greenbank.domain.merchant.Merchant;
import com.br.greenbank.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction")
public class Transaction implements ITransaction {
    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

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
    public void transfer(BigDecimal value, User payer, User payee) {
        if (payer.getBankAccount().getBalance().compareTo(value) < 0) {
            logger.error("Insufficient funds");
            throw new IllegalArgumentException("Insufficient funds");
        }

        try {
            payer.getBankAccount().withdraw(value);
            payee.getBankAccount().deposit(value);
        } catch (Exception e) {
            logger.error("Error while transferring money:", e);
            throw new IllegalArgumentException("Error while transferring money:", e);
        }

    }

    @Override
    public void transfer(BigDecimal value, User payer, Merchant payee) {

        if (payer.getBankAccount().getBalance().compareTo(value) < 0) {
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
