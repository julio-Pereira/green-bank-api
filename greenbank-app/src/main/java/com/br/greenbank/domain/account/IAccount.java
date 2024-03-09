package com.br.greenbank.domain.account;

import java.math.BigDecimal;

public interface IAccount {
    void deposit(BigDecimal value);
    void withdraw(BigDecimal value);
    BigDecimal balance(BigDecimal balance);

}
