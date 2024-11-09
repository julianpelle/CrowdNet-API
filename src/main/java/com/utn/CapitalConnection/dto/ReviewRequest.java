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

    public Long getIdReview() {
        return idReview;
    }

    public String getIdUser() {
        return idUser;
    }

    public float getStars() {
        return stars;
    }

    public String getReviewText() {
        return reviewText;
    }
}