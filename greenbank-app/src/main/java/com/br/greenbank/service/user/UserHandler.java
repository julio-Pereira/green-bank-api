package com.br.greenbank.service.user;

import com.br.greenbank.model.user.User;
import com.br.greenbank.dto.user.CreateUserDto;
import com.br.greenbank.dto.user.UpdateUserDto;
import com.br.greenbank.dto.user.UserEmailDto;
import com.br.greenbank.dto.user.UserNameDto;
import com.br.greenbank.dto.user.UserPasswordDto;

import java.time.Instant;
import java.util.UUID;

public class UserHandler implements IUser {

    public UserHandler() {}

    @Override
    public User create(CreateUserDto userDto) {
        return new User(
                UUID.randomUUID(),
                userDto.fullName(),
                userDto.email(),
                userDto.password(),
                userDto.nationalId(),
                Instant.now(),
                Instant.now(),
                null,
                null,
                null);
    }

    @Override
    public void update(User user, UpdateUserDto userDto) {
        if (userDto.fullName() != null) {
            user.setFullName(userDto.fullName());
        }

        if (userDto.email() != null) {
            user.setEmail(userDto.email());
        }

        if (userDto.password() != null) {
            user.setPassword(userDto.password());
        }

    }

    @Override
    public void updatePassword(User user, UserPasswordDto userDto) {
        if (userDto.password() != null && userDto.newPassword() != null) {
            user.setPassword(userDto.newPassword());
        }
    }

    @Override
    public void updateEmail(User user, UserEmailDto userDto) {
        if (userDto.email() != null) {
            user.setEmail(userDto.email());
        }
    }

    @Override
    public void updateFullName(User user, UserNameDto userDto) {
        if (userDto.fullName() != null) {
            user.setFullName(userDto.fullName());
        }
    }
}
