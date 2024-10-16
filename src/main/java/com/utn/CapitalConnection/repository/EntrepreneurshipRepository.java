package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import com.utn.CapitalConnection.types.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntrepreneurshipRepository extends JpaRepository<EntrepreneurshipEntity, Long> {

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) = LOWER(:name)")
    Optional<EntrepreneurshipEntity> findByName(@Param("name") String name);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<EntrepreneurshipEntity> findByNameContaining(@Param("name") String name);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.category = :category")
    List<EntrepreneurshipEntity> findByCategory(@Param("category") Category category);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.goal >= :minGoal")
    List<EntrepreneurshipEntity> findByMinimumGoal(@Param("minGoal") float minGoal);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.goal <= :maxGoal")
    List<EntrepreneurshipEntity> findByMaximumGoal(@Param("maxGoal") float maxGoal);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE " +
            "(:minGoal IS NULL OR e.goal >= :minGoal) " +
            "AND (:maxGoal IS NULL OR e.goal <= :maxGoal)")
    List<EntrepreneurshipEntity> findByGoalRange(@Param("minGoal") Float minGoal,
                                                 @Param("maxGoal") Float maxGoal);
    @Query("SELECT e FROM EntrepreneurshipEntity e JOIN e.entrepreneurs ent WHERE ent.id = :entrepreneurId")
    List<EntrepreneurshipEntity> findByEntrepreneurId(@Param("entrepreneurId") Long entrepreneurId);

    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<EntrepreneurshipEntity> findByDescriptionContaining(@Param("description") String description);


    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE SOUNDEX(e.name) = SOUNDEX(:name)")
    List<EntrepreneurshipEntity> findByNameSimilar(@Param("name") String name);

}
