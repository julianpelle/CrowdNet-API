package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.types.Category;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public abstract class User{

    protected Long id;

    protected String name;

    protected String surname;

    protected String email;

    protected Date dateOfBirth;

    protected BigDecimal wallet;

    protected int yearsOfExperience;

    protected Category industry;

    protected String adress;

    public User() {
    }

    public User(String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, String adress) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.wallet = wallet;
        this.yearsOfExperience = yearsOfExperience;
        this.industry = industry;
        this.adress = adress;
    }

    public User(Long id, String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, String adress) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.wallet = wallet;
        this.yearsOfExperience = yearsOfExperience;
        this.industry = industry;
        this.adress = adress;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return yearsOfExperience == user.yearsOfExperience && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(wallet, user.wallet) && industry == user.industry && Objects.equals(adress, user.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, adress);
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
                ", adress='" + adress + '\'' +
                '}';
    }


}
