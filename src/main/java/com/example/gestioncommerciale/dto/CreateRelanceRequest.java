package com.example.gestioncommerciale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateRelanceRequest {

    @NotNull(message = "La date de relance est obligatoire")
    private LocalDate dateRelance;

    @NotBlank(message = "Le commentaire est obligatoire")
    private String commentaire;

    @NotBlank(message = "Le résultat est obligatoire")
    private String resultat;

    @NotNull(message = "Le client est obligatoire")
    @Positive(message = "L'identifiant du client doit être positif")
    private Long clientId;
}
