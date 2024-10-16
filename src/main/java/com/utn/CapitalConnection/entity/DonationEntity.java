package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "donations")
public class DonationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime date;


    // Asociación con Entrepreneurship
    @ManyToOne
    @JoinColumn(name = "entrepreneurship_id", nullable = false)
    private EntrepreneurshipEntity entrepreneurship;

    // Asociación con InvestorEntity
    @ManyToOne
    @JoinColumn(name = "investor_id", nullable = false)
    private InvestorEntity investor;


    public DonationEntity() {
    }

    public DonationEntity(Long id, BigDecimal amount, LocalDateTime date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setEntrepreneurship(EntrepreneurshipEntity entrepreneurship) {
        this.entrepreneurship = entrepreneurship;
    }

    public void setInvestor(InvestorEntity investor) {
        this.investor = investor;
    }
}
