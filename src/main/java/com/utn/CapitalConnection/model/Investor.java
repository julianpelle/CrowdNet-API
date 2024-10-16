package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.types.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Investor extends User{

    @Min(value = 0, message = "The portfolio value must be a positive number or zero")
    @NotNull(message = "Portfolio value cannot be null")
    private BigDecimal portfolioValue;


    public Investor() {
    }

    public Investor(String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, Address address, BigDecimal portfolioValueAT) {
        super(name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, address);
        portfolioValue = portfolioValue;
    }

    public Investor(Long id, String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, Address address, BigDecimal portfolioValueAT) {
        super(id, name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, address);
        portfolioValue = portfolioValueAT;
    }

    public BigDecimal getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(BigDecimal portfolioValueAT) {
        portfolioValue = portfolioValueAT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Investor that = (Investor) o;
        return Objects.equals(portfolioValue, that.portfolioValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), portfolioValue);
    }

    @Override
    public String toString() {
        return "Investor{" +
                "PortfolioValue=" + portfolioValue +
                '}';
    }
}
