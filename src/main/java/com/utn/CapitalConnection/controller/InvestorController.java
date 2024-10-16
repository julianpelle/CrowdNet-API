package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.entity.InvestorEntity;
import com.utn.CapitalConnection.exception.InvestorNonExistingException;
import com.utn.CapitalConnection.service.InvestorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/investors")
@Validated
public class InvestorController extends UserController<InvestorEntity> {

    private final InvestorService investorService;


    @Autowired
    public InvestorController(InvestorService investorService) {
        super(investorService);
        this.investorService = investorService;
    }



    @Operation(summary = "Get all investors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully")
    })
    @GetMapping
    @Override
    public List<InvestorEntity> getAllUsers() {
        return investorService.findAllUsers();
    }

    @Operation(summary = "Get an investor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investor retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Investor not found")
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<InvestorEntity> getUserById(
            @Parameter(description = "Unique account identifier", example = "1") @PathVariable Long id) {
        try {
            InvestorEntity investor = investorService.findUserById(id);
            return ResponseEntity.ok(investor);
        } catch (InvestorNonExistingException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Create a new investor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Investor created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid investor data")
    })
    @PostMapping
    @Override
    public ResponseEntity<InvestorEntity> createUser(@Valid @RequestBody InvestorEntity investor) {
        try {
            InvestorEntity savedInvestor = investorService.saveUser(investor);
            return ResponseEntity.status(201).body(savedInvestor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(summary = "Update an investor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investor updated successfully"),
            @ApiResponse(responseCode = "404", description = "Investor not found"),
            @ApiResponse(responseCode = "400", description = "Invalid investor data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InvestorEntity> updateInvestor(
            @Parameter(description = "Unique account identifier", example = "1") @PathVariable Long id,
            @Parameter(description = "Investor data to update") @Valid @RequestBody InvestorEntity investor) {
        try {
            InvestorEntity existingInvestor = investorService.findUserById(id);

            existingInvestor.setName(investor.getName());
            existingInvestor.setSurname(investor.getSurname());
            existingInvestor.setEmail(investor.getEmail());
            existingInvestor.setDateOfBirth(investor.getDateOfBirth());
            existingInvestor.setWallet(investor.getWallet());
            existingInvestor.setYearsOfExperience(investor.getYearsOfExperience());
            existingInvestor.setCategory(investor.getCategory());
            existingInvestor.setAddress(investor.getAddress());
            existingInvestor.setPortfolioValue(investor.getPortfolioValue());

            InvestorEntity updatedInvestor = investorService.saveUser(existingInvestor);
            return ResponseEntity.ok(updatedInvestor);

        } catch (InvestorNonExistingException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Delete an investor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Investor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Investor not found")
    })
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteUser(@Parameter(description = "Unique account identifier", example = "1") @PathVariable Long id) {
        try {
            investorService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (InvestorNonExistingException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Find investors by minimum portfolio value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid minimum portfolio value")
    })
    @GetMapping("/portfolioValue")
    public List<InvestorEntity> findInvestorsByPortfolioValue(
            @Parameter(description = "Minimum portfolio value", example = "10000.00") @RequestParam @NotNull BigDecimal minPortfolioValue) {
        return investorService.findInvestorsByPortfolioValue(minPortfolioValue);
    }

    @Operation(summary = "Find investors by minimum years of experience")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid minimum years of experience")
    })
    @GetMapping("/experience")
    public List<InvestorEntity> findInvestorsByExperience(
            @Parameter(description = "Minimum years of experience", example = "5") @RequestParam @NotNull @Positive int minYearsOfExperience) {
        return investorService.findInvestorsByExperience(minYearsOfExperience);
    }

    @Operation(summary = "Find investors within a portfolio value range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of investors retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid portfolio value range")
    })
    @GetMapping("/portfolioRange")
    public List<InvestorEntity> findInvestorsByPortfolioValueRange(
            @Parameter(description = "Minimum portfolio value", example = "10000.00") @RequestParam BigDecimal minPortfolioValue,
            @Parameter(description = "Maximum portfolio value", example = "50000.00") @RequestParam BigDecimal maxPortfolioValue) {
        return investorService.findInvestorsByPortfolioValueRange(minPortfolioValue, maxPortfolioValue);
    }
}
