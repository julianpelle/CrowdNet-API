package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.InvestorEntity;
import com.utn.CapitalConnection.repository.InvestorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvestorService extends UserService<InvestorEntity> {

    private final InvestorRepository investorRepository;

    @Autowired
    public InvestorService(InvestorRepository investorRepository) {
        super(investorRepository);
        this.investorRepository = investorRepository;
    }

    @Operation(summary = "Find investors by minimum portfolio value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid minimum portfolio value")
    })
    public List<InvestorEntity> findInvestorsByPortfolioValue(
            @Parameter(description = "Minimum portfolio value", example = "10000.00") BigDecimal minPortfolioValue) {
        return investorRepository.findInvestorsByPortfolioValue(minPortfolioValue);
    }

    @Operation(summary = "Find investors by minimum years of experience")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid minimum years of experience")
    })
    public List<InvestorEntity> findInvestorsByExperience(
            @Parameter(description = "Minimum years of experience", example = "5") int minYearsOfExperience) {
        return investorRepository.findInvestorsByExperience(minYearsOfExperience);
    }

    @Operation(summary = "Find investors within a portfolio value range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid portfolio value range")
    })
    public List<InvestorEntity> findInvestorsByPortfolioValueRange(
            @Parameter(description = "Minimum portfolio value", example = "10000.00") BigDecimal minPortfolioValue,
            @Parameter(description = "Maximum portfolio value", example = "50000.00") BigDecimal maxPortfolioValue) {
        return investorRepository.findInvestorsByPortfolioRange(minPortfolioValue, maxPortfolioValue);
    }
}
