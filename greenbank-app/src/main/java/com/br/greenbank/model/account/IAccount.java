package com.br.greenbank.model.account;

import com.br.greenbank.model.account.Account;
import com.br.greenbank.dto.account.UserAccountDto;

public interface IAccount {
    Account create(UserAccountDto userAccountDto);
}
