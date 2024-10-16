package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.entity.UserEntity;
import com.utn.CapitalConnection.exception.UserNonExistingException;
import com.utn.CapitalConnection.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public abstract class UserController<T extends UserEntity> {

    protected final UserService<T> userService;

    @Autowired
    public UserController(UserService<T> userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<T> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getUserById(
            @Parameter(description = "Unique user identifier", example = "1") @PathVariable Long id) {
        try {
            T user = userService.findUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNonExistingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<T> createUser(@Valid @RequestBody T user) {
        try {
            T savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "Unique user identifier", example = "1") @PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserNonExistingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
