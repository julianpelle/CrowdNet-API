package com.utn.CapitalConnection.entity;

import com.utn.CapitalConnection.types.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entrepreneurs")
public class EntrepreneurEntity extends UserEntity {

    @Column(nullable = false)
    private float successRate;  // Corregido a successRate

    @Column(nullable = false)
    private String cbu;

    @ManyToMany(mappedBy = "entrepreneurs", fetch = FetchType.LAZY)
    private List<NotificationEntity> notifications = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "entrepreneur_entrepreneurship", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "entrepreneur_id"), // Columna que hace referencia al emprendedor
            inverseJoinColumns = @JoinColumn(name = "entrepreneurship_id") // Columna que hace referencia al emprendimiento
    )
    private List<EntrepreneurshipEntity> entrepreneurships = new ArrayList<>(); // Cambiar a EntrepreneurshipEntity

    // Constructor, getters y setters

    public List<EntrepreneurshipEntity> getEntrepreneurships() {
        return entrepreneurships;
    }

    public void setEntrepreneurships(List<EntrepreneurshipEntity> entrepreneurships) {
        this.entrepreneurships = entrepreneurships;
    }

    // Métodos para agregar y eliminar emprendimientos
    public void addEntrepreneurship(EntrepreneurshipEntity entrepreneurship) {
        entrepreneurships.add(entrepreneurship);
        entrepreneurship.getEntrepreneurs().add(this);  // Agregar relación inversa
    }

    public void removeEntrepreneurship(EntrepreneurshipEntity entrepreneurship) {
        entrepreneurships.remove(entrepreneurship);
        entrepreneurship.getEntrepreneurs().remove(this);  // Eliminar relación inversa
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
        notification.getEntrepreneurs().add(this);  // Corrección del nombre de la lista
    }

    public void removeNotification(NotificationEntity notification) {
        notifications.remove(notification);
        notification.getEntrepreneurs().remove(this);  // Corrección del nombre de la lista
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
