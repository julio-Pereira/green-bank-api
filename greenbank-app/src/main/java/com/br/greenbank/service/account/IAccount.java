package com.br.greenbank.service.account;

import com.br.greenbank.domain.account.Account;
import com.br.greenbank.dto.account.UserAccountDto;

public interface IAccount {
    Account create(UserAccountDto userAccountDto);
}
