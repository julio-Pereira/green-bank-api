package com.br.greenbank.controller.user;

import com.br.greenbank.domain.user.User;
import com.br.greenbank.dto.user.CreateUserDto;
import com.br.greenbank.dto.user.UpdateUserDto;
import com.br.greenbank.dto.user.UserEmailDto;
import com.br.greenbank.dto.user.UserNameDto;
import com.br.greenbank.dto.user.UserPasswordDto;
import com.br.greenbank.exceptions.UserExistsException;
import com.br.greenbank.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.br.greenbank.constants.PathConstants.USER_BASE_ROUTE;


@RestController
@RequestMapping(USER_BASE_ROUTE)
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") String id) {
        var user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUserDto userDto) throws UserExistsException {
        var user = service.createNewUser(userDto);
        return ResponseEntity.created(URI.create(String.format(USER_BASE_ROUTE, user.toString()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UpdateUserDto userDto) {
        service.updateUser(id, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        service.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/username")
    public ResponseEntity<Void> updateUserName(@PathVariable("id") String id, @RequestBody UserNameDto nameDto) {
        service.updateUserName(id, nameDto);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping(value = "/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") String id, @RequestBody UserPasswordDto passwordDto) {
        service.updatePassword(id, passwordDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/email")
    public ResponseEntity<Void> updateEmail(@PathVariable("id") String id, @RequestBody UserEmailDto emailDto) {
        service.updateEmail(id, emailDto);
        return ResponseEntity.noContent().build();
    }
}
