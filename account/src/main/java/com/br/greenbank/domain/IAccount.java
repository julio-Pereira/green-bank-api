package com.br.greenbank.domain;

public interface IAccount {
    void deposit(double value);
    void withdraw(double value);
    double balance(double balance);

}
