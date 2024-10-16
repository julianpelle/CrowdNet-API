package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.types.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Entrepreneur extends User {

    @Positive
    private float successRate;

    @NotBlank
    private String cbu;

    private List<Entrepreneurship> entrepreneurshipList;

    public Entrepreneur() {
    }

    public Entrepreneur(String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, Address address, float successRate, String cbu) {
        super(name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, address);
        this.successRate = successRate;
        this.cbu = cbu;
    }

    public Entrepreneur(Long id, String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, Address address, float successRate, String cbu) {
        super(id, name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, address);
        this.successRate = successRate;
        this.cbu = cbu;
    }

    public float getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(float successRate) {
        this.successRate = successRate;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public List<Entrepreneurship> getEntrepreneurshipList() {
        return entrepreneurshipList;
    }

    public void setEntrepreneurshipList(List<Entrepreneurship> entrepreneurshipList) {
        this.entrepreneurshipList = entrepreneurshipList;
    }

    public void addEntrepreneurship(Entrepreneurship entrepreneurship) {
        this.entrepreneurshipList.add(entrepreneurship);
    }
}
