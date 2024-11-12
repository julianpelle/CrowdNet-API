package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.dto.EntrepreneurshipRequest;
import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import com.utn.CapitalConnection.entity.ReviewEntity;
import com.utn.CapitalConnection.model.Entrepreneurship;
import com.utn.CapitalConnection.service.EntrepreneurshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/entrepreneurships")
public class EntrepreneurshipController {

    @Autowired
    private EntrepreneurshipService entrepreneurshipService;

    @GetMapping
    public ResponseEntity<Page<EntrepreneurshipEntity>> getEntrepreneurships(Pageable pageable) {
        Page<EntrepreneurshipEntity> entrepreneurships = entrepreneurshipService.getAllEntrepreneurships(pageable);
        return ResponseEntity.ok(entrepreneurships);
    }



    @Operation(summary = "Get entrepreneurships by user id", description = "Returns a list of entrepreneurships that match a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    @GetMapping("/u/{idUser}")
    public List<EntrepreneurshipEntity> getReviewsByUserId(@Parameter(description = "Id  to search for")
                                                 @PathVariable String idUser) {
        return entrepreneurshipService.getEntrepreneurshipByUserId(idUser);
    }
    @Operation(summary = "Get entrepreneurship by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the entrepreneurship",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class))),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found",
                    content = @Content)
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<EntrepreneurshipEntity> getEntrepreneurshipByName(
            @Parameter(description = "Name of the entrepreneurship to find") @PathVariable String name) {
        return ResponseEntity.ok(entrepreneurshipService.getEntrepreneurshipByName(name));
    }

    @PostMapping
    public ResponseEntity<EntrepreneurshipEntity> createEntrepreneurship(
            @RequestBody EntrepreneurshipEntity entrepreneurship) {
        // Verificar si idUser es null y manejarlo según sea necesario
        if (entrepreneurship.getIdUser() == null) {
            entrepreneurship.setIdUser("defaultUserId");
        }

        EntrepreneurshipEntity createdEntrepreneurship = entrepreneurshipService.createEntrepreneurship(entrepreneurship);
        return new ResponseEntity<>(createdEntrepreneurship, HttpStatus.CREATED);
    }


    @Operation(summary = "Get entrepreneurship by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneurship found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class))),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntrepreneurshipEntity> getEntrepreneurshipById(
            @Parameter(description = "ID of the entrepreneurship to find") @PathVariable Long id) {
        try {
            EntrepreneurshipEntity entrepreneurship = entrepreneurshipService.getEntrepreneurshipById(id);
            return ResponseEntity.ok(entrepreneurship);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update an existing entrepreneurship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneurship updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class))),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntrepreneurship(
            @PathVariable(required = false) Long id,
            @RequestBody EntrepreneurshipEntity updatedEntrepreneurship) {

        if (id == null) {
            return ResponseEntity.badRequest().body("ID is missing or invalid.");
        }

        return ResponseEntity.ok(entrepreneurshipService.updateEntrepreneurship(id, updatedEntrepreneurship));
    }

    @Operation(summary = "Partially update an existing entrepreneurship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneurship partially updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class))),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found",
                    content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<EntrepreneurshipEntity> patchEntrepreneurship(
            @PathVariable Long id,
            @RequestBody EntrepreneurshipRequest patchData) {
        EntrepreneurshipEntity updated = entrepreneurshipService.patchEntrepreneurship(id, patchData);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Deactivate an entrepreneurship by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entrepreneurship deactivated",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateEntrepreneurship(@PathVariable Long id) {
        entrepreneurshipService.deactivateEntrepreneurship(id);
        return ResponseEntity.noContent().build();  // Respondemos que la operación se realizó correctamente
    }

    @GetMapping("/active")
    public ResponseEntity<Page<EntrepreneurshipEntity>> getActiveEntrepreneurships(Pageable pageable) {
        Page<EntrepreneurshipEntity> activeEntrepreneurships = entrepreneurshipService.getActiveEntrepreneurships(pageable);
        return ResponseEntity.ok(activeEntrepreneurships);
    }
}
