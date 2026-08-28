package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.dto.CreateVenteRequest;
import com.example.gestioncommerciale.entity.Vente;
import com.example.gestioncommerciale.service.VenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventes")
@RequiredArgsConstructor
@Tag(
    name = "Ventes",
    description = "Gestion des ventes commerciales"
)
public class VenteController {

    private final VenteService service;

    // GET ALL
    @Operation(
        summary = "Récupérer toutes les ventes",
        description = "Retourne la liste de toutes les ventes"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Liste des ventes récupérée avec succès"
        )
    })
    @GetMapping
    public List<Vente> getAll() {
        return service.getAllVentes();
    }

    // GET BY ID
    @Operation(
        summary = "Récupérer une vente",
        description = "Récupère une vente à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Vente trouvée"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Vente introuvable"
        )
    })
    @GetMapping("/{id}")
    public Vente getById(@PathVariable Long id) {
        return service.getVenteById(id);
    }

    // CREATE
    @Operation(
        summary = "Créer une vente",
        description = "Crée une nouvelle vente à partir des informations fournies"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Vente créée avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides"
        )
    })
    @PostMapping
    public Vente create(
        @Valid @RequestBody CreateVenteRequest request) {

        return service.createVente(request);
    }

    // UPDATE
    @Operation(
        summary = "Modifier une vente",
        description = "Modifie une vente existante"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Vente modifiée avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides ou vente introuvable"
        )
    })
    @PutMapping("/{id}")
    public Vente update(
        @PathVariable Long id,
        @Valid @RequestBody CreateVenteRequest request) {

        return service.updateVente(id, request);
    }

    // DELETE
    @Operation(
        summary = "Supprimer une vente",
        description = "Supprime une vente à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Vente supprimée avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Vente introuvable"
        )
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        service.deleteVente(id);

        return "Vente supprimée avec succès";
    }
}
