package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.UserEntity;
import com.utn.CapitalConnection.exception.UserNonExistingException;
import com.utn.CapitalConnection.repository.UserRepository;
import com.utn.CapitalConnection.types.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format")
    })
    public T findUserById(
            @Parameter(description = "Unique identifier of the user", example = "1") Long id) throws UserNonExistingException {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new UserNonExistingException(id));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The provided ID must be valid and not null", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while retrieving user by ID", e);
        }
    }


    @Operation(summary = "Save a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User saved successfully")
    })
    public T saveUser(T user) {
        return userRepository.save(user);
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public void deleteUser(Long id) throws UserNonExistingException {
        if (!userRepository.existsById(id)) {
            throw new UserNonExistingException(id);
        }
        userRepository.deleteById(id);
    }


    @Operation(summary = "Find users by name or experience range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found matching the criteria")
    })
    public List<T> findByNameOrExperience(String name, Integer minExperience, Integer maxExperience) {
        return userRepository.findByNameOrExperience(name, minExperience, maxExperience);
    }

    @Operation(summary = "Find user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public Optional<T> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Operation(summary = "Find users by name or surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found matching the criteria")
    })
    public List<T> findByNameOrSurname(String name, String surname) {
        return userRepository.findByNameOrSurname(name, surname);
    }

    @Operation(summary = "Find users by category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found matching the category")
    })
    public List<T> findByCategory(Category category) {
        return userRepository.findByCategory(category);
    }

    @Operation(summary = "Find users by years of experience range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found matching the experience range")
    })
    public List<T> findByYearsOfExperienceBetween(Integer minExperience, Integer maxExperience) {
        return userRepository.findByYearsOfExperienceBetween(minExperience, maxExperience);
    }

    @Operation(summary = "Find users by registration date after")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found matching the registration date criteria")
    })
    public List<T> findByRegistrationDateAfter(LocalDateTime registrationDate) {
        return userRepository.findByRegistrationDateAfter(registrationDate);
    }

    @Operation(summary = "Find users by wallet range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found within the wallet range")
    })
    public List<T> findByWalletBetween(BigDecimal minWallet, BigDecimal maxWallet) {
        return userRepository.findByWalletBetween(minWallet, maxWallet);
    }

    @Operation(summary = "Count users by category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total count of users in the specified category")
    })
    public long countByCategory(Category category) {
        return userRepository.countByCategory(category);
    }

    @Operation(summary = "Find users by name, surname, and experience")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found matching the criteria")
    })
    public List<T> findByNameSurnameAndExperience(String name, String surname, Integer minExperience, Integer maxExperience) {
        return userRepository.findByNameSurnameAndExperience(name, surname, minExperience, maxExperience);
    }

    @Operation(summary = "Find users born after a specific date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found born after the specified date")
    })
    public List<T> findByDateOfBirthAfter(LocalDate dateOfBirth) {
        return userRepository.findByDateOfBirthAfter(dateOfBirth);
    }

    @Operation(summary = "Find users born before a specific date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found born before the specified date")
    })
    public List<T> findByDateOfBirthBefore(LocalDate dateOfBirth) {
        return userRepository.findByDateOfBirthBefore(dateOfBirth);
    }
}
