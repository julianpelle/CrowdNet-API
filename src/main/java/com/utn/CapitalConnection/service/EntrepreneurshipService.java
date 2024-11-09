package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import com.utn.CapitalConnection.entity.PictureEntity;
import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.entity.VideoEntity;
import com.utn.CapitalConnection.exception.NoEntrepreneurshipsFoundException;
import com.utn.CapitalConnection.model.Entrepreneurship;
import com.utn.CapitalConnection.model.Review;
import com.utn.CapitalConnection.repository.EntrepreneurshipRepository;
import com.utn.CapitalConnection.types.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntrepreneurshipService {

    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    public List<EntrepreneurshipEntity> getAllEntrepreneurships() {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findAll();
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found.");
        }
        return entrepreneurships;
    }

    public Page<Entrepreneurship> findEntrepreneurship(String name, Float stars, BigDecimal goal, Pageable pageable) { Page<EntrepreneurshipEntity> entity; // Filtrado
        if (name != null && stars != null && goal != null) { entity = entrepreneurshipRepository.findByNameStarsGoal(name, stars, goal, pageable); } else if (name != null && stars != null) { entity = entrepreneurshipRepository.findByNameAndStars(name, stars, pageable); } else if (name != null && goal != null) { entity = entrepreneurshipRepository.findByNameAndGoal(name, goal, pageable); } else if (stars != null && goal != null) { entity = entrepreneurshipRepository.findByStarsAndGoal(stars, goal, pageable); } else if (name != null) { entity = entrepreneurshipRepository.findByNameContainingIgnoreCase(name, pageable); } else if (stars != null) { entity = entrepreneurshipRepository.findByStars(stars, pageable); } else if (goal != null) { entity = entrepreneurshipRepository.findByGoal(goal, pageable); } else { entity = entrepreneurshipRepository.findAll(pageable); } return entity.map(this::entityToEntrepreneurship);}

    private Entrepreneurship entityToEntrepreneurship(EntrepreneurshipEntity entity) {
        Entrepreneurship entrepreneurship = new Entrepreneurship();

        entrepreneurship.setId(entity.getId());
        entrepreneurship.setName(entity.getName());
        entrepreneurship.setDescription(entity.getDescription());
        entrepreneurship.setGoal(entity.getGoal());
        entrepreneurship.setCategory(entity.getCategory());
        entrepreneurship.setImages(entity.getPictures());
        entrepreneurship.setVideos(entity.getVideos());



        List<Review> reviews = new ArrayList<>();
        for (ReviewEntity reviewEntity : entity.getReviews()) {
            Review review = new Review();
            review.setIdReview(reviewEntity.getId());
            review.setStars(reviewEntity.getStars());
            review.setReviewText(reviewEntity.getReviewText());
            reviews.add(review);
        }
        entrepreneurship.setReviewList(reviews);

        return entrepreneurship;
    }


    // GET all entrepreneurships
    public List<EntrepreneurshipEntity> getAllEntrepreneurships() {
        List<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipRepository.findAll();
        if (entrepreneurships.isEmpty()) {
            throw new NoEntrepreneurshipsFoundException("No entrepreneurships found.");
        }
        return entrepreneurships;
    }


    // GET by exact name
    public EntrepreneurshipEntity getEntrepreneurshipByName(@NotBlank String name) {
        return entrepreneurshipRepository.findByName(name)
                .orElseThrow(() -> new NoEntrepreneurshipsFoundException("Entrepreneurship not found with name: " + name));
    }

    // POST create a new entrepreneurship
    public EntrepreneurshipEntity createEntrepreneurship(EntrepreneurshipEntity entrepreneurship) {
        System.out.println(entrepreneurship);
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    // PUT update an entrepreneurship by ID
    @Transactional
    public EntrepreneurshipEntity updateEntrepreneurship(@NotNull Long id, EntrepreneurshipEntity updatedEntrepreneurship) {
        EntrepreneurshipEntity entrepreneurship = entrepreneurshipRepository.findById(id)
                .orElseThrow(() -> new NoEntrepreneurshipsFoundException("Entrepreneurship not found with id: " + id));

        entrepreneurship.setName(updatedEntrepreneurship.getName());
        entrepreneurship.setCategory(updatedEntrepreneurship.getCategory());
        entrepreneurship.setGoal(updatedEntrepreneurship.getGoal());
        entrepreneurship.setDescription(updatedEntrepreneurship.getDescription());

        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Operation(summary = "Get ntrepreneurships by user creator",
            description = "Returns a list of entrepreneurships that match a specific star rating.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneurship found")
    })
    public List<EntrepreneurshipEntity> getEntrepreneurshipByUserId(@Parameter(description = "Star rating to search for")
                                                 @Positive String id) {
        return entrepreneurshipRepository.findByUserId(id);
    }

    // PATCH partially update an entrepreneurship
    @Transactional
    public EntrepreneurshipEntity partiallyUpdateEntrepreneurship(@NotNull Long id, String name, BigDecimal goal) {
        EntrepreneurshipEntity entrepreneurship = entrepreneurshipRepository.findById(id)
                .orElseThrow(() -> new NoEntrepreneurshipsFoundException("Entrepreneurship not found with id: " + id));

        if (name != null) {
            entrepreneurship.setName(name);
        }
        if (goal != null) {
            entrepreneurship.setGoal(goal);
        }

        return entrepreneurshipRepository.save(entrepreneurship);
    }

    // DELETE an entrepreneurship by ID
    public void deleteEntrepreneurship(@NotNull Long id) {
        if (!entrepreneurshipRepository.existsById(id)) {
            throw new NoEntrepreneurshipsFoundException("Entrepreneurship not found with id: " + id);
        }
        entrepreneurshipRepository.deleteById(id);
    }
}
