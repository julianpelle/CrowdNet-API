package com.utn.CapitalConnection.entity;

import com.utn.CapitalConnection.types.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entrepreneurs")
public class EntrepreneurEntity extends UserEntity {

    @Positive(message = "The number wanna be positive")
    @Column(nullable = false)
    private float successRate;

    @NotBlank(message = "The CBU cant be empty")
    @Column(nullable = false)
    private String cbu;

    @ManyToMany(mappedBy = "entrepreneurs", fetch = FetchType.LAZY)
    private List<NotificationEntity> notifications = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "entrepreneur_entrepreneurship",
            joinColumns = @JoinColumn(name = "entrepreneur_id"),
            inverseJoinColumns = @JoinColumn(name = "entrepreneurship_id")
    )
    private List<EntrepreneurshipEntity> entrepreneurships = new ArrayList<>();


    public List<EntrepreneurshipEntity> getEntrepreneurships() {
        return entrepreneurships;
    }

    public void setEntrepreneurships(List<EntrepreneurshipEntity> entrepreneurships) {
        this.entrepreneurships = entrepreneurships;
    }

    public EntrepreneurEntity() {
    }

    public EntrepreneurEntity(String name, String surname, String email, LocalDate dateOfBirth, BigDecimal wallet, int yearsOfExperience, Category category, AddressEntity address, float successRate, String cbu, List<NotificationEntity> notifications) {
        super(name, surname, email, dateOfBirth, wallet, yearsOfExperience, category, address);
        this.successRate = successRate;
        this.cbu = cbu;
        this.notifications = notifications;
    }

    public float getSuccessRate() {
        return successRate;
    }

    public String getCbu() {
        return cbu;
    }

    public List<NotificationEntity> getNotifications() {
        return notifications;
    }

    public void addNotification(NotificationEntity notification) {
        notifications.add(notification);
        notification.getEntrepreneurs().add(this);
    }

    public void removeNotification(NotificationEntity notification) {
        notifications.remove(notification);
        notification.getEntrepreneurs().remove(this);
    }

    public void setSuccessRate(float successRate) {
        this.successRate = successRate;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setNotifications(List<NotificationEntity> notifications) {
        this.notifications = notifications;
    }
}
