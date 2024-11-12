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

    // Método para encontrar por nombre exacto
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) = LOWER(:name)")
    Optional<EntrepreneurshipEntity> findByName(@Param("name") String name);

    // Método para encontrar por nombre que contenga la cadena proporcionada
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<EntrepreneurshipEntity> findByNameContaining(@Param("name") String name);

    // Método para encontrar por meta mínima
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.goal >= :minGoal")
    List<EntrepreneurshipEntity> findByMinimumGoal(@Param("minGoal") BigDecimal minGoal);

    // Método para encontrar por meta máxima
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.goal <= :maxGoal")
    List<EntrepreneurshipEntity> findByMaximumGoal(@Param("maxGoal") BigDecimal maxGoal);

    // Método para encontrar por rango de meta
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE " +
            "(:minGoal IS NULL OR e.goal >= :minGoal) " +
            "AND (:maxGoal IS NULL OR e.goal <= :maxGoal)")
    List<EntrepreneurshipEntity> findByGoalRange(@Param("minGoal") BigDecimal minGoal,
                                                 @Param("maxGoal") BigDecimal maxGoal);

    // Método para encontrar por descripción que contenga la cadena
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<EntrepreneurshipEntity> findByDescriptionContaining(@Param("description") String description);

    // Método para encontrar por nombre similar (basado en SOUNDEX)
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE SOUNDEX(e.name) = SOUNDEX(:name)")
    List<EntrepreneurshipEntity> findByNameSimilar(@Param("name") String name);


    // Paginación por nombre y meta
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')) AND e.goal = :goal")
    Page<EntrepreneurshipEntity> findByNameAndGoal(@Param("name") String name, @Param("goal") BigDecimal goal, Pageable pageable);

    // Paginación por descripción
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    Page<EntrepreneurshipEntity> findByDescriptionContainingIgnoreCase(@Param("description") String description, Pageable pageable);

    // Paginación por categoría
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.category = :category")
    Page<EntrepreneurshipEntity> findByCategory(@Param("category") String category, Pageable pageable);

    // Paginación por meta
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE e.goal = :goal")
    Page<EntrepreneurshipEntity> findByGoal(@Param("goal") BigDecimal goal, Pageable pageable);

    // Paginación por nombre
    @Query("SELECT e FROM EntrepreneurshipEntity e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<EntrepreneurshipEntity> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);


    // Método para encontrar por ID de usuario
    @Query("SELECT r FROM EntrepreneurshipEntity r WHERE r.idUser = :idUser")
    List<EntrepreneurshipEntity> findByUserId(@Param("idUser") String idUser);

    Page<EntrepreneurshipEntity> findByIsActivatedTrue(Pageable pageable);
}
