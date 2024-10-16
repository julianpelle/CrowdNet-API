package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.DonationEntity;
import com.utn.CapitalConnection.exception.DonationNonExistingException;
import com.utn.CapitalConnection.repository.DonationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {
    private final DonationRepository donationRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Operation(summary = "Register a new donation",
            description = "Creates a new donation in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Donation registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public DonationEntity saveDonation(
            @Parameter(description = "Details of the donation to register")
            @Valid DonationEntity donation) {
        if (donation.getAmount() == null || donation.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The donation amount must be greater than zero.");
        }
        return donationRepository.save(donation);
    }

    @Operation(summary = "Get all donations",
            description = "Returns a list of all registered donations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donations found")
    })
    public List<DonationEntity> getAllDonations() {
        return donationRepository.findAll();
    }

    @Operation(summary = "Get a donation by ID",
            description = "Returns the details of a specific donation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation found"),
            @ApiResponse(responseCode = "404", description = "Donation not found")
    })
    public DonationEntity getDonationById(@Parameter(description = "ID of the donation to retrieve") @NotNull Long id) {
        return donationRepository.findById(id).orElseThrow(() -> new DonationNonExistingException("Donation not found"));
    }

    @Operation(summary = "Update an existing donation",
            description = "Updates the details of a donation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation updated successfully"),
            @ApiResponse(responseCode = "404", description = "Donation not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public DonationEntity updateDonation(
            @Parameter(description = "ID of the donation to update")
            Long id,
            @Parameter(description = "Details of the donation to update")
            DonationEntity donationDetails) throws DonationNonExistingException {
        DonationEntity donation = getDonationById(id);
        donation.setAmount(donationDetails.getAmount());
        donation.setDate(donationDetails.getDate());
        return donationRepository.save(donation);
    }


    @Operation(summary = "Delete a donation",
            description = "Deletes a specific donation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Donation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Donation not found")
    })
    public void deleteDonation(@NotNull Long id) {
        DonationEntity donation = getDonationById(id);
        donationRepository.delete(donation);
    }

    public List<DonationEntity> getDonationsByEntrepreneurshipId(@NotNull Long entrepreneurshipId) {
        return donationRepository.findByEntrepreneurshipId(entrepreneurshipId);
    }

    public List<DonationEntity> getDonationsByInvestorId(@NotNull Long investorId) {
        return donationRepository.findByInvestorId(investorId);
    }

    public List<DonationEntity> getDonationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.findByDateBetween(startDate, endDate);
    }

    public DonationEntity getLatestDonation() {
        return donationRepository.findTopByOrderByDateDesc().orElse(null);
    }
}
