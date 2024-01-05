package com.br.greenbank.domain;

public interface ITransaction {
    void transfer(double value, User payer, User payee);

    void transfer(double value, User payer, Merchant payee);

}
