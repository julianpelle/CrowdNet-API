package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "entrepreneurships")
public class EntrepreneurshipEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true) // Permite que sea null
    private String idUser;


    @NotBlank(message = "Name must not be blank")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Column(length = 1000) // Si has cambiado el tamaño de la columna
    private String description;

    @Positive(message = "Goal must be a positive number")
    @Column(nullable = false)
    private BigDecimal goal;

    @Positive(message = "collected must be a positive number")
    @Column(nullable = false)
    private BigDecimal collected;

    @Column(nullable = false)
    private String category;

    @ElementCollection
    @CollectionTable(name = "entrepreneurship_images", joinColumns = @JoinColumn(name = "entrepreneurship_id"))
    @Column(name = "link_image")
    private List<String> images;

    @ElementCollection
    @CollectionTable(name = "entrepreneurship_videos", joinColumns = @JoinColumn(name = "entrepreneurship_id"))
    @Column(name = "link_video")
    private List<String> videos;

    @Column(nullable = false)
    private Boolean isActivated = true; // Este campo indica si el emprendimiento está activo

    // Getters y setters

    public EntrepreneurshipEntity() {
    }

    public EntrepreneurshipEntity(String name,String idUser, ArrayList<String> images, String description, ArrayList<String> videos, BigDecimal goal, String category, BigDecimal collected, Boolean isActivated) {
        this.name = name;
        this.idUser = idUser;
        this.images = images;
        this.description = description;
        this.videos = videos;
        this.goal = goal;
        this.category = category;
        this.collected = collected;
        this.isActivated = isActivated;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String id_user) {
        this.idUser = id_user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getVideos() {
        return videos;
    }

    public @Positive(message = "collected must be a positive number") BigDecimal getCollected() {
        return collected;
    }

    public void setCollected(@Positive(message = "collected must be a positive number") BigDecimal collected) {
        this.collected = collected;
    }

    public BigDecimal getGoal() {
        return goal;
    }

    public String getCategory() {
        return category;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

}
