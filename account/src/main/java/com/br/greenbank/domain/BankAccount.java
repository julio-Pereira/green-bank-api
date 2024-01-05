package com.br.greenbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_bank_account")
public class BankAccount implements IAccount, Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @UuidGenerator(style = UuidGenerator.Style.TIME)
        private UUID id;

        @Column(unique = true, name = "bank_account_number")
        private Long accountNumber;
        @Column(name = "bank_account_balance")
        private double balance;

        @JsonIgnore
        @OneToOne
        @MapsId
        private User user;

        @JsonIgnore
        @OneToOne
        @MapsId
        private Merchant merchant;


        public BankAccount(Long accountNumber, double balance, User user) {
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.user = user;
        }

        public BankAccount(Long accountNumber, double balance, Merchant merchant) {
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.merchant = merchant;
        }

        public BankAccount() {}

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public Long getAccountNumber() {
                return accountNumber;
        }

        public void setAccountNumber(Long accountNumber) {
                this.accountNumber = accountNumber;
        }

        public double getBalance() {
                return balance(balance);
        }

        public void setBalance(double balance) {
                this.balance = balance;
        }

        public User getUser() {
                return user;
        }

        public void setCustomer(User user) {
                this.user = user;
        }

        public Merchant getMerchant() {
                return merchant;
        }

        public void setMerchant(Merchant merchant) {
                this.merchant = merchant;
        }

        @Override
        public void deposit(double value) {
            this.balance += value;
        }

        @Override
        public void withdraw(double value) {
            this.balance -= value;
        }

        @Override
        public double balance(double balance) {
                if (balance < 0) throw new IllegalArgumentException("Balance must not be negative");
                System.out.println("Balance: " + balance);
                return balance;
        }


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                BankAccount that = (BankAccount) o;
                return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }
}
