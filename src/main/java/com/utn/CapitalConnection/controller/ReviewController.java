package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "Create a new review", description = "Saves a new review in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ReviewEntity> createReview(@Parameter(description = "Details of the review to create")
                                                     @Valid @RequestBody ReviewEntity review) {
        ReviewEntity createdReview = reviewService.saveReview(review);
        return ResponseEntity.status(201).body(createdReview);
    }

    @Operation(summary = "Get all reviews", description = "Returns a list of all reviews.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    @GetMapping
    public List<ReviewEntity> getAllReviews() {
        return reviewService.getAllReviews();
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

    @Operation(summary = "Get reviews by entrepreneurship ID", description = "Returns a list of reviews associated with a specific entrepreneurship.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found"),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found")
    })
    @GetMapping("/entrepreneurships/{entrepreneurshipId}")
    public List<ReviewEntity> getReviewsByEntrepreneurshipId(@Parameter(description = "ID of the entrepreneurship to search")
                                                             @PathVariable @NotNull Long entrepreneurshipId) {
        return reviewService.getReviewsByEntrepreneurshipId(entrepreneurshipId);
    }

    @Operation(summary = "Get reviews by star rating", description = "Returns a list of reviews that match a specific star rating.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    @GetMapping("/stars/{stars}")
    public List<ReviewEntity> getReviewsByStars(@Parameter(description = "Star rating to search for")
                                                @PathVariable @Positive float stars) {
        return reviewService.getReviewsByStars(stars);
    }

    @Operation(summary = "Count reviews for an entrepreneurship", description = "Returns the number of reviews for a specific entrepreneurship.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
    })
    @GetMapping("/count/{entrepreneurshipId}")
    public long countReviewsByEntrepreneurshipId(@Parameter(description = "ID of the entrepreneurship to count reviews for")
                                                 @PathVariable @NotNull Long entrepreneurshipId) {
        return reviewService.countReviewsByEntrepreneurshipId(entrepreneurshipId);
    }

    @Operation(summary = "Get average stars for an entrepreneurship", description = "Returns the average star rating for a specific entrepreneurship.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average stars retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found")
    })
    @GetMapping("/average/{entrepreneurshipId}")
    public ResponseEntity<Double> getAverageStarsByEntrepreneurshipId(@Parameter(description = "ID of the entrepreneurship to get average stars for")
                                                                      @PathVariable @NotNull Long entrepreneurshipId) {
        Double averageStars = reviewService.getAverageStarsByEntrepreneurshipId(entrepreneurshipId);
        return averageStars != null ? ResponseEntity.ok(averageStars) : ResponseEntity.notFound().build();
    }
}
