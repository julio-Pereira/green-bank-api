package com.br.greenbank.dto.user;

public record CreateUserDto(String fullName, String email, String password, String nationalId) {
}
