package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.entity.Prospect;
import com.example.gestioncommerciale.service.ProspectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prospects")
@RequiredArgsConstructor
@Tag(
    name = "Prospects",
    description = "Gestion des prospects"
)
public class ProspectController {

    private final ProspectService service;

    // GET ALL
    @Operation(
        summary = "Récupérer tous les prospects",
        description = "Retourne la liste de tous les prospects"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Liste des prospects récupérée avec succès"
        )
    })
    @GetMapping
    public List<Prospect> getAll() {
        return service.getAllProspects();
    }

    // GET BY ID
    @Operation(
        summary = "Récupérer un prospect",
        description = "Récupère un prospect à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Prospect trouvé"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Prospect introuvable"
        )
    })
    @GetMapping("/{id}")
    public Prospect getById(@PathVariable Long id) {
        return service.getProspectById(id);
    }

    // CREATE
    @Operation(
        summary = "Créer un prospect",
        description = "Crée un nouveau prospect"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Prospect créé avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides"
        )
    })
    @PostMapping
    public Prospect create(
        @Valid @RequestBody Prospect prospect) {

        return service.saveProspect(prospect);
    }

    // UPDATE
    @Operation(
        summary = "Modifier un prospect",
        description = "Modifie les informations d'un prospect existant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Prospect modifié avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides ou prospect introuvable"
        )
    })
    @PutMapping("/{id}")
    public Prospect update(
        @PathVariable Long id,
        @Valid @RequestBody Prospect prospect) {

        return service.updateProspect(id, prospect);
    }

    // DELETE
    @Operation(
        summary = "Supprimer un prospect",
        description = "Supprime un prospect à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Prospect supprimé avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Prospect introuvable"
        )
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteProspect(id);
    }
}
