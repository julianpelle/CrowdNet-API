package com.utn.CapitalConnection.repository;

import com.utn.CapitalConnection.entity.InvestorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InvestorRepository extends UserRepository<InvestorEntity>{
    @Query("SELECT i FROM InvestorEntity i WHERE i.portfolioValue >= :minPortfolioValue")
    List<InvestorEntity> findInvestorsByPortfolioValue(@Param("minPortfolioValue") BigDecimal minPortfolioValue);

    @Query("SELECT i FROM InvestorEntity i WHERE i.yearsOfExperience >= :minYearsOfExperience")
    List<InvestorEntity> findInvestorsByExperience(@Param("minYearsOfExperience") int minYearsOfExperience);

    @Query("SELECT i FROM InvestorEntity i WHERE " +
            "(:minPortfolioValue IS NULL OR i.portfolioValue >= :minPortfolioValue) " +
            "AND (:maxPortfolioValue IS NULL OR i.portfolioValue <= :maxPortfolioValue)")
    List<InvestorEntity> findInvestorsByPortfolioRange(@Param("minPortfolioValue") BigDecimal minPortfolioValue,
                                                       @Param("maxPortfolioValue") BigDecimal maxPortfolioValue);

}
