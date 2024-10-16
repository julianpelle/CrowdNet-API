package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT r FROM ReviewEntity r JOIN r.entrepreneurships e WHERE e.id = :entrepreneurshipId")
    List<ReviewEntity> findReviewsByEntrepreneurshipId(@Param("entrepreneurshipId") Long entrepreneurshipId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.stars = :stars")
    List<ReviewEntity> findReviewsByStars(@Param("stars") float stars);

    @Query("SELECT COUNT(r) FROM ReviewEntity r JOIN r.entrepreneurships e WHERE e.id = :entrepreneurshipId")
    long countReviewsByEntrepreneurshipId(@Param("entrepreneurshipId") Long entrepreneurshipId);

    @Query("SELECT AVG(r.stars) FROM ReviewEntity r JOIN r.entrepreneurships e WHERE e.id = :entrepreneurshipId")
    Double findAverageStarsByEntrepreneurshipId(@Param("entrepreneurshipId") Long entrepreneurshipId);

}
