package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Message cannot be blank")
    @Column
    private String message;

    @NotNull(message = "Shipping date is required")
    @Column(name = "shipping_date")
    private LocalDateTime shippingDate;

    @ManyToMany
    @JoinTable(
            name = "investor_notifications",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "investor_id"))
    private List<InvestorEntity> investor;

    @ManyToMany
    @JoinTable(
            name = "entrepreneurs_notifications",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "entrepreneur_id"))
    private List<EntrepreneurEntity> entrepreneurs;

    public NotificationEntity() {}

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        try {
            if (message == null || message.trim().isEmpty()) {
                throw new IllegalArgumentException("Message cannot be null or blank");
            }
            this.message = message;
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting message: " + e.getMessage());
        }
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        try {
            if (shippingDate == null) {
                throw new IllegalArgumentException("Shipping date is required");
            }
            this.shippingDate = shippingDate;
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting shipping date: " + e.getMessage());
        }
    }

    public List<InvestorEntity> getInvestors() {
        return investor;
    }

    public List<EntrepreneurEntity> getEntrepreneurs() {
        return entrepreneurs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvestor(List<InvestorEntity> investor) {
        this.investor = investor;
    }

    public void setEntrepreneurs(List<EntrepreneurEntity> entrepreneurs) {
        this.entrepreneurs = entrepreneurs;
    }
}
