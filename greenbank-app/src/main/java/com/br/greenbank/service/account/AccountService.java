package com.br.greenbank.service.account;

import com.br.greenbank.domain.account.Account;
import com.br.greenbank.dto.account.UserAccountDto;
import com.br.greenbank.model.account.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.br.greenbank.constants.ErrorHandlerConstants.ERROR_ON_CREATING_ACCOUNT;

@Service
public class AccountService {

    private final AccountRepository repository;
    private AccountHandler accountImpl = new AccountHandler();

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Account createNewAccount(UserAccountDto userAccountDto) {
        var account = accountImpl.create(userAccountDto);

        try {
            return repository.save(account);
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_ON_CREATING_ACCOUNT, e);
        }
    }

    public List<Account> getAllAccounts() { return repository.findAll(); }

}
