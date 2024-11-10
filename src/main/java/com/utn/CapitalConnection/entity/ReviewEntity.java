package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ID User must not be null")
    @Column(nullable = false)
    private String idUser;

    @NotNull(message = "Stars must not be null")
    @Positive(message = "Stars must be positive")
    @Column(nullable = false)
    private float stars;

    @NotBlank(message = "Review text must not be blank")
    @Column(nullable = false)
    private String reviewText;

    public ReviewEntity() {
    }

    public ReviewEntity(String idUser, float stars, String reviewText) {
        this.idUser = idUser;
        this.stars = stars;
        this.reviewText = reviewText;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
