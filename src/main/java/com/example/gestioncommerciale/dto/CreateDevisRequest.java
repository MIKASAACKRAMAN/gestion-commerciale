package com.example.gestioncommerciale.dto;

import com.example.gestioncommerciale.enums.DevisStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateDevisRequest {

    private String reference;
    private LocalDate dateDevis;
    private Double montant;
    private DevisStatus statut;

    private Long clientId;
    private Long vehiculeId;

}
