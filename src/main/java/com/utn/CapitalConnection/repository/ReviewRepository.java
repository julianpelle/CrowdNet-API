package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    // Método para obtener las reseñas por idEntrepreneurship
    List<ReviewEntity> findByIdEntrepreneurship(Long idEntrepreneurship);

    @Query("SELECT r FROM ReviewEntity r WHERE r.stars = :stars")
    List<ReviewEntity> findReviewsByStars(@Param("stars") float stars);



    @Query("SELECT r FROM ReviewEntity r WHERE r.idUser = :idUser")
    List<ReviewEntity> findByUserId(@Param("idUser") String idUser);

}
