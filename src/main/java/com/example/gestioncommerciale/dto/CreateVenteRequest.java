package com.example.gestioncommerciale.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateVenteRequest {

    @NotNull(message = "La date de vente est obligatoire")
    private LocalDate dateVente;

    @NotNull(message = "Le montant final est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false,
        message = "Le montant final doit être supérieur à 0")
    private Double montantFinal;

    @NotBlank(message = "Le mode de paiement est obligatoire")
    private String modePaiement;

    @NotNull(message = "Le devis est obligatoire")
    @Positive(message = "L'identifiant du devis doit être positif")
    private Long devisId;
}
