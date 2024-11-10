package com.utn.CapitalConnection.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Review {

    private Long idReview;
    private String idUser;

    @NotNull(message = "Stars must not be null")
    @Positive(message = "Stars must be positive")
    private float stars;

    @NotBlank(message = "Review text must not be blank")
    private String reviewText;

    // Constructor de DTO Review
    public Review(Long idReview, String idUser, float stars, String reviewText) {
        this.idReview = idReview;
        this.idUser = idUser;
        this.stars = stars;
        this.reviewText = reviewText;
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
}
