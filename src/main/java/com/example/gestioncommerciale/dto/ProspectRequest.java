package com.example.gestioncommerciale.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProspectRequest {

    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String adresse;
    private String statut;
    private LocalDate dateCreation;

}
