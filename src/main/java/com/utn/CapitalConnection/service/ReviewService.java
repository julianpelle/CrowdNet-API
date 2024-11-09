package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.exception.NoReviewFoundException;
import com.utn.CapitalConnection.repository.ReviewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Operation(summary = "Create a new review",
            description = "Saves a new review in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ReviewEntity saveReview(@Parameter(description = "Details of the review to create")
                                   @Valid ReviewEntity review) {
        return reviewRepository.save(review);
    }

    @Operation(summary = "Get all reviews",
            description = "Returns a list of all reviews.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Operation(summary = "Get a review by ID",
            description = "Returns details of a specific review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    public ReviewEntity getReviewById(@Parameter(description = "ID of the review to retrieve")
                                      @NotNull Long id) throws NoReviewFoundException {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NoReviewFoundException("Review not found with id " + id));
    }

    @Operation(summary = "Update an existing review",
            description = "Updates the details of a review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated successfully"),
            @ApiResponse(responseCode = "404", description = "Review not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ReviewEntity updateReview(@Parameter(description = "ID of the review to update")
                                     @NotNull Long id,
                                     @Parameter(description = "Details of the review to update")
                                     @Valid ReviewEntity reviewDetails) throws NoReviewFoundException {
        ReviewEntity existingReview = getReviewById(id);

        existingReview.setStars(reviewDetails.getStars());
        existingReview.setReviewText(reviewDetails.getReviewText());


        if (reviewDetails.getEntrepreneurships() != null) {
            existingReview.setEntrepreneurships(reviewDetails.getEntrepreneurships());
        }

        return reviewRepository.save(existingReview);
    }

    @Operation(summary = "Delete a review",
            description = "Deletes a specific review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Review deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    public void deleteReview(@Parameter(description = "ID of the review to delete")
                             @NotNull Long id) throws NoReviewFoundException {
        ReviewEntity existingReview = getReviewById(id);
        reviewRepository.delete(existingReview);
    }

    @Operation(summary = "Get reviews by entrepreneurship ID",
            description = "Returns a list of reviews associated with a specific entrepreneurship.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found"),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found")
    })
    public List<ReviewEntity> getReviewsByEntrepreneurshipId(@Parameter(description = "ID of the entrepreneurship to search")
                                                             @NotNull Long entrepreneurshipId) {
        return reviewRepository.findReviewsByEntrepreneurshipId(entrepreneurshipId);
    }

    @Operation(summary = "Get reviews by star rating",
            description = "Returns a list of reviews that match a specific star rating.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    public List<ReviewEntity> getReviewsByStars(@Parameter(description = "Star rating to search for")
                                                @Positive float stars) {
        return reviewRepository.findReviewsByStars(stars);
    }



    @Operation(summary = "Get reviews by user comment",
            description = "Returns a list of reviews that match a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    public List<ReviewEntity> getReviewsByUserId(@Parameter(description = "Id rating to search for")
                                                @Positive String id) {
        return reviewRepository.findByUserId(id);
    }




    @Operation(summary = "Count reviews for an entrepreneurship",
            description = "Returns the number of reviews for a specific entrepreneurship.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
    })
    public long countReviewsByEntrepreneurshipId(@Parameter(description = "ID of the entrepreneurship to count reviews for")
                                                 @NotNull Long entrepreneurshipId) {
        return reviewRepository.countReviewsByEntrepreneurshipId(entrepreneurshipId);
    }

    @Operation(summary = "Get average stars for an entrepreneurship",
            description = "Returns the average star rating for a specific entrepreneurship.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average stars retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found")
    })
    public Double getAverageStarsByEntrepreneurshipId(@Parameter(description = "ID of the entrepreneurship to get average stars for")
                                                      @NotNull Long entrepreneurshipId) {
        return reviewRepository.findAverageStarsByEntrepreneurshipId(entrepreneurshipId);
    }
}
