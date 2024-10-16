package com.utn.CapitalConnection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Stars must not be null")
    @Positive(message = "Stars must be positive")
    @Column(nullable = false)
    private float stars;

    @NotBlank(message = "Review text must not be blank")
    @Column(nullable = false)
    private String reviewText;

    @ManyToMany
    @JoinTable(
            name = "reviews_about_entrepreneurship",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "entrepreneurship_id"))
    private List<EntrepreneurshipEntity> entrepreneurships;

    public ReviewEntity() {
    }

    public ReviewEntity(Long id, float stars, String reviewText) {
        this.id = id;
        this.stars = stars;
        this.reviewText = reviewText;
    }

    public Long getId() {
        return id;
    }

    public float getStars() {
        return stars;
    }

    public String getReviewText() {
        return reviewText;
    }

    public List<EntrepreneurshipEntity> getEntrepreneurships() {
        return entrepreneurships;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setEntrepreneurships(List<EntrepreneurshipEntity> entrepreneurships) {
        this.entrepreneurships = entrepreneurships;
    }
}

