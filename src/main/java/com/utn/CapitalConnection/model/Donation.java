package com.utn.CapitalConnection.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Donation {

    private Long id;

    private BigDecimal amount;

    private LocalDateTime date;

    private Long idEntrepreneurship;

    public Donation() {
    }

    public Donation(BigDecimal amount, LocalDateTime date, Long idEntrepreneurship) {
        this.amount = amount;
        this.date = date;
        this.idEntrepreneurship = idEntrepreneurship;
    }

    public Donation(Long id, BigDecimal amount, LocalDateTime date, Long idEntrepreneurship) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.idEntrepreneurship = idEntrepreneurship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getIdEntrepreneurship() {
        return idEntrepreneurship;
    }

    public void setIdEntrepreneurship(Long idEntrepreneurship) {
        this.idEntrepreneurship = idEntrepreneurship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donation donation)) return false;
        return Objects.equals(id, donation.id) && Objects.equals(amount, donation.amount) && Objects.equals(date, donation.date) && Objects.equals(idEntrepreneurship, donation.idEntrepreneurship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, date, idEntrepreneurship);
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", idEntrepreneurship=" + idEntrepreneurship +
                '}';
    }
}
