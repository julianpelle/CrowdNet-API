package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntrepreneurshipRepository extends JpaRepository<EntrepreneurshipEntity, Long> {

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) = LOWER(:name)")
    Optional<EntrepreneurshipEntity> findByName(@Param("name") String name);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<EntrepreneurshipEntity> findByNameContaining(@Param("name") String name);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.goal >= :minGoal")
    List<EntrepreneurshipEntity> findByMinimumGoal(@Param("minGoal") BigDecimal minGoal);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.goal <= :maxGoal")
    List<EntrepreneurshipEntity> findByMaximumGoal(@Param("maxGoal") BigDecimal maxGoal);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE " +
            "(:minGoal IS NULL OR e.goal >= :minGoal) " +
            "AND (:maxGoal IS NULL OR e.goal <= :maxGoal)")
    List<EntrepreneurshipEntity> findByGoalRange(@Param("minGoal") BigDecimal minGoal,
                                                 @Param("maxGoal") BigDecimal maxGoal);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<EntrepreneurshipEntity> findByDescriptionContaining(@Param("description") String description);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE SOUNDEX(e.name) = SOUNDEX(:name)")
    List<EntrepreneurshipEntity> findByNameSimilar(@Param("name") String name);

    @Query("SELECT e FROM EntrepreneurshipEntity e JOIN e.reviews r WHERE r.stars = :stars")
    Page<EntrepreneurshipEntity> findByStars(@Param("stars") float stars, Pageable pageable);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')) AND e.goal = :goal")
    Page<EntrepreneurshipEntity> findByNameAndGoal(@Param("name") String name, @Param("goal") BigDecimal goal, Pageable pageable);

    @Query("SELECT e FROM EntrepreneurshipEntity e JOIN e.reviews r WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')) AND r.stars = :stars")
    Page<EntrepreneurshipEntity> findByNameAndStars(@Param("name") String name, @Param("stars") float stars, Pageable pageable);

    @Query("SELECT e FROM EntrepreneurshipEntity e JOIN e.reviews r WHERE r.stars = :stars AND e.goal = :goal")
    Page<EntrepreneurshipEntity> findByStarsAndGoal(@Param("stars") float stars, @Param("goal") BigDecimal goal, Pageable pageable);

    @Query("SELECT e FROM EntrepreneurshipEntity e JOIN e.reviews r WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')) AND r.stars = :stars AND e.goal = :goal")
    Page<EntrepreneurshipEntity> findByNameStarsGoal(@Param("name") String name, @Param("stars") float stars, @Param("goal") BigDecimal goal, Pageable pageable);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    Page<EntrepreneurshipEntity> findByDescriptionContainingIgnoreCase(@Param("description") String description, Pageable pageable);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.category = :category")
    Page<EntrepreneurshipEntity> findByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.goal = :goal")
    Page<EntrepreneurshipEntity> findByGoal(@Param("goal") BigDecimal goal, Pageable pageable);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<EntrepreneurshipEntity> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Query("SELECT r FROM EntrepreneurshipEntity r WHERE r.id_user = :id_user")
    List<EntrepreneurshipEntity> findByUserId(@Param("id_user") String idUser);
}
