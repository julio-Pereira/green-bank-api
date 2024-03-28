package com.br.greenbank.domain.account;

import java.math.BigDecimal;

public class Account implements IAccount {
    private BigDecimal balance;

    public Account() {
        this.balance = BigDecimal.ZERO;
    }

    @Override
    public void deposit(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deposit value must be positive");
        }

        if (value.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Deposit value must be greater than zero");
        }

        balance = balance.add(value);
    }

    @Override
    public void withdraw(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Withdraw value must be positive");
        }

        if (value.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Withdraw value must be greater than zero");
        }

        if (balance.compareTo(value) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }


        balance = balance.subtract(value);
    }

    @Override
    public BigDecimal balance(BigDecimal balance) {
        return this.balance;
    }
}