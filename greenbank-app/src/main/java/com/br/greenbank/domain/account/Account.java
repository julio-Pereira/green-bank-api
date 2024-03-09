package com.br.greenbank.domain.account;

import com.br.greenbank.domain.merchant.Merchant;
import com.br.greenbank.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_bank_account")
public class Account implements IAccount, Serializable {

        private static final long serialVersionUID = 1L;
        private static final Logger logger = LoggerFactory.getLogger(Account.class);

        @Id
        @UuidGenerator(style = UuidGenerator.Style.TIME)
        private UUID id;

        @Column(unique = true, name = "bank_account_number")
        private int accountNumber;
        @Column(name = "bank_account_balance")
        private BigDecimal balance;

        @JsonIgnore
        @OneToOne
        @MapsId
        private User user;

        @JsonIgnore
        @OneToOne
        @MapsId
        private Merchant merchant;


        public Account(UUID accountId, int accountNumber, BigDecimal balance, User user) {
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.user = user;
        }

        public Account(UUID accountId, int accountNumber, BigDecimal balance, Merchant merchant) {
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.merchant = merchant;
        }

        public Account() {}

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public int getAccountNumber() {
                return accountNumber;
        }

        public void setAccountNumber(int accountNumber) {
                this.accountNumber = accountNumber;
        }

        public BigDecimal getBalance() {
                return balance(balance);
        }

        public void setBalance(BigDecimal balance) {
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
        public void deposit(BigDecimal value) {
            this.balance = this.balance.add(value);
        }

        @Override
        public void withdraw(BigDecimal value) {
                if (this.balance.compareTo(value) < 0) {
                        logger.error("Insufficient funds");
                        throw new IllegalArgumentException("Insufficient funds");
                }
            this.balance = this.balance.subtract(value);
        }

        @Override
        public BigDecimal balance(BigDecimal balance) {
                if (balance.compareTo(BigDecimal.ZERO) < 0)
                        throw new IllegalArgumentException("Insufficient funds");

                logger.info(String.format("Balance: $1%s", balance));
                return balance;
        }


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Account that = (Account) o;
                return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }
}
