package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.entity.DonationEntity;
import com.utn.CapitalConnection.exception.DonationNonExistingException;
import com.utn.CapitalConnection.service.DonationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    private final DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @Operation(summary = "Register a new donation",
            description = "Creates a new donation in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Donation registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<DonationEntity> createDonation(
            @Parameter(description = "Details of the donation to register")
            @Valid @RequestBody DonationEntity donation) {
        try {
            DonationEntity savedDonation = donationService.saveDonation(donation);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDonation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get all donations",
            description = "Returns a list of all registered donations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donations found")
    })
    @GetMapping
    public List<DonationEntity> getAllDonations() {
        return donationService.getAllDonations();
    }

    @Operation(summary = "Get a donation by ID",
            description = "Returns the details of a specific donation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation found"),
            @ApiResponse(responseCode = "404", description = "Donation not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DonationEntity> getDonationById(
            @Parameter(description = "ID of the donation to retrieve") @PathVariable @NotNull Long id) {
        try {
            DonationEntity donation = donationService.getDonationById(id);
            return ResponseEntity.ok(donation);
        } catch (DonationNonExistingException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Update an existing donation",
            description = "Updates the details of a donation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation updated successfully"),
            @ApiResponse(responseCode = "404", description = "Donation not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DonationEntity> updateDonation(
            @Parameter(description = "ID of the donation to update") @PathVariable @NotNull Long id,
            @Parameter(description = "Details of the donation to update") @Valid @RequestBody DonationEntity donationDetails) {
        try {
            DonationEntity updatedDonation = donationService.updateDonation(id, donationDetails);
            return ResponseEntity.ok(updatedDonation);
        } catch (DonationNonExistingException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete a donation",
            description = "Deletes a specific donation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Donation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Donation not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(
            @Parameter(description = "ID of the donation to delete") @PathVariable @NotNull Long id) {
        try {
            donationService.deleteDonation(id);
            return ResponseEntity.noContent().build();
        } catch (DonationNonExistingException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get donations by entrepreneurship ID",
            description = "Returns a list of donations associated with a specific entrepreneurship.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donations found"),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found")
    })
    @GetMapping("/entrepreneurship/{entrepreneurshipId}")
    public List<DonationEntity> getDonationsByEntrepreneurshipId(
            @Parameter(description = "ID of the entrepreneurship to search") @PathVariable @NotNull Long entrepreneurshipId) {
        return donationService.getDonationsByEntrepreneurshipId(entrepreneurshipId);
    }

    @Operation(summary = "Get donations by investor ID",
            description = "Returns a list of donations made by a specific investor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donations found"),
            @ApiResponse(responseCode = "404", description = "Investor not found")
    })
    @GetMapping("/investor/{investorId}")
    public List<DonationEntity> getDonationsByInvestorId(
            @Parameter(description = "ID of the investor to search") @PathVariable @NotNull Long investorId) {
        return donationService.getDonationsByInvestorId(investorId);
    }

    @Operation(summary = "Get donations within a date range",
            description = "Returns a list of donations that were made within a date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donations found"),
            @ApiResponse(responseCode = "400", description = "Invalid dates")
    })
    @GetMapping("/date-range")
    public List<DonationEntity> getDonationsByDateRange(
            @Parameter(description = "Start date of the range") @RequestParam @NotNull LocalDateTime startDate,
            @Parameter(description = "End date of the range") @RequestParam @NotNull LocalDateTime endDate) {
        return donationService.getDonationsByDateRange(startDate, endDate);
    }

    @Operation(summary = "Get the most recent donation",
            description = "Returns the most recently registered donation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation found"),
            @ApiResponse(responseCode = "404", description = "No donations registered")
    })
    @GetMapping("/latest")
    public ResponseEntity<DonationEntity> getLatestDonation() {
        try {
            DonationEntity latestDonation = donationService.getLatestDonation();
            return latestDonation != null ? ResponseEntity.ok(latestDonation) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
