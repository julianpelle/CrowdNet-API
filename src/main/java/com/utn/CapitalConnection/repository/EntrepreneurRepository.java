package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.EntrepreneurEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntrepreneurRepository extends UserRepository<EntrepreneurEntity>{
    // Find entrepreneurs with a minimum success rate
    @Query("SELECT e FROM EntrepreneurEntity e WHERE e.successRate >= :minSuccessRate")
    List<EntrepreneurEntity> findEntrepreneursBySuccessRate(@Param("minSuccessRate") float minSuccessRate);

    // Find entrepreneurs within a range of success rates
    @Query("SELECT e FROM EntrepreneurEntity e WHERE " +
            "(:minSuccessRate IS NULL OR e.successRate >= :minSuccessRate) " +
            "AND (:maxSuccessRate IS NULL OR e.successRate <= :maxSuccessRate)")
    List<EntrepreneurEntity> findEntrepreneursBySuccessRateRange(@Param("minSuccessRate") Float minSuccessRate,
                                                                 @Param("maxSuccessRate") Float maxSuccessRate);

    // Find entrepreneurs by specific CBU
    @Query("SELECT e FROM EntrepreneurEntity e WHERE e.cbu = :cbu")
    Optional<EntrepreneurEntity> findEntrepreneurByCbu(@Param("cbu") String cbu);

}
