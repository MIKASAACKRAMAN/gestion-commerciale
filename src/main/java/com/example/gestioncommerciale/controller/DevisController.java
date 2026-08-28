package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.dto.CreateDevisRequest;
import com.example.gestioncommerciale.entity.Devis;
import com.example.gestioncommerciale.service.DevisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devis")
@RequiredArgsConstructor
@Tag(
    name = "Devis",
    description = "Gestion des devis commerciaux"
)
public class DevisController {

    private final DevisService service;

    // GET ALL
    @Operation(
        summary = "Récupérer tous les devis",
        description = "Retourne la liste de tous les devis"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Liste des devis récupérée avec succès"
        )
    })
    @GetMapping
    public List<Devis> getAll() {
        return service.getAllDevis();
    }

    // GET BY ID
    @Operation(
        summary = "Récupérer un devis",
        description = "Récupère un devis à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Devis trouvé"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Devis introuvable"
        )
    })
    @GetMapping("/{id}")
    public Devis getById(@PathVariable Long id) {
        return service.getDevisById(id);
    }

    // CREATE
    @Operation(
        summary = "Créer un devis",
        description = "Crée un nouveau devis à partir d'un client et d'un véhicule"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Devis créé avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides"
        )
    })
    @PostMapping
    public Devis create(
        @Valid @RequestBody CreateDevisRequest request) {

        return service.createDevis(request);
    }

    // UPDATE
    @Operation(
        summary = "Modifier un devis",
        description = "Modifie un devis existant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Devis modifié avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides ou devis introuvable"
        )
    })
    @PutMapping("/{id}")
    public Devis update(
        @PathVariable Long id,
        @Valid @RequestBody CreateDevisRequest request) {

        return service.updateDevis(id, request);
    }

    // DELETE
    @Operation(
        summary = "Supprimer un devis",
        description = "Supprime un devis à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Devis supprimé avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Devis introuvable"
        )
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        service.deleteDevis(id);

        return "Devis supprimé avec succès";
    }
}
