package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.EntrepreneurEntity;
import com.utn.CapitalConnection.repository.EntrepreneurRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepreneurService extends UserService<EntrepreneurEntity> {
    private final EntrepreneurRepository entrepreneurRepository;

    @Autowired
    public EntrepreneurService(EntrepreneurRepository entrepreneurRepository) {
        super(entrepreneurRepository);
        this.entrepreneurRepository = entrepreneurRepository;
    }

    @Operation(summary = "Find entrepreneurs by minimum success rate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of entrepreneurs retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid minimum success rate")
    })
    public List<EntrepreneurEntity> findEntrepreneursBySuccessRate(
            @Parameter(description = "Minimum success rate", example = "75.0") float minSuccessRate) {
        if (minSuccessRate < 0) {
            throw new IllegalArgumentException("Minimum success rate must be non-negative.");
        }
        return entrepreneurRepository.findEntrepreneursBySuccessRate(minSuccessRate);
    }

    @Operation(summary = "Find entrepreneurs by success rate range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of entrepreneurs retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid success rate values")
    })
    public List<EntrepreneurEntity> findEntrepreneursBySuccessRateRange(
            @Parameter(description = "Minimum success rate", example = "50.0") Float minSuccessRate,
            @Parameter(description = "Maximum success rate", example = "90.0") Float maxSuccessRate) {
        if (minSuccessRate == null || maxSuccessRate == null || minSuccessRate < 0 || maxSuccessRate < 0) {
            throw new IllegalArgumentException("Invalid success rate values.");
        }
        return entrepreneurRepository.findEntrepreneursBySuccessRateRange(minSuccessRate, maxSuccessRate);
    }

    @Operation(summary = "Find an entrepreneur by CBU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur found"),
            @ApiResponse(responseCode = "400", description = "CBU cannot be null or empty"),
            @ApiResponse(responseCode = "404", description = "Entrepreneur not found")
    })
    public Optional<EntrepreneurEntity> findEntrepreneurByCbu(
            @Parameter(description = "CBU of the entrepreneur", example = "1234567890123456789012") String cbu) {
        if (cbu == null || cbu.isEmpty()) {
            throw new IllegalArgumentException("CBU cannot be null or empty.");
        }
        return entrepreneurRepository.findEntrepreneurByCbu(cbu);
    }

    @Operation(summary = "Update an entrepreneur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur updated successfully"),
            @ApiResponse(responseCode = "404", description = "Entrepreneur not found")
    })
    public EntrepreneurEntity updateUser(
            @Parameter(description = "Unique entrepreneur identifier", example = "1") Long id,
            @Parameter(description = "Updated entrepreneur data") EntrepreneurEntity entrepreneurUpdates) {
        Optional<EntrepreneurEntity> optionalEntrepreneur = entrepreneurRepository.findById(id);

        if (optionalEntrepreneur.isPresent()) {
            EntrepreneurEntity existingEntrepreneur = optionalEntrepreneur.get();

            // Update fields as needed
            if (entrepreneurUpdates.getName() != null) {
                existingEntrepreneur.setName(entrepreneurUpdates.getName());
            }
            if (entrepreneurUpdates.getSurname() != null) {
                existingEntrepreneur.setSurname(entrepreneurUpdates.getSurname());
            }
            if (entrepreneurUpdates.getEmail() != null) {
                existingEntrepreneur.setEmail(entrepreneurUpdates.getEmail());
            }
            // Add more fields as necessary

            return entrepreneurRepository.save(existingEntrepreneur);
        }

        // Return null or throw an exception if the entrepreneur is not found
        return null; // Or throw an exception, depending on your error handling
    }
}
