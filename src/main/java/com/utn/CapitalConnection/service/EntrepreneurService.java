package com.utn.CapitalConnection.service;

import com.utn.CapitalConnection.entity.EntrepreneurEntity;
import com.utn.CapitalConnection.exception.EntrepreneurNonExistingException;
import com.utn.CapitalConnection.repository.EntrepreneurRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            @Parameter(description = "Minimum success rate", example = "75.0")
            @NotNull @Positive float minSuccessRate) {
        return entrepreneurRepository.findEntrepreneursBySuccessRate(minSuccessRate);
    }

    @Operation(summary = "Find entrepreneurs by success rate range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of entrepreneurs retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid success rate values")
    })
    public List<EntrepreneurEntity> findEntrepreneursBySuccessRateRange(
            @Parameter(description = "Minimum success rate", example = "50.0")
            @NotNull Float minSuccessRate,
            @Parameter(description = "Maximum success rate", example = "90.0")
            @NotNull Float maxSuccessRate) {
        return entrepreneurRepository.findEntrepreneursBySuccessRateRange(minSuccessRate, maxSuccessRate);
    }

    @Operation(summary = "Update an entrepreneur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur updated successfully"),
            @ApiResponse(responseCode = "404", description = "Entrepreneur not found")
    })
    public EntrepreneurEntity updateUser(Long id, EntrepreneurEntity entrepreneurUpdates) throws EntrepreneurNonExistingException {
        try {
            EntrepreneurEntity existingEntrepreneur = entrepreneurRepository.findById(id)
                    .orElseThrow(() -> new EntrepreneurNonExistingException(id));

            // Validaciones
            if (entrepreneurUpdates.getName() == null || entrepreneurUpdates.getName().isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            }
            if (entrepreneurUpdates.getSurname() == null || entrepreneurUpdates.getSurname().isBlank()) {
                throw new IllegalArgumentException("El apellido no puede estar vacío");
            }
            if (entrepreneurUpdates.getEmail() == null || entrepreneurUpdates.getEmail().isBlank()) {
                throw new IllegalArgumentException("El email no puede estar vacío");
            }
            if (entrepreneurUpdates.getSuccessRate() < 0) {
                throw new IllegalArgumentException("La tasa de éxito debe ser positiva");
            }
            if (entrepreneurUpdates.getCbu() == null || entrepreneurUpdates.getCbu().isBlank()) {
                throw new IllegalArgumentException("El CBU no puede estar vacío");
            }

            existingEntrepreneur.setName(entrepreneurUpdates.getName());
            existingEntrepreneur.setSurname(entrepreneurUpdates.getSurname());
            existingEntrepreneur.setEmail(entrepreneurUpdates.getEmail());
            existingEntrepreneur.setDateOfBirth(entrepreneurUpdates.getDateOfBirth());
            existingEntrepreneur.setWallet(entrepreneurUpdates.getWallet());
            existingEntrepreneur.setYearsOfExperience(entrepreneurUpdates.getYearsOfExperience());
            existingEntrepreneur.setCategory(entrepreneurUpdates.getCategory());
            existingEntrepreneur.setAddress(entrepreneurUpdates.getAddress());
            existingEntrepreneur.setSuccessRate(entrepreneurUpdates.getSuccessRate());
            existingEntrepreneur.setCbu(entrepreneurUpdates.getCbu());

            return entrepreneurRepository.save(existingEntrepreneur);
        } catch (EntrepreneurNonExistingException e) {
            throw e;
        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException("Datos inválidos: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el emprendedor: " + e.getMessage(), e);
        }
    }
}
