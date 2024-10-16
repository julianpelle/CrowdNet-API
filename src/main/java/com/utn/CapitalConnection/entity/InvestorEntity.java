package com.utn.CapitalConnection.entity;

import com.utn.CapitalConnection.types.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "investor")
public class InvestorEntity extends UserEntity {

    @Column(nullable = false)
    private BigDecimal portfolioValue;

    @ManyToMany(mappedBy = "investor", fetch = FetchType.LAZY)
    private List<NotificationEntity> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "investor")
    private List<DonationEntity> donations;

    public InvestorEntity() {
    }

    public InvestorEntity(String name, String surname, String email, LocalDate dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category category, AddressEntity address, BigDecimal portfolioValue, List<NotificationEntity> notifications) {
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
