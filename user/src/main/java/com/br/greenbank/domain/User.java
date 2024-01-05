package com.br.greenbank.domain;


import com.br.greenbank.enums.CustomerType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID userId;

    private String fullName;
    @Column(unique = true)
    private String email;
    private String password;

    private CustomerType customerType;

    @Column(unique = true)
    private String nationalId;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant modifiedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "payer")
    private List<Transaction> outgoingTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "payee")
    private List<Transaction> incomingTransactions = new ArrayList<>();

    public User(UUID userId, String fullName, String email, String password, String nationalId, Instant createdAt, Instant modifiedAt, BankAccount bankAccount, List<Transaction> outgoingTransactions, List<Transaction> incomingTransactions) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.nationalId = nationalId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.customerType = CustomerType.USER;
        this.bankAccount = bankAccount;
        this.outgoingTransactions = outgoingTransactions;
        this.incomingTransactions = incomingTransactions;
    }

    public User() {
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public void setOutgoingTransactions(List<Transaction> outgoingTransactions) {
        this.outgoingTransactions = outgoingTransactions;
    }

    public List<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<Transaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
