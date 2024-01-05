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
import java.util.UUID;

@Entity
@Table(name = "tb_merchant")
public class Merchant {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID merchantId;
    private String companyName;
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

    @OneToOne(mappedBy = "merchant", cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "merchant")
    private List<Transaction> incomingTransactions = new ArrayList<>();


    public Merchant(UUID merchantId, String companyName, String email, String password, String nationalId, Instant createdAt, Instant modifiedAt, BankAccount bankAccount, List<Transaction> incomingTransactions) {
        this.merchantId = merchantId;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.nationalId = nationalId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.customerType = CustomerType.MERCHANT;
        this.bankAccount = bankAccount;
        this.incomingTransactions = incomingTransactions;
    }

    public Merchant() {}

    public UUID getMerchantId() {
        return merchantId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public List<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<Transaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }
}
