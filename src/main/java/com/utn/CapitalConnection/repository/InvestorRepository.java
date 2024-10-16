package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.InvestorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InvestorRepository extends UserRepository<InvestorEntity>{
    // Find investors with a minimum portfolio value
    @Query("SELECT i FROM InvestorEntity i WHERE i.portfolioValue >= :minPortfolioValue")
    List<InvestorEntity> findInvestorsByPortfolioValue(@Param("minPortfolioValue") BigDecimal minPortfolioValue);

    // Find investors by minimum years of experience
    @Query("SELECT i FROM InvestorEntity i WHERE i.yearsOfExperience >= :minYearsOfExperience")
    List<InvestorEntity> findInvestorsByExperience(@Param("minYearsOfExperience") int minYearsOfExperience);

    // Find investors within a range of portfolio values
    @Query("SELECT i FROM InvestorEntity i WHERE " +
            "(:minPortfolioValue IS NULL OR i.portfolioValue >= :minPortfolioValue) " +
            "AND (:maxPortfolioValue IS NULL OR i.portfolioValue <= :maxPortfolioValue)")
    List<InvestorEntity> findInvestorsByPortfolioRange(@Param("minPortfolioValue") BigDecimal minPortfolioValue,
                                                       @Param("maxPortfolioValue") BigDecimal maxPortfolioValue);

}
