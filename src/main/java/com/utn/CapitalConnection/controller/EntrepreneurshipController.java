package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.entity.EntrepreneurshipEntity;
import com.utn.CapitalConnection.repository.EntrepreneurshipRepository;
import com.utn.CapitalConnection.types.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entrepreneurships")
public class EntrepreneurshipController {
    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Operation(summary = "Get all entrepreneurships")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all entrepreneurships",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class)))
    })
    @GetMapping
    public List<EntrepreneurshipEntity> getAllEntrepreneurships() {
        return entrepreneurshipRepository.findAll();
    }

    @Operation(summary = "Get entrepreneurship by exact name (case-insensitive)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the entrepreneurship",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class))),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found",
                    content = @Content)
    })
    @GetMapping("/name")
    public ResponseEntity<EntrepreneurshipEntity> getEntrepreneurshipByName(
            @Parameter(description = "Name of the entrepreneurship to find") @RequestParam String name) {
        try {
            Optional<EntrepreneurshipEntity> entrepreneurship = entrepreneurshipRepository.findByName(name);
            return entrepreneurship.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(summary = "Search entrepreneurships by name (case-insensitive)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found entrepreneurships",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class)))
    })
    @GetMapping("/search")
    public List<EntrepreneurshipEntity> getEntrepreneurshipsByNameContaining(
            @Parameter(description = "Part of the name to search for") @RequestParam String name) {
        try {
            return entrepreneurshipRepository.findByNameContaining(name);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Operation(summary = "Get entrepreneurships by category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found entrepreneurships in the specified category",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class)))
    })
    @GetMapping("/category")
    public List<EntrepreneurshipEntity> getEntrepreneurshipsByCategory(
            @Parameter(description = "Category of the entrepreneurships") @RequestParam Category category) {
        try {
            return entrepreneurshipRepository.findByCategory(category);
        } catch (Exception e) {
            return List.of();
        }
    }

    @Operation(summary = "Get entrepreneurships with a minimum goal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found entrepreneurships above the specified goal",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class)))
    })
    @GetMapping("/goal/min")
    public List<EntrepreneurshipEntity> getEntrepreneurshipsByMinimumGoal(
            @Parameter(description = "Minimum goal amount") @RequestParam float minGoal) {
        try {
            return entrepreneurshipRepository.findByMinimumGoal(minGoal);
        } catch (Exception e) {

            return List.of();
        }
    }

    @GetMapping("/goal/max")
    public List<EntrepreneurshipEntity> getEntrepreneurshipsByMaximumGoal(
            @Parameter(description = "Maximum goal amount") @RequestParam float maxGoal) {
        try {
            return entrepreneurshipRepository.findByMaximumGoal(maxGoal);
        } catch (Exception e) {

            return List.of();
        }
    }

    @Operation(summary = "Get entrepreneurships within a goal range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found entrepreneurships within the specified range",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class)))
    })
    @GetMapping("/goal/range")
    public List<EntrepreneurshipEntity> getEntrepreneurshipsByGoalRange(
            @Parameter(description = "Minimum goal amount (optional)") @RequestParam(required = false) Float minGoal,
            @Parameter(description = "Maximum goal amount (optional)") @RequestParam(required = false) Float maxGoal) {
        try {
            return entrepreneurshipRepository.findByGoalRange(minGoal, maxGoal);
        } catch (Exception e) {

            return List.of();
        }
    }

    @Operation(summary = "Get entrepreneurships by entrepreneur ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found entrepreneurships associated with the entrepreneur",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class)))
    })
    @GetMapping("/entrepreneur/{entrepreneurId}")
    public List<EntrepreneurshipEntity> getEntrepreneurshipsByEntrepreneurId(
            @Parameter(description = "ID of the entrepreneur") @PathVariable Long entrepreneurId) {
        try {
            return entrepreneurshipRepository.findByEntrepreneurId(entrepreneurId);
        } catch (Exception e) {

            return List.of();
        }
    }

    @Operation(summary = "Search entrepreneurships by description (case-insensitive)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found entrepreneurships matching the description",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class)))
    })
    @GetMapping("/description")
    public List<EntrepreneurshipEntity> getEntrepreneurshipsByDescriptionContaining(
            @Parameter(description = "Part of the description to search for") @RequestParam String description) {
        try {
            return entrepreneurshipRepository.findByDescriptionContaining(description);
        } catch (Exception e) {

            return List.of();
        }
    }
}
