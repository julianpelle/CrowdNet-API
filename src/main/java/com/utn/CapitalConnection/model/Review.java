package com.utn.CapitalConnection.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Review {

    private Long idReview;
    private String idUser;
    private String username;

    @NotNull(message = "Stars must not be null")
    @Positive(message = "Stars must be positive")
    private float stars;

    @NotBlank(message = "Review text must not be blank")
    private String reviewText;


    private Long idEntrepreneurship;


    // Constructor de DTO Review
    public Review(Long idReview, String idUser, float stars, String reviewText, Long idEntrepreneurship, String username) {
        this.idReview = idReview;
        this.idUser = idUser;
        this.stars = stars;
        this.reviewText = reviewText;
        this.idEntrepreneurship = idEntrepreneurship;
        this.username = username;
    }

    // Getters y setters
    public Long getIdReview() {
        return idReview;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Long getIdEntrepreneurship() {
        return idEntrepreneurship;
    }

    public void setIdEntrepreneurship(Long idEntrepreneurship) {
        this.idEntrepreneurship = idEntrepreneurship;
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
