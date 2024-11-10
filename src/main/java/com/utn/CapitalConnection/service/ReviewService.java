package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.model.Review;
import com.utn.CapitalConnection.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Método para convertir Review a ReviewEntity.
     */
    public ReviewEntity convertToEntity(Review review) {
        return new ReviewEntity(
                review.getIdUser(),
                review.getStars(),
                review.getReviewText(),
                review.getIdEntrepreneurship(),
                review.getUsername()
        );
    }

    /**
     * Método para convertir ReviewEntity a Review.
     */
    public Review convertToModel(ReviewEntity reviewEntity) {
        return new Review(
                reviewEntity.getId(),
                reviewEntity.getIdUser(),
                reviewEntity.getStars(),
                reviewEntity.getReviewText(),
                reviewEntity.getIdEntrepreneurship(),
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

    public List<ReviewEntity> getReviewsByEntrepreneurship(Long idEntrepreneurship) {
        return reviewRepository.findByIdEntrepreneurship(idEntrepreneurship);
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
}
