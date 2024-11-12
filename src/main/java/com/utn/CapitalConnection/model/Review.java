package com.utn.CapitalConnection.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Review {

    private Long id;
    private String idUser;
    private String username;

    @NotNull(message = "Stars must not be null")
    @Positive(message = "Stars must be positive")
    private float stars;

    @NotBlank(message = "Review text must not be blank")
    private String reviewText;


    private Long entrepreneurshipId;  // Aquí solo necesitas el ID del emprendimiento

    // Constructor de Review
    public Review(Long id, String idUser, float stars, String reviewText, Long entrepreneurshipId, String username) {
        this.id = id;
        this.idUser = idUser;
        this.stars = stars;
        this.reviewText = reviewText;
        this.entrepreneurshipId = entrepreneurshipId;
        this.username = username;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long idReview) {
        this.id = idReview;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Long getEntrepreneurshipId() {
        return entrepreneurshipId;
    }

    public void setEntrepreneurshipId(Long entrepreneurshipId) {
        this.entrepreneurshipId = entrepreneurshipId;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}