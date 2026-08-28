package com.example.gestioncommerciale.dto;

import com.example.gestioncommerciale.enums.DevisStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateDevisRequest {

    @NotBlank(message = "La référence est obligatoire")
    private String reference;

    @NotNull(message = "La date du devis est obligatoire")
    private LocalDate dateDevis;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false,
        message = "Le montant doit être supérieur à 0")
    private Double montant;

    @NotNull(message = "Le statut est obligatoire")
    private DevisStatus statut;

    @NotNull(message = "Le client est obligatoire")
    @Positive(message = "L'identifiant du client doit être positif")
    private Long clientId;

    @NotNull(message = "Le véhicule est obligatoire")
    @Positive(message = "L'identifiant du véhicule doit être positif")
    private Long vehiculeId;
}
