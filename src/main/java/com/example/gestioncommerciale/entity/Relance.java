package com.example.gestioncommerciale.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "relances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateRelance;

    @Column(nullable = false)
    private String commentaire;

    @Column(nullable = false)
    private String resultat;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;
}
