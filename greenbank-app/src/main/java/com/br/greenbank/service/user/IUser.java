package com.br.greenbank.service.user;

import com.br.greenbank.model.user.User;
import com.br.greenbank.dto.user.CreateUserDto;
import com.br.greenbank.dto.user.UpdateUserDto;
import com.br.greenbank.dto.user.UserEmailDto;
import com.br.greenbank.dto.user.UserNameDto;
import com.br.greenbank.dto.user.UserPasswordDto;

public interface IUser {
    User create(CreateUserDto userDto);

    void update(User user, UpdateUserDto userDto);

    void updatePassword(User user, UserPasswordDto userDto);

    void updateEmail(User user, UserEmailDto userDto);

    void updateFullName(User user, UserNameDto userDto);

}
