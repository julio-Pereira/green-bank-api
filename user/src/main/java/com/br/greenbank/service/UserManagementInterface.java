package com.br.greenbank.service;

import com.br.greenbank.domain.User;
import com.br.greenbank.dto.CreateUserDto;
import com.br.greenbank.dto.UpdateUserDto;
import com.br.greenbank.dto.UserEmailDto;
import com.br.greenbank.dto.UserNameDto;
import com.br.greenbank.dto.UserPasswordDto;

public interface UserManagementInterface {
    User create(CreateUserDto userDto);

    void update(User user, UpdateUserDto userDto);

    void updatePassword(User user, UserPasswordDto userDto);

    void updateEmail(User user, UserEmailDto userDto);

    void updateFullName(User user, UserNameDto userDto);

}
