package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.dto.ReviewRequest;
import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.exception.EntrepreneurshipNotFoundException;
import com.utn.CapitalConnection.model.Review;
import com.utn.CapitalConnection.repository.EntrepreneurshipRepository;
import com.utn.CapitalConnection.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, EntrepreneurshipRepository entrepreneurshipRepository) {
        this.reviewRepository = reviewRepository;
        this.entrepreneurshipRepository = entrepreneurshipRepository;
    };

    /**
     * Método para convertir Review a ReviewEntity.
     */
    public ReviewEntity convertToEntity(ReviewRequest review) {
        if (review.getIdEntrepreneurship() == null) {
            System.out.println(review.getIdEntrepreneurship());

            throw new IllegalArgumentException("The ID must not be null FORRO");

        }else{
            System.out.println(review.getIdEntrepreneurship());
        }
        EntrepreneurshipEntity entrepreneurship = entrepreneurshipRepository.findById(review.getIdEntrepreneurship())
                .orElseThrow(() -> new EntrepreneurshipNotFoundException("Entrepreneurship not found with id: " + review.getIdEntrepreneurship()));


        return new ReviewEntity(
                review.getIdUser(),
                review.getStars(),
                review.getReviewText(),
                review.getUsername(),
                entrepreneurship
        );
    }

    /**
     * Método para convertir ReviewEntity a Review.
     */
    public Review convertToModel(ReviewEntity reviewEntity) {
        return new Review(
                reviewEntity.getIdReview(),
                reviewEntity.getIdUser(),
                reviewEntity.getStars(),
                reviewEntity.getReviewText(),
                reviewEntity.getEntrepreneurship().getId(),
                reviewEntity.getUsername()
        );
    }

    /**
     * Método para guardar una reseña en la base de datos.
     */
    public ReviewEntity createReview(ReviewEntity reviewEntity) {
        return reviewRepository.save(reviewEntity);
    }

    /**
     * Método para obtener una reseña por ID.
     */
    public ReviewEntity getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    /**
     * Método para obtener todas las reseñas.
     */
    public Iterable<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<ReviewRequest> getReviewsByEntrepreneurship(Long idEntrepreneurship) {
        // Aquí obtienes los ReviewEntity y luego los conviertes a ReviewDTO
        List<ReviewEntity> reviewEntities = reviewRepository.findByEntrepreneurshipId(idEntrepreneurship);

        return reviewEntities.stream()
                .map(reviewEntity -> new ReviewRequest(
                        reviewEntity.getIdReview(),
                        reviewEntity.getIdUser(),
                        reviewEntity.getUsername(),
                        reviewEntity.getStars(),
                        reviewEntity.getReviewText(),
                        reviewEntity.getEntrepreneurship().getId()
                        ))
                .collect(Collectors.toList());
    }
    /**
     * Método para obtener reseñas por estrellas.
     */
    public Iterable<ReviewEntity> getReviewsByStars(float stars) {
        return reviewRepository.findReviewsByStars(stars);
    }

    /**
     * Método para obtener reseñas por ID de usuario.
     */
    public Iterable<ReviewEntity> getReviewsByUserId(String userId) {
        return reviewRepository.findByUserId(userId);
    }

    /**
     * Método para actualizar una reseña existente.
     */
    public ReviewEntity updateReview(Long id, ReviewEntity reviewDetails) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        // Actualizar detalles de la reseña
        reviewEntity.setStars(reviewDetails.getStars());
        reviewEntity.setReviewText(reviewDetails.getReviewText());

        return reviewRepository.save(reviewEntity);
    }

    /**
     * Método para eliminar una reseña por ID.
     */
    public void deleteReview(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        reviewRepository.delete(reviewEntity);
    }

    public List<ReviewEntity> getAllReviews1() {
        return reviewRepository.findAll();
    }


    public ReviewEntity updateReviewPatch(Long id, ReviewEntity reviewDetails) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        // Aquí solo actualizamos los campos que realmente han sido enviados para su actualización
        if (reviewDetails.getStars() != 0) {
            reviewEntity.setStars(reviewDetails.getStars());
        }
        if (reviewDetails.getReviewText() != null && !reviewDetails.getReviewText().isEmpty()) {
            reviewEntity.setReviewText(reviewDetails.getReviewText());
        }
        if (reviewDetails.getUsername() != null && !reviewDetails.getUsername().isEmpty()) {
            reviewEntity.setUsername(reviewDetails.getUsername());
        }

        // De nuevo, si tienes más campos que quieras manejar, simplemente agrega más condiciones aquí.

        return reviewRepository.save(reviewEntity);
    }
}
