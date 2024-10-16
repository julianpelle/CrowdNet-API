package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.UserEntity;
import com.utn.CapitalConnection.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class UserService<T extends UserEntity> {
    protected final UserRepository<T> userRepository;

    @Autowired
    public UserService(UserRepository<T> userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all users retrieved successfully")
    })
    public List<T> findAllUsers() {
        return userRepository.findAll();
    }

    @Operation(summary = "Find user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public Optional<T> findUserById(
            @Parameter(description = "Unique identifier of the user", example = "1") Long id) {
        return userRepository.findById(id);
    }

    @Operation(summary = "Save a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    public T saveUser(
            @Parameter(description = "User entity to be saved") T user) {
        return userRepository.save(user);
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public void deleteUser(
            @Parameter(description = "Unique identifier of the user to be deleted", example = "1") Long id) {
        userRepository.deleteById(id);
    }
}
