package com.utn.CapitalConnection.dto;

import com.utn.CapitalConnection.types.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "Common data for all users")
public abstract class UserRequest {

    @Schema(description = "ID of the user", example = "1")
    protected Long id;

    @Schema(description = "Name of the user", example = "John")
    protected String name;

    @Schema(description = "Surname of the user", example = "Doe")
    protected String surname;

    @Schema(description = "Email of the user", example = "john.doe@example.com")
    protected String email;

    @Schema(description = "Date of birth of the user", example = "1990-05-21")
    protected Date dateOfBirth;

    @Schema(description = "Wallet balance of the user", example = "1500.00")
    protected BigDecimal wallet;

    @Schema(description = "Years of experience", example = "10")
    protected int yearsOfExperience;

    @Schema(description = "Industry category", example = "Finance")
    protected Category industry;

    @Schema(description = "Address of the user", example = "123 Main St")
    protected String address;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public Category getIndustry() {
        return industry;
    }

    public String getAddress() {
        return address;
    }
}