package com.utn.CapitalConnection.controller;

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

    private final EntrepreneurshipService entrepreneurshipService;

    @Autowired
    public EntrepreneurshipController(EntrepreneurshipService entrepreneurshipService) {
        this.entrepreneurshipService = entrepreneurshipService;
    }

    @GetMapping("/entrepreneurships") public Page<Entrepreneurship> getEntrepreneurships( @Parameter(description = "Filtro opcional para buscar emprendimientos por nombre", example = "Mi Startup") @RequestParam(required = false) String name, @Parameter(description = "Filtro opcional para buscar emprendimientos con una calificación específica", example = "4.5") @RequestParam(required = false) Float stars, @Parameter(description = "Filtro opcional para buscar emprendimientos con una meta específica", example = "50000") @RequestParam(required = false) BigDecimal goal, @Parameter(description = "Ordenar la lista de emprendimientos por id, nombre o meta", example = "name") @RequestParam(required = false, defaultValue = "id") OrderEntrepreneurship order, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) { Pageable pageable = PageRequest.of(page, size, Sort.by(order.getProperty()).ascending()); return entrepreneurshipService.findEntrepreneurship(name, stars, goal, pageable); }

    @Operation(summary = "Get all entrepreneurships")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all entrepreneurships",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntrepreneurshipEntity.class)))
    })
    @GetMapping("/all")
    public List<EntrepreneurshipEntity> getAllEntrepreneurships() {
        return entrepreneurshipService.getAllEntrepreneurships();
    }


    @Operation(summary = "Get entrepreneurships by user id", description = "Returns a list of entrepreneurships that match a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    @GetMapping("/{id_user}")
    public List<EntrepreneurshipEntity> getReviewsByUserId(@Parameter(description = "Id  to search for")
                                                 @PathVariable @Positive String Id_user) {
        return entrepreneurshipService.getEntrepreneurshipByUserId(Id_user);
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
        if (entrepreneurship.getId_user() == null) {
            entrepreneurship.setId_user("defaultUserId");
        }
        if (entrepreneurship.getReviews() == null) {
            entrepreneurship.setReviews(new ArrayList<>());
        }
        EntrepreneurshipEntity createdEntrepreneurship = entrepreneurshipService.createEntrepreneurship(entrepreneurship);
        return new ResponseEntity<>(createdEntrepreneurship, HttpStatus.CREATED);
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
    public ResponseEntity<EntrepreneurshipEntity> updateEntrepreneurship(
            @PathVariable Long id, @RequestBody EntrepreneurshipEntity updatedEntrepreneurship) {
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
    public ResponseEntity<EntrepreneurshipEntity> partiallyUpdateEntrepreneurship(
            @PathVariable Long id, @RequestBody EntrepreneurshipEntity partialUpdate) {
        return ResponseEntity.ok(entrepreneurshipService.partiallyUpdateEntrepreneurship(id, partialUpdate.getName(), partialUpdate.getGoal()));
    }

    @Operation(summary = "Delete an entrepreneurship by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entrepreneurship deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Entrepreneurship not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrepreneurship(@PathVariable Long id) {
        entrepreneurshipService.deleteEntrepreneurship(id);
        return ResponseEntity.noContent().build();
    }
}
