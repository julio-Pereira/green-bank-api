package com.br.greenbank.dto;

public record CreateUserDto(String fullName, String email, String password, String nationalId) {
}
