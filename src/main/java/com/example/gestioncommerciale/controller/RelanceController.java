package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.dto.CreateRelanceRequest;
import com.example.gestioncommerciale.entity.Relance;
import com.example.gestioncommerciale.service.RelanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relances")
@RequiredArgsConstructor
@Tag(
    name = "Relances",
    description = "Gestion des relances commerciales"
)
public class RelanceController {

    private final RelanceService service;

    // GET ALL
    @Operation(
        summary = "Récupérer toutes les relances",
        description = "Retourne la liste de toutes les relances"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Liste des relances récupérée avec succès"
        )
    })
    @GetMapping
    public List<Relance> getAll() {
        return service.getAllRelances();
    }

    // GET BY ID
    @Operation(
        summary = "Récupérer une relance",
        description = "Récupère une relance à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Relance trouvée"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Relance introuvable"
        )
    })
    @GetMapping("/{id}")
    public Relance getById(@PathVariable Long id) {
        return service.getRelanceById(id);
    }

    // CREATE
    @Operation(
        summary = "Créer une relance",
        description = "Crée une nouvelle relance commerciale"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Relance créée avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides"
        )
    })
    @PostMapping
    public Relance create(
        @Valid @RequestBody CreateRelanceRequest request) {

        return service.createRelance(request);
    }

    // UPDATE
    @Operation(
        summary = "Modifier une relance",
        description = "Modifie une relance existante"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Relance modifiée avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides ou relance introuvable"
        )
    })
    @PutMapping("/{id}")
    public Relance update(
        @PathVariable Long id,
        @Valid @RequestBody CreateRelanceRequest request) {

        return service.updateRelance(id, request);
    }

    // DELETE
    @Operation(
        summary = "Supprimer une relance",
        description = "Supprime une relance à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Relance supprimée avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Relance introuvable"
        )
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        service.deleteRelance(id);

        return "Relance supprimée avec succès";
    }
}
