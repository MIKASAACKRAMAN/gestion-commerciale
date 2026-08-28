package com.example.gestioncommerciale.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ventes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateVente;

    @Column(nullable = false)
    private Double montantFinal;

    @Column(nullable = false)
    private String modePaiement;

    @OneToOne
    @JoinColumn(name = "devis_id", nullable = false, unique = true)
    private Devis devis;
}
