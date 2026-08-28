package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.entity.Vehicule;
import com.example.gestioncommerciale.service.VehiculeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
@Tag(
    name = "Véhicules",
    description = "Gestion des véhicules"
)
public class VehiculeController {

    private final VehiculeService service;

    // GET ALL
    @Operation(
        summary = "Récupérer tous les véhicules",
        description = "Retourne la liste de tous les véhicules"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Liste des véhicules récupérée avec succès"
        )
    })
    @GetMapping
    public List<Vehicule> getAll() {
        return service.getAllVehicules();
    }

    // GET BY ID
    @Operation(
        summary = "Récupérer un véhicule",
        description = "Récupère un véhicule à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Véhicule trouvé"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Véhicule introuvable"
        )
    })
    @GetMapping("/{id}")
    public Vehicule getById(@PathVariable Long id) {
        return service.getVehiculeById(id);
    }

    // CREATE
    @Operation(
        summary = "Créer un véhicule",
        description = "Crée un nouveau véhicule et l'associe automatiquement à l'administrateur connecté"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Véhicule créé avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides"
        )
    })
    @PostMapping
    public Vehicule create(
        @Valid @RequestBody Vehicule vehicule) {

        return service.saveVehicule(vehicule);
    }

    // UPDATE
    @Operation(
        summary = "Modifier un véhicule",
        description = "Modifie les informations d'un véhicule existant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Véhicule modifié avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides ou véhicule introuvable"
        )
    })
    @PutMapping("/{id}")
    public Vehicule update(
        @PathVariable Long id,
        @Valid @RequestBody Vehicule vehicule) {

        return service.updateVehicule(id, vehicule);
    }

    // DELETE
    @Operation(
        summary = "Supprimer un véhicule",
        description = "Supprime un véhicule à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Véhicule supprimé avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Véhicule introuvable"
        )
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        service.deleteVehicule(id);

        return "Véhicule supprimé avec succès";
    }
}
