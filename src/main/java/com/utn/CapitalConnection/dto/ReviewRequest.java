package com.utn.CapitalConnection.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload for Review information")
public class ReviewRequest {

    @Schema(description = "ID of the review", example = "1")
    private Long id;

    @Schema(description = "ID of the user who wrote the review", example = "Three")
    private String idUser;

    @Schema(description = "Nickname of the user who wrote the review", example = "Three")
    private String username;


    @Schema(description = "Star rating of the review", example = "4.5")
    private float stars;

    @Schema(description = "Text of the review", example = "Great project with a promising future!")
    private String reviewText;


    @Schema(description = "ID of the entrepreneurship the review is about", example = "2")
    private Long idEntrepreneurship;  // ID of the entrepreneurship to associate with the review

    public ReviewRequest(Long id, String idUser, String username, float stars, String reviewText, Long idEntrepreneurship) {
        this.id = id;
        this.idUser = idUser;
        this.username = username;
        this.stars = stars;
        this.reviewText = reviewText;
        this.idEntrepreneurship = idEntrepreneurship;
    }

    // Getters and setters

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

    public Long getIdEntrepreneurship() {
        return idEntrepreneurship;
    }

    public void setIdEntrepreneurship(Long idEntrepreneurship) {
        this.idEntrepreneurship = idEntrepreneurship;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
