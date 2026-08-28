package com.example.gestioncommerciale.controller;

import com.example.gestioncommerciale.entity.Client;
import com.example.gestioncommerciale.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(
    name = "Clients",
    description = "Gestion des clients"
)
public class ClientController {

    private final ClientService service;

    // GET ALL
    @Operation(
        summary = "Récupérer tous les clients",
        description = "Retourne la liste de tous les clients"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Liste des clients récupérée avec succès"
        )
    })
    @GetMapping
    public List<Client> getAll() {
        return service.getAllClients();
    }

    // GET BY ID
    @Operation(
        summary = "Récupérer un client",
        description = "Récupère un client à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Client trouvé"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Client introuvable"
        )
    })
    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return service.getClientById(id);
    }

    // CREATE
    @Operation(
        summary = "Créer un client",
        description = "Crée un nouveau client et l'associe automatiquement à l'administrateur connecté"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Client créé avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides"
        )
    })
    @PostMapping
    public Client create(
        @Valid @RequestBody Client client) {

        return service.saveClient(client);
    }

    // UPDATE
    @Operation(
        summary = "Modifier un client",
        description = "Modifie les informations d'un client existant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Client modifié avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données invalides ou client introuvable"
        )
    })
    @PutMapping("/{id}")
    public Client update(
        @PathVariable Long id,
        @Valid @RequestBody Client client) {

        return service.updateClient(id, client);
    }

    // DELETE
    @Operation(
        summary = "Supprimer un client",
        description = "Supprime un client à partir de son identifiant"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Client supprimé avec succès"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Client introuvable"
        )
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteClient(id);
    }
}
