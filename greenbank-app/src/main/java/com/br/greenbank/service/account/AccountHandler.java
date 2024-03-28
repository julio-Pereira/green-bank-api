package com.br.greenbank.service.account;

import com.br.greenbank.model.account.Account;
import com.br.greenbank.dto.account.UserAccountDto;
import com.br.greenbank.model.account.IAccount;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class AccountHandler implements IAccount {

    public AccountHandler() {}

    @Override
    public Account create(UserAccountDto userAccountDto) {
       return new Account(
                UUID.randomUUID(),
                generateAccountNumber(),
                BigDecimal.ZERO,
                userAccountDto.userId()
        );
    }

    public int generateAccountNumber() {
        Random random = new Random();
       return 1000000 + random.nextInt(99999999);
    }
}
