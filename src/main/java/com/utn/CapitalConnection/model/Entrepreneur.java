package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.types.Category;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Entrepreneur extends User{

    private float successRate;
    private String cbu;
    private List<Entrepreneurship> entrepreneurshipList;

    public Entrepreneur() {

    }

    public Entrepreneur(String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, String adress, float successRate, String cbu ) {
        super(name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, adress);
        this.successRate = successRate;
        this.cbu = cbu;
    }

    public Entrepreneur(Long id, String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, String adress, float successRate, String cbu ) {
        super(id, name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, adress);
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

    public void addEntrepreneurship(Entrepreneurship entrepreneurship){
        this.entrepreneurshipList.add(entrepreneurship);
    }
}
