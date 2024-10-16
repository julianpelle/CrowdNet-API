package com.utn.CapitalConnection.controller;

import com.utn.CapitalConnection.entity.EntrepreneurEntity;
import com.utn.CapitalConnection.service.EntrepreneurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrepreneurs")
public class EntrepreneurController extends UserController<EntrepreneurEntity> {

    private final EntrepreneurService entrepreneurService;

    @Autowired
    public EntrepreneurController(EntrepreneurService entrepreneurService) {
        super(entrepreneurService);
        this.entrepreneurService = entrepreneurService;
    }

    @Operation(summary = "Registrar un nuevo emprendedor",
            description = "Crea un nuevo emprendedor en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Emprendedor registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud no válida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/register")  // Ruta ajustada
    public EntrepreneurEntity registerEntrepreneur(
            @Parameter(description = "Detalles del emprendedor a registrar")
            @RequestBody EntrepreneurEntity entrepreneur) {
        return entrepreneurService.saveUser(entrepreneur);
    }

    @Operation(summary = "Actualizar un emprendedor existente",
            description = "Actualiza los detalles de un emprendedor en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emprendedor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Emprendedor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud no válida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PatchMapping("/{id}") // Ruta para actualizar el emprendedor
    public ResponseEntity<EntrepreneurEntity> updateEntrepreneur(
            @Parameter(description = "ID del emprendedor a actualizar")
            @PathVariable Long id,
            @Parameter(description = "Detalles del emprendedor a actualizar")
            @RequestBody EntrepreneurEntity entrepreneurUpdates) {

        EntrepreneurEntity updatedEntrepreneur = entrepreneurService.updateUser(id, entrepreneurUpdates);

        if (updatedEntrepreneur == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedEntrepreneur);
    }

    // Agregar otros métodos específicos para emprendedores, si es necesario
}
