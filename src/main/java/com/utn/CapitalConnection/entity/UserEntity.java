package com.utn.CapitalConnection.entity;

import com.utn.CapitalConnection.types.Category;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(max = 50, message = "Surname must not exceed 50 characters")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "Date of birth is required")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotNull(message = "Wallet balance is required")
    @Positive(message = "Wallet balance must be positive")
    @Column(nullable = false)
    private BigDecimal wallet;

    @Min(value = 0, message = "Years of experience cannot be negative")
    @Column(nullable = false)
    private int yearsOfExperience;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @NotNull(message = "Address is required")
    @Valid
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id", unique = true)
    private AddressEntity address;


    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registrationDate;

    // Constructor
    public UserEntity(String name, String surname, String email, LocalDate dateOfBirth,
                      BigDecimal wallet, int yearsOfExperience, Category category,
                      AddressEntity address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.wallet = wallet;
        this.yearsOfExperience = yearsOfExperience;
        this.category = category;
        this.address = address;
    }
        // Constructor
    public UserEntity() {
    }


    // Getters
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public Category getCategory() {
        return category;
    }

    public AddressEntity getAddress() {
        return address;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }


}
