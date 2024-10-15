package com.utn.CapitalConnection.model;

import java.util.Objects;

public class Review {

    private Long idReview;
   private Long idUser;
    private float stars;
    private String reviewText;

    public Review() {
    }

    public Review(Long idUser, float stars, String reviewText) {
        this.idUser = idUser;
        this.stars = stars;
        this.reviewText = reviewText;
    }

    public Review(Long idReview, Long idUser, float stars, String reviewText) {
        this.idReview = idReview;
        this.idUser = idUser;
        this.stars = stars;
        this.reviewText = reviewText;
    }

    public Long getIdReview() {
        return idReview;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return Float.compare(review.stars, stars) == 0 && Objects.equals(idReview, review.idReview) && Objects.equals(idUser, review.idUser) && Objects.equals(reviewText, review.reviewText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReview, idUser, stars, reviewText);
    }

    @Override
    public String toString() {
        return "Review{" +
                "idReview=" + idReview +
                ", idUser=" + idUser +
                ", stars=" + stars +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
