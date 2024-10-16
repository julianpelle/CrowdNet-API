package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.entity.EntrepreneurEntity;
import com.utn.CapitalConnection.exception.EntrepreneurNonExistingException;
import com.utn.CapitalConnection.exception.InvestorNonExistingException;
import com.utn.CapitalConnection.service.EntrepreneurService;
import io.swagger.v3.oas.annotations.Parameter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrepreneurs")
public class EntrepreneurController extends UserController<EntrepreneurEntity> {

    private final EntrepreneurService entrepreneurService;

    @Autowired
    public EntrepreneurController(EntrepreneurService entrepreneurService) {
        super(entrepreneurService);
        this.entrepreneurService = entrepreneurService;
    }

    @PostMapping("/register")
    public ResponseEntity<EntrepreneurEntity> registerEntrepreneur(
            @Parameter(description = "Detalles del emprendedor a registrar")
            @Valid @RequestBody EntrepreneurEntity entrepreneur) {
        try {
            EntrepreneurEntity savedEntrepreneur = entrepreneurService.saveUser(entrepreneur);
            return ResponseEntity.status(201).body(savedEntrepreneur);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<EntrepreneurEntity> getUserById(
            @Parameter(description = "Identificador único de la cuenta", example = "1") @PathVariable Long id) {
        try {
            EntrepreneurEntity entrepreneur = entrepreneurService.findUserById(id);
            return ResponseEntity.ok(entrepreneur);
        } catch (InvestorNonExistingException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Manejo de excepciones genérico
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrepreneurEntity> updateEntrepreneur(
            @Parameter(description = "ID del emprendedor a actualizar")
            @PathVariable @NotNull Long id,
            @Parameter(description = "Detalles del emprendedor a actualizar")
            @Valid @RequestBody EntrepreneurEntity entrepreneurUpdates) {
        try {
            EntrepreneurEntity updatedEntrepreneur = entrepreneurService.updateUser(id, entrepreneurUpdates);
            return ResponseEntity.ok(updatedEntrepreneur);
        } catch (EntrepreneurNonExistingException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteUser(@Parameter(description = "Identificador único de la cuenta", example = "1") @PathVariable Long id) {
        try {
            entrepreneurService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (EntrepreneurNonExistingException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}