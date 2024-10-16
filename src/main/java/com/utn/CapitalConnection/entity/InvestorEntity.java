package com.utn.CapitalConnection.entity;

import com.utn.CapitalConnection.types.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "investor")
public class InvestorEntity extends UserEntity {

    @NotNull(message = "Portfolio value is required.")
    @Column(nullable = false)
    private BigDecimal portfolioValue;

    @ManyToMany(mappedBy = "investor", fetch = FetchType.LAZY)
    private List<NotificationEntity> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "investor")
    private List<DonationEntity> donations;

    public InvestorEntity() {
    }

    public InvestorEntity(@NotBlank String name, @NotBlank String surname, @NotBlank String email,
                          @NotNull LocalDate dateOfBirth, @NotNull BigDecimal wallet,
                          int yearsOfExperience, @NotNull Category category,
                          @NotNull AddressEntity address, @NotNull BigDecimal portfolioValue,
                          List<NotificationEntity> notifications) {
        super(name, surname, email, dateOfBirth, wallet, yearsOfExperience, category, address);
        this.portfolioValue = portfolioValue;
        this.notifications = notifications;
    }

    public BigDecimal getPortfolioValue() {
        return portfolioValue;
    }

    public List<NotificationEntity> getNotifications() {
        return notifications;
    }

    public void addNotification(NotificationEntity notification) {
        notifications.add(notification);
        notification.getInvestors().add(this);
    }

    public void removeNotification(NotificationEntity notification) {
        notifications.remove(notification);
        notification.getInvestors().remove(this);
    }

    public void setPortfolioValue(BigDecimal portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public void setNotifications(List<NotificationEntity> notifications) {
        this.notifications = notifications;
    }

    public void setDonations(List<DonationEntity> donations) {
        this.donations = donations;
    }
}
