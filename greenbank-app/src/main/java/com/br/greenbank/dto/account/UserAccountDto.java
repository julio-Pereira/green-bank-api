package com.br.greenbank.dto.account;

import com.br.greenbank.model.user.User;

public record UserAccountDto(int accountNumber, String nationalId, User userId) {}
