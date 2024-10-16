package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.types.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public abstract class User{

    protected Long id;

    @NotBlank(message = "Name is required")
    protected String name;

    @NotBlank(message = "Surname is required")
    protected String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    protected String email;

    @NotNull(message = "Date of birth is required")
    protected Date dateOfBirth;

    @NotNull(message = "Wallet balance is required")
    @Positive(message = "Wallet balance must be positive")
    protected BigDecimal wallet;

    @Min(value = 0, message = "Years of experience cannot be negative")
    protected int yearsOfExperience;

    @NotNull(message = "Industry category is required")
    protected Category industry;

    @NotNull(message = "Address is required")
    @Valid
    protected Address address;

    public User() {
    }

    public User(String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, Address address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.wallet = wallet;
        this.yearsOfExperience = yearsOfExperience;
        this.industry = industry;
        this.address = address;
    }

    public User(Long id, String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, Address address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.wallet = wallet;
        this.yearsOfExperience = yearsOfExperience;
        this.industry = industry;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Category getIndustry() {
        return industry;
    }

    public void setIndustry(Category industry) {
        this.industry = industry;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return yearsOfExperience == user.yearsOfExperience && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(wallet, user.wallet) && industry == user.industry && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", wallet=" + wallet +
                ", yearsOfExperience=" + yearsOfExperience +
                ", industry=" + industry +
                ", adress='" + address + '\'' +
                '}';
    }


}
