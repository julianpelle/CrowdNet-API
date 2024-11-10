package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.dto.ReviewRequest;
import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.model.Review;
import com.utn.CapitalConnection.service.EntrepreneurshipService;
import com.utn.CapitalConnection.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    private EntrepreneurshipService entrepreneurshipService;

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        // Convertir Review a ReviewEntity
        ReviewEntity reviewEntity = reviewService.convertToEntity(review);
        ReviewEntity savedReview = reviewService.createReview(reviewEntity);
        // Convertir ReviewEntity de vuelta a Review
        return reviewService.convertToModel(savedReview);
    }



    @Operation(summary = "Get a review by ID", description = "Returns details of a specific review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReviewEntity> getReviewById(
            @Parameter(description = "ID of the review to retrieve") @PathVariable @NotNull Long id) {
        return Optional.ofNullable(reviewService.getReviewById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @Operation(summary = "Update an existing review", description = "Updates the details of a review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated successfully"),
            @ApiResponse(responseCode = "404", description = "Review not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReviewEntity> updateReview(@Parameter(description = "ID of the review to update")
                                                     @PathVariable @NotNull Long id,
                                                     @Parameter(description = "Details of the review to update")
                                                     @Valid @RequestBody ReviewEntity reviewDetails) {
        ReviewEntity updatedReview = reviewService.updateReview(id, reviewDetails);
        return ResponseEntity.ok(updatedReview);
    }

    @Operation(summary = "Delete a review", description = "Deletes a specific review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Review deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@Parameter(description = "ID of the review to delete")
                                             @PathVariable @NotNull Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all reviews", description = "Returns a list of all reviews.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    @GetMapping
    public ResponseEntity<List<ReviewEntity>> getAllReviews() {
        List<ReviewEntity> reviews = reviewService.getAllReviews1();
        return ResponseEntity.ok(reviews);
    }





}
