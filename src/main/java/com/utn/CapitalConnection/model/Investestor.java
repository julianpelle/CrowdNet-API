package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.types.Category;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Investestor extends User{

    private BigDecimal PortfolioValue;


    public Investestor() {
    }

    public Investestor(String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, String adress, BigDecimal portfolioValue) {
        super(name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, adress);
        PortfolioValue = portfolioValue;
    }

    public Investestor(Long id, String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, String adress, BigDecimal portfolioValue) {
        super(id, name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, adress);
        PortfolioValue = portfolioValue;
    }

    public BigDecimal getPortfolioValue() {
        return PortfolioValue;
    }

    public void setPortfolioValue(BigDecimal portfolioValue) {
        PortfolioValue = portfolioValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Investestor that = (Investestor) o;
        return Objects.equals(PortfolioValue, that.PortfolioValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), PortfolioValue);
    }

    @Override
    public String toString() {
        return "Investestor{" +
                "PortfolioValue=" + PortfolioValue +
                '}';
    }
}
