package com.br.greenbank.dto.account;

import com.br.greenbank.domain.user.User;

import java.math.BigDecimal;

public record UserAccountDto(int accountNumber, String nationalId, User userId) {}
