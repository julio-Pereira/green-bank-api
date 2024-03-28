package com.br.greenbank.controller.account;

import com.br.greenbank.model.account.Account;
import com.br.greenbank.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.br.greenbank.constants.PathConstants.ACCOUNT_BASE_ROUTE;

@RestController
@RequestMapping(ACCOUNT_BASE_ROUTE)
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        var accounts = service.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }



}
