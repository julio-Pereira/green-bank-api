package com.br.greenbank.domain.transaction;

import com.br.greenbank.model.merchant.Merchant;
import com.br.greenbank.model.user.User;

import java.math.BigDecimal;

public interface ITransaction {
    void transfer(BigDecimal value, User payer, User payee);

    void transfer(BigDecimal value, User payer, Merchant payee);

}
