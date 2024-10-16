package com.utn.CapitalConnection.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class Review {

    private Long idReview;

    @NotNull(message = "Stars must not be null")
    @Positive(message = "Stars must be positive")
    private float stars;

    @NotBlank(message = "Review text must not be blank")
    private String reviewText;

    public Review() {
    }

    public Review(float stars, String reviewText) {
        this.stars = stars;
        this.reviewText = reviewText;
    }

    public Review(Long idReview, float stars, String reviewText) {
        this.idReview = idReview;
        this.stars = stars;
        this.reviewText = reviewText;
    }

    public Long getIdReview() {
        return idReview;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return Float.compare(review.stars, stars) == 0 && Objects.equals(idReview, review.idReview) && Objects.equals(reviewText, review.reviewText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReview, stars, reviewText);
    }

    @Override
    public String toString() {
        return "Review{" +
                "idReview=" + idReview +
                ", stars=" + stars +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
