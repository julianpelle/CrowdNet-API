package com.utn.CapitalConnection.model;

import com.utn.CapitalConnection.types.Category;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Investor extends User{

    @NotNull(message = "Portfolio value cannot be null")
    private BigDecimal PortfolioValue;


    public Investor() {
    }

    public Investor(String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, Address address, BigDecimal portfolioValue) {
        super(name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, address);
        PortfolioValue = portfolioValue;
    }

    public Investor(Long id, String name, String surname, String email, Date dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category industry, Address address, BigDecimal portfolioValue) {
        super(id, name, surname, email, dateOfBirth, wallet, yearsOfExperience, industry, address);
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
        Investor that = (Investor) o;
        return Objects.equals(PortfolioValue, that.PortfolioValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), PortfolioValue);
    }

    @Override
    public String toString() {
        return "Investor{" +
                "PortfolioValue=" + PortfolioValue +
                '}';
    }
}
