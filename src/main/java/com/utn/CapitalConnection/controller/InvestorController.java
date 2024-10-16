package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.entity.InvestorEntity;
import com.utn.CapitalConnection.service.InvestorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/investors")
public class InvestorController extends UserController<InvestorEntity> {

    private final InvestorService investorService;

    @Autowired
    public InvestorController(InvestorService investorService) {
        super(investorService);
        this.investorService = investorService;
    }

    // Get all investors
    @Operation(summary = "Get all investors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully")
    })
    @GetMapping
    @Override
    public List<InvestorEntity> getAllUsers() {
        return investorService.findAllUsers();
    }

    // Get investor by ID
    @Operation(summary = "Get an account by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<InvestorEntity> getUserById(
            @Parameter(description = "Unique account identifier", example = "1")
            @PathVariable Long id) {
        return investorService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new investor
    @Operation(summary = "Create a new investor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Investor created successfully")
    })
    @PostMapping
    @Override
    public InvestorEntity createUser(@RequestBody InvestorEntity investor) {
        return investorService.saveUser(investor);
    }

    // Update investor
    @Operation(summary = "Update an investor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investor updated successfully"),
            @ApiResponse(responseCode = "404", description = "Investor not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InvestorEntity> updateInvestor(
            @Parameter(description = "Unique account identifier", example = "1") @PathVariable Long id,
            @Parameter(description = "Investor data to update") @RequestBody InvestorEntity investor) {
        return investorService.findUserById(id)
                .map(existingInvestor -> {
                    investor.setId(id); // Ensure that the ID is set correctly
                    return ResponseEntity.ok(investorService.saveUser(investor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete investor
    @Operation(summary = "Delete an investor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Investor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Investor not found")
    })
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteUser(@Parameter(description = "Unique account identifier", example = "1") @PathVariable Long id) {
        investorService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Find investors by minimum portfolio value
    @Operation(summary = "Find investors by minimum portfolio value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully")
    })
    @GetMapping("/portfolioValue")
    public List<InvestorEntity> findInvestorsByPortfolioValue(
            @Parameter(description = "Minimum portfolio value", example = "10000.00") @RequestParam BigDecimal minPortfolioValue) {
        return investorService.findInvestorsByPortfolioValue(minPortfolioValue);
    }

    // Find investors by minimum years of experience
    @Operation(summary = "Find investors by minimum years of experience")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully")
    })
    @GetMapping("/experience")
    public List<InvestorEntity> findInvestorsByExperience(
            @Parameter(description = "Minimum years of experience", example = "5") @RequestParam int minYearsOfExperience) {
        return investorService.findInvestorsByExperience(minYearsOfExperience);
    }

    // Find investors within a portfolio value range
    @Operation(summary = "Find investors within a portfolio value range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully")
    })
    @GetMapping("/portfolioRange")
    public List<InvestorEntity> findInvestorsByPortfolioValueRange(
            @Parameter(description = "Minimum portfolio value", example = "10000.00") @RequestParam BigDecimal minPortfolioValue,
            @Parameter(description = "Maximum portfolio value", example = "50000.00") @RequestParam BigDecimal maxPortfolioValue) {
        return investorService.findInvestorsByPortfolioValueRange(minPortfolioValue, maxPortfolioValue);
    }
}
