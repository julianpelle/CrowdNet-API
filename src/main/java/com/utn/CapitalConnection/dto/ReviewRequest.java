package com.utn.CapitalConnection.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload for Review information")
public class ReviewRequest {

    @Schema(description = "ID of the review", example = "1")
    private Long idReview;

    @Schema(description = "ID of the user who wrote the review", example = "Three")
    private String idUser;

    @Schema(description = "Star rating of the review", example = "4.5")
    private float stars;

    @Schema(description = "Text of the review", example = "Great project with a promising future!")
    private String reviewText;

    @Schema(description = "ID of the entrepreneurship the review is about", example = "2")
    private Long entrepreneurshipId;  // ID of the entrepreneurship to associate with the review

    // Getters and setters

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

    public Long getEntrepreneurshipId() {
        return entrepreneurshipId;
    }

    public void setEntrepreneurshipId(Long entrepreneurshipId) {
        this.entrepreneurshipId = entrepreneurshipId;
    }
}
