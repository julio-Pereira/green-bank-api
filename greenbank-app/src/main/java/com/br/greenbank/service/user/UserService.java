package com.br.greenbank.service.user;

import com.br.greenbank.domain.user.User;
import com.br.greenbank.dto.user.CreateUserDto;
import com.br.greenbank.dto.user.UpdateUserDto;
import com.br.greenbank.dto.user.UserEmailDto;
import com.br.greenbank.dto.user.UserNameDto;
import com.br.greenbank.dto.user.UserPasswordDto;
import com.br.greenbank.exceptions.UserExistsException;
import com.br.greenbank.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.br.greenbank.constants.ErrorHandlerConstants.EMAIL_ALREADY_EXISTS;
import static com.br.greenbank.constants.ErrorHandlerConstants.EMPTY_EMAIL;
import static com.br.greenbank.constants.ErrorHandlerConstants.EMPTY_NAME;
import static com.br.greenbank.constants.ErrorHandlerConstants.EMPTY_NEW_PASSWORD;
import static com.br.greenbank.constants.ErrorHandlerConstants.EMPTY_PASSWORD;
import static com.br.greenbank.constants.ErrorHandlerConstants.USER_ALREADY_EXISTS;
import static com.br.greenbank.constants.ErrorHandlerConstants.USER_NOT_FOUND;

@Service
public class UserService {
    private final UserRepository repository;
    private UserHandler userImpl = new UserHandler();

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    @Transactional
    public User createNewUser(CreateUserDto userDto) throws UserExistsException {
        var user = userImpl.create(userDto);

        if (repository.existsByEmail(user.getEmail())) {
            throw new UserExistsException(USER_ALREADY_EXISTS);
        }

        try {
            return repository.save(user);
        } catch (Exception e) {
           throw new IllegalArgumentException(USER_ALREADY_EXISTS, e);
        }
    }

    @Transactional
    public void updateUser(String userId, UpdateUserDto userDto) {
        var id = UUID.fromString(userId);
        var findUser = repository.findById(id);

        if (findUser.isEmpty()) throw new IllegalArgumentException(USER_NOT_FOUND);

        if (userDto.fullName().isEmpty()) throw new IllegalArgumentException(EMPTY_NAME);

        if (userDto.email().isEmpty()) throw new IllegalArgumentException(EMPTY_EMAIL);

        if (userDto.password().isEmpty()) throw new IllegalArgumentException(EMPTY_PASSWORD);

        var user = findUser.get();

        userImpl.update(user, userDto);

        repository.updateUser(user.getUserId(), user.getFullName(), user.getEmail(), user.getPassword());
    }

    @Transactional
    public void updatePassword(String userId, UserPasswordDto passwordDto) {
      var id = UUID.fromString(userId);
      var findUser = repository.findById(id);

      if (findUser.isEmpty()) throw new IllegalArgumentException(USER_NOT_FOUND);

      if (passwordDto.password().isEmpty()) throw new IllegalArgumentException(EMPTY_PASSWORD);

      if (passwordDto.newPassword().isEmpty()) throw new IllegalArgumentException(EMPTY_NEW_PASSWORD);

      var user = findUser.get();

      userImpl.updatePassword(user, passwordDto);

      repository.updateUserPassword(user.getUserId(), user.getPassword());
    }

    @Transactional
    public void updateEmail(String userId, UserEmailDto emailDto) {
      var id = UUID.fromString(userId);
      var findUser = repository.findById(id);

      if (findUser.isEmpty()) {
          throw new IllegalArgumentException(USER_NOT_FOUND);
      }

      if (emailDto.email().isEmpty()) {
          throw new IllegalArgumentException(EMPTY_EMAIL);
      }

      var user = findUser.get();

      userImpl.updateEmail(user, emailDto);

      repository.updateUserEmail(user.getUserId(), user.getEmail());
    }

    @Transactional
    public void updateUserName(String userId, UserNameDto nameDto) {
      var id = UUID.fromString(userId);
      var findUser = repository.findById(id);

      if (findUser.isEmpty()) {
          throw new IllegalArgumentException(USER_NOT_FOUND);
      }

        if (nameDto.fullName().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME);
        }

      var user = findUser.get();

      userImpl.updateFullName(user, nameDto);

      repository.updateUserName(user.getUserId(), user.getFullName());
    }

    @Transactional
    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var findUser = repository.findById(id);

        if (findUser.isEmpty()) {
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }

        repository.deleteById(id);
    }
}
